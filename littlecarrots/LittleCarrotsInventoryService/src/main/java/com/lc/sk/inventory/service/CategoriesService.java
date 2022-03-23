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
import com.lc.sk.inventory.entities.Categories;
import com.lc.sk.inventory.exception.subexception.CategoriesException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.CategoriesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)

public class CategoriesService {

	@Autowired
	CategoriesRepository categoriesRepository;
	

	
		
	public CategoriesService() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	/* Get all Categories list */
	@GetMapping(path = URLMapping.CATEGORIES_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Categories>> getAllCategories() {
		List<Categories> categories = (List<Categories>) this.categoriesRepository.getAllCategories();
		if (categories.isEmpty()) {
			throw new CategoriesException(ConstantValues.NO_CATEGORIES_LIST_FOUND);
		} else {

			return new ResponseEntity<List<Categories>>(categories,  HttpStatus.ACCEPTED);
		}

	}

	/* categories details by catid */
	@GetMapping(path = URLMapping.CATEGORIES_MAPPING_PATH_BY_CID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Categories>> getById(@PathVariable long catid) {
		@SuppressWarnings("boxing")
		Optional<Categories> categories = this.categoriesRepository.getAllCategoriesbyId(catid);
		if (!categories.isPresent()||categories.get() == null) {
			throw new CategoriesException(ConstantValues.NO_CATEGORIES_LIST_FOUND_GIVEN_CATID);
		} else {
			return new ResponseEntity<Optional<Categories>>(categories,  HttpStatus.ACCEPTED);
		}
	}
	
	
	
	//insertion 
	@PostMapping(path = URLMapping.CATEGORIES_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name = ConstantValues.CATEGORY, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String category,
			@RequestParam(name = ConstantValues.GENDER, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String gender
			) {
		if(category.equals(ConstantValues.DEFAULT_STRING )|| gender.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else {
		ResponseBean responseBean = new ResponseBean();
		Categories categories=this.categoriesRepository.save(new Categories(category,gender));
		if( categories.getCategory().equals(category))
		{
			responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
		}else {
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		}
		}
	}
	
	

	/* Categories details update by id */
	@PutMapping(path = URLMapping.CATEGORIES_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(
			@RequestParam(name = ConstantValues.CATID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long catid,
			@RequestParam(name = ConstantValues.CATEGORY, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String category,
			@RequestParam(name = ConstantValues.GENDER, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String gender
			) 
	{
		if(catid==Long.parseLong(ConstantValues.DEFAULT_INT) || category.equals(ConstantValues.DEFAULT_STRING )|| gender.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else {
		@SuppressWarnings("boxing")
		Optional<Categories> categories = this.categoriesRepository.getAllCategoriesbyId(catid);
		if (categories.isPresent()) {
			categories.get().setCategory(category);
			categories.get().setGender(gender);	
			Categories categories1 =this.categoriesRepository.save(categories.get());

			if (categories1.getCatid() == catid) {
				ResponseBean responseBean1 = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean1,  HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}

		} else {
			throw new CategoriesException(ConstantValues.NO_CATEGORIES_LIST_FOUND_GIVEN_CATID);
		}
		}
}
	
	@GetMapping(path = URLMapping.CATBGU, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Categories>> getCategoriesByGender(@PathVariable String genderid){
		return new ResponseEntity<List<Categories>>(this.categoriesRepository.getCategoriesByGenderId(genderid),  HttpStatus.ACCEPTED);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
