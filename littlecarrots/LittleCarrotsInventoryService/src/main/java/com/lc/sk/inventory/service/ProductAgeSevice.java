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
import com.lc.sk.inventory.entities.ProductAge;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.ProductAgeNotFoundException;
import com.lc.sk.inventory.repositories.ProductAgeRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class ProductAgeSevice {
	@Autowired
	ProductAgeRepository productAgeRepository;
	

	
	public ProductAgeSevice() {
		
	}
	
	// Get all Productages 
	@GetMapping(path = URLMapping.PRODUCTAGE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductAge>> getAllProductAgeDetails() {
		List<ProductAge> productages = (List<ProductAge>) this.productAgeRepository.getallages();

		if (productages.isEmpty()) {
			throw new ProductAgeNotFoundException(ConstantValues.NO_PRODUCTAGES_LIST_FOUND);
		} else {
			return new ResponseEntity<List<ProductAge>>(productages,  HttpStatus.ACCEPTED);
		}

	}
	
	// Get by ageid
	@GetMapping(path=URLMapping.PRODUCTAGE_PATH_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<ProductAge>> getProductAgeById(@PathVariable String ageid)
	{
		Optional<ProductAge> productages = this.productAgeRepository.getbyageid(ageid);
		if(productages.isPresent())
		{
			return new ResponseEntity<Optional<ProductAge>>(productages,  HttpStatus.ACCEPTED);
		}
		else
		{
			throw new ProductAgeNotFoundException(ConstantValues.NO_PRODUCTAGES_LIST_FOUND);
		}
	}
	
	
	//insertion 
		@PostMapping(path = URLMapping.PRODUCTAGE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody	
		public ResponseEntity<ResponseBean> addList(
				@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING)String ageid,
				@RequestParam(name = ConstantValues.DES, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String des) {
			ResponseBean responseBean = new ResponseBean();
			if(ageid.equals(ConstantValues.DEFAULT_STRING)||des.equals(ConstantValues.DEFAULT_STRING)) {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			}else {
				ProductAge productage=this.productAgeRepository.save(new ProductAge(ageid,des));
				if(productage.getAgeid().equals(ageid))
				{
					responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					responseBean.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.ACCEPTED);
				}
				else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				}
				}
			}
		
		//update
				@PutMapping(path=URLMapping.PRODUCTAGE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public ResponseEntity<ResponseBean> update(
						@RequestParam(name = ConstantValues.DES, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String des,
						@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING)String ageid){
					if(ageid.equals(ConstantValues.DEFAULT_STRING)||des.equals(ConstantValues.DEFAULT_STRING)) {
						throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
					}else {
					 Optional<ProductAge> getproductages = this.productAgeRepository.findById(ageid);
					 if(getproductages.isPresent())
					  {
						 getproductages.get().setDes(des);
						  ProductAge updateproductages=this.productAgeRepository.save(getproductages.get());
						  
						  if (updateproductages.getAgeid() == ageid)
						  {
								ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
								return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
							}
						  else 
						  {
								throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
						  }  
					  }
					  else
						{
						  throw new ProductAgeNotFoundException(ConstantValues.NO_PRODUCTAGES_LIST_FOUND);
						}
				}}
	
}
