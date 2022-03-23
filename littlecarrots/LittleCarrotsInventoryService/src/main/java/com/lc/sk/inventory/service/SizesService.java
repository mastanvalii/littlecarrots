package com.lc.sk.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.bean.SizesDetails;
import com.lc.sk.inventory.entities.Sizes;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.SizeNotFoundException;
import com.lc.sk.inventory.repositories.SizesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.GarbageCollector;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class SizesService {

	@Autowired
	SizesRepository sizesRepository;



	// Get size by id
	@GetMapping(path = URLMapping.SIZE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Sizes>> getsizebyid(@PathVariable long sizeid) {
		Optional<Sizes> sizes = this.sizesRepository.getSizesById(sizeid);
		if (sizes.isPresent()) {
			return new ResponseEntity<Optional<Sizes>>(sizes,   HttpStatus.ACCEPTED);
		} else {
			throw new SizeNotFoundException(ConstantValues.SIZES_NOT_FOUND_EXCEPTION);
		}

	}

	// Get list of Sizes
	@GetMapping(path = URLMapping.SIZES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sizes>> getAllSizes() {
		List<Sizes> sizes = (List<Sizes>) this.sizesRepository.getAllSizes();
		if (sizes.isEmpty()) {
			throw new SizeNotFoundException(ConstantValues.SIZES_NOT_FOUND_EXCEPTION);
		} else {
			return new ResponseEntity<List<Sizes>>(sizes,   HttpStatus.ACCEPTED);
		}
	}	
	
	
	// Insert sizes
	@PostMapping(path = URLMapping.SIZES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertsize(
			@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) String ageid,
			@RequestParam(name = ConstantValues.GENDER, required = true, defaultValue = ConstantValues.DEFAULT_INT) String gender,
			@RequestParam(name = ConstantValues.SIZE_NO, required = true, defaultValue = ConstantValues.DEFAULT_INT) String sizeno,
			@RequestParam(name = ConstantValues.HEIGHT, required = true, defaultValue = ConstantValues.DEFAULT_INT) String height,
			@RequestParam(name = ConstantValues.WEIGHT, required = true, defaultValue = ConstantValues.DEFAULT_INT) String weight,
			@RequestParam(name = ConstantValues.CHEST, required = true, defaultValue = ConstantValues.DEFAULT_INT) String chest,
			@RequestParam(name = ConstantValues.WAIST, required = true, defaultValue = ConstantValues.DEFAULT_INT) String waist,
			@RequestParam(name = ConstantValues.HIP, required = true, defaultValue = ConstantValues.DEFAULT_INT) String hip,
			@RequestParam(name = ConstantValues.SUB_CAT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long subcatid,
			@RequestParam(name = ConstantValues.ISOCODE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String isocode) {
		if (ageid.equals(ConstantValues.DEFAULT_INT) 
				//|| sizeno.equals(ConstantValues.DEFAULT_INT)
				||gender.equals(ConstantValues.DEFAULT_INT) 
				|| subcatid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| isocode.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			Sizes sizes = this.sizesRepository.save(new Sizes(ageid,gender, sizeno, height, weight, chest, waist, hip,subcatid,isocode));

			if (sizes.getAgeid() == ageid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}

	}

	// Update by id
	@PutMapping(path = URLMapping.SIZES, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatesize(
			@RequestParam(name = ConstantValues.SIZE_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long sizeid,
			@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) String ageid,
			@RequestParam(name = ConstantValues.GENDER, required = true, defaultValue = ConstantValues.DEFAULT_INT) String gender,
			@RequestParam(name = ConstantValues.SIZE_NO, required = true, defaultValue = ConstantValues.DEFAULT_INT) String sizeno,
			@RequestParam(name = ConstantValues.HEIGHT, required = true, defaultValue = ConstantValues.DEFAULT_INT) String height,
			@RequestParam(name = ConstantValues.WEIGHT, required = true, defaultValue = ConstantValues.DEFAULT_INT) String weight,
			@RequestParam(name = ConstantValues.CHEST, required = true, defaultValue = ConstantValues.DEFAULT_INT) String chest,
			@RequestParam(name = ConstantValues.WAIST, required = true, defaultValue = ConstantValues.DEFAULT_INT) String waist,
			@RequestParam(name = ConstantValues.HIP, required = true, defaultValue = ConstantValues.DEFAULT_INT) String hip,
			@RequestParam(name = ConstantValues.SUB_CAT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long subcatid,
			@RequestParam(name = ConstantValues.ISOCODE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String isocode) {
		if ( sizeid == Long.parseLong(ConstantValues.DEFAULT_INT)
				||ageid.equals(ConstantValues.DEFAULT_INT) 
				//|| sizeno.equals(ConstantValues.DEFAULT_INT)
				|| gender.equals(ConstantValues.DEFAULT_INT)
				|| subcatid==Long.parseLong(ConstantValues.DEFAULT_INT) 
				||isocode.equals(ConstantValues.DEFAULT_STRING)) {
			
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
		Optional<Sizes> sizes = this.sizesRepository.findById(sizeid);
		if (sizes.isPresent()) {
			sizes.get().setAgeid(ageid);
			sizes.get().setSizeno(sizeno);
			sizes.get().setHeight(height);
			sizes.get().setWeight(weight);
			sizes.get().setChest(chest);
			sizes.get().setWaist(waist);
			sizes.get().setHip(hip);
			sizes.get().setGender(gender);
			sizes.get().setSubcatid(subcatid);
			sizes.get().setIsocode(isocode);
			Sizes size = this.sizesRepository.save(sizes.get());

			if (size.getAgeid() == ageid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
		} else {
			throw new SizeNotFoundException(ConstantValues.SIZES_NOT_FOUND_EXCEPTION);
		}
	}}

	@GetMapping(path=URLMapping.SIZESLIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SizesDetails>> getAllSubcatDetails(){
		GarbageCollector.clearMemory();
		List<SizesDetails> value = this.sizesRepository.getDetails();
		return new ResponseEntity<List<SizesDetails>>(value,  HttpStatus.ACCEPTED);
	}


}
