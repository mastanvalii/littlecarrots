/**
 * 
 */
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Countries;
import com.lc.sk.inventory.exception.subexception.CountriesNotFoundException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.repositories.CountriesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class CountriesService {

	@Autowired
	private CountriesRepository countriesRepository;



	public CountriesService() {
		super();
	}

	// get all countries list
	@GetMapping(path = URLMapping.COUNTRY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Countries>> getAllCountryDetails() {
		List<Countries> countries = (List<Countries>) this.countriesRepository.getAllCountries();

		if (countries.isEmpty()) {
			throw new CountriesNotFoundException(ConstantValues.NO_COUNTRIES_LIST_FOUND);
		} else {

			return new ResponseEntity<List<Countries>>(countries,  HttpStatus.ACCEPTED);
		}

	}

	// get country details by id
	@GetMapping(path = URLMapping.COUNTRY_PATH_WITH_COUNTRY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Countries>> getCountryById(@PathVariable String isocode) {
		Optional<Countries> country = this.countriesRepository.getCountryById(isocode);
		if (country.isPresent()) {

			return new ResponseEntity<Optional<Countries>>(country,  HttpStatus.ACCEPTED);
		} else {
			throw new CountriesNotFoundException(ConstantValues.NO_COUNTRIES_LIST_FOUND_GIVEN_ISOCODE);
		}
	}

	// enable new country to status 1
	@PutMapping(path = URLMapping.UPDATE_COUNTRY_STATUS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> enableCountry(@PathVariable String isocode, @PathVariable boolean status) {
		ResponseBean responseBean = new ResponseBean();
		
		Optional<Countries> country = this.countriesRepository.getCountryById(isocode);
		if(country.isPresent())
		{
		country.get().setStatus(status);
		
		Countries tempCountry = this.countriesRepository.save(country.get());
		if (tempCountry.getIsocode().equals(isocode) && tempCountry.getStatus() == status) {
			responseBean.setMessage(ConstantValues.COUNTRY_STATUS_UPDATE_SUCCESS);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());

		} else {
			throw new DBInsertException(ConstantValues.COUNTRY_STATUS_UPDATE_FAIL + isocode);
		}

		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}
		else
		{
		throw new CountriesNotFoundException(ConstantValues.COUNTRY_NOT_FOUND);
		
		}
	}

	// get all active countries list
	@GetMapping(path = URLMapping.GET_COUNTRIES_ENABLED_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Countries>> getAllActiveCountryDetails() {
		List<Countries> activeCountries = (List<Countries>) this.countriesRepository.getActiveCountries();
			return new ResponseEntity<List<Countries>>(activeCountries,  HttpStatus.ACCEPTED);

	}

}
