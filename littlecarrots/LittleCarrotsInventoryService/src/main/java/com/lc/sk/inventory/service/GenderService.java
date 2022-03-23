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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.entities.Genders;
import com.lc.sk.inventory.exception.subexception.GendersException;
import com.lc.sk.inventory.repositories.GendersRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class GenderService {

	@Autowired
	GendersRepository genderRepository;



	public GenderService() {

	}

	/**
	 * @param genderid
	 * @param gender
	 */
//get all genders list
	@GetMapping(path = URLMapping.GENDERS_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Genders>> getAllGendersDetails() {
		List<Genders> genders = (List<Genders>) this.genderRepository.getAllGenders();

		if (genders.isEmpty()) {
			throw new GendersException(ConstantValues.NO_GENDERS_LIST_FOUND);
		} else {
			return new ResponseEntity<List<Genders>>(genders,  HttpStatus.ACCEPTED);
		}

	}
//get genders details by id

	@GetMapping(path = URLMapping.GENDERS_MAPPING_PATH_WITH_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Genders>> getGenderById(@PathVariable String genderid) {
		Optional<Genders> genders = this.genderRepository.getGendersById(genderid);
		if (genders.isPresent()) {

			return new ResponseEntity<Optional<Genders>>(genders,  HttpStatus.ACCEPTED);
		} else {
			throw new GendersException(ConstantValues.NO_GENDERS_LIST_FOUND);
		}
	}

}
