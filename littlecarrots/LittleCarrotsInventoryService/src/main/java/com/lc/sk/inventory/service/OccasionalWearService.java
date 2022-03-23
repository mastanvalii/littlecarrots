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

import com.lc.sk.inventory.entities.OccasionalWear;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.OccasionWearNotFoundException;
import com.lc.sk.inventory.exception.subexception.OccasionidNotFoundException;
import com.lc.sk.inventory.repositories.OccasionalWearRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)

public class OccasionalWearService {

	@Autowired
	OccasionalWearRepository occasionwearRepository;



	public OccasionalWearService() {

	}

	// Get all OCCASIONS
	@GetMapping(path = URLMapping.OCCASIONALWEAR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<OccasionalWear>> getAlloccasionalwearDetails() {
		List<OccasionalWear> occasions = (List<OccasionalWear>) this.occasionwearRepository.getallocc();

		if (occasions.isEmpty()) {
			throw new OccasionWearNotFoundException(ConstantValues.NO_OCCASIONALWEAR_LIST_FOUND);
		} else {
			return new ResponseEntity<List<OccasionalWear>>(occasions,  HttpStatus.ACCEPTED);
		}
	}

	// Get by ID
	@GetMapping(path = URLMapping.OCCASIONALWEAR_PATH_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<OccasionalWear>> getoccasionId(@PathVariable int occasionid) {
		Optional<OccasionalWear> occasions = this.occasionwearRepository.getoccbyid(occasionid);
		if (occasions.isPresent()) {

			return new ResponseEntity<Optional<OccasionalWear>>(occasions,  HttpStatus.ACCEPTED);
		} else {
			throw new OccasionWearNotFoundException(ConstantValues.OCCASIONID_NOT_FOUND);
		}
	}

	// insertion
	@PostMapping(path = URLMapping.OCCASIONALWEAR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name = ConstantValues.OCCANAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String occaname,
			@RequestParam(name = ConstantValues.SUBCATID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long subcatid) {
		ResponseBean responseBean = new ResponseBean();
		if (occaname.equals(ConstantValues.DEFAULT_STRING)
				|| subcatid == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			OccasionalWear occasionwear = this.occasionwearRepository.save(new OccasionalWear(occaname, subcatid));
			if (occasionwear.getOccaname().equals(occaname)) {
				responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());

				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}
	}

	// update
	@PutMapping(path = URLMapping.OCCASIONALWEAR_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(
			@RequestParam(name = ConstantValues.OCCASIONID, required = true, defaultValue = ConstantValues.DEFAULT_INT) int occasionid,
			@RequestParam(name = ConstantValues.OCCANAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String occaname,
			@RequestParam(name = ConstantValues.SUBCATID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long subcatid) {
		if (occasionid == Long.parseLong(ConstantValues.DEFAULT_INT) || occaname.equals(ConstantValues.DEFAULT_STRING)
				|| subcatid == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
		Optional<OccasionalWear> occasionsget = this.occasionwearRepository.findById(occasionid);
		if (occasionsget.isPresent()) {
			occasionsget.get().setOccaname(occaname);
			occasionsget.get().setSubcatid(subcatid);
			OccasionalWear occasionupdate = this.occasionwearRepository.save(occasionsget.get());

			if (occasionupdate.getOccasionid() == occasionid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());

				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
		} else {
			throw new OccasionidNotFoundException(ConstantValues.OCCASIONID_NOT_FOUND);
		}
	}}

}
