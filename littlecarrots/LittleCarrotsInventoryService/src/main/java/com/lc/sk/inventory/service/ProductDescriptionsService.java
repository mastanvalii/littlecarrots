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
import com.lc.sk.inventory.entities.ProductDescriptions;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.ProductDescriptionsNotFoundException;
import com.lc.sk.inventory.repositories.ProductDescriptionsRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path=URLMapping.INVENTORY_ROOT_PATH)

public class ProductDescriptionsService {

	@Autowired
	ProductDescriptionsRepository productdescriptionrepository;
	

	
	public ProductDescriptionsService() {
		
	}
	
	
	
	//Get all ProductDescriptions
	@GetMapping (path = URLMapping.PRODUCTDESCRIPTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductDescriptions>> getAllProductDescriptions(){
		List<ProductDescriptions> productdescriptions= (List<ProductDescriptions>)this.productdescriptionrepository.getalldesc();
		if(productdescriptions.isEmpty()) {
			throw new ProductDescriptionsNotFoundException(ConstantValues.NO_PRODUCTDESCRIPTIONS_LIST_FOUND);
		}
		else {
			return new ResponseEntity<List<ProductDescriptions>>(productdescriptions, HttpStatus.ACCEPTED);
		}
	}
	
	//Get by id
	@GetMapping(path=URLMapping.PRODUCTDESCRIPTIONS_PATH_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<ProductDescriptions>> getDescriptionsid(@PathVariable long  descriptionid)
	{
		Optional<ProductDescriptions> productdescriptions = this.productdescriptionrepository.getdescbyid(descriptionid);
		if(productdescriptions.isPresent())
		{
			return new ResponseEntity<Optional<ProductDescriptions>>(productdescriptions,   HttpStatus.ACCEPTED);
		}
		else
		{
			throw new ProductDescriptionsNotFoundException(ConstantValues.NO_PRODUCTDESCRIPTIONS_LIST_FOUND);
		}
	}
	
	//Insertion
	@PostMapping (path = URLMapping.PRODUCTDESCRIPTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name=ConstantValues.DESCRIPTION,required=true,defaultValue=ConstantValues.DEFAULT_STRING)String description){
		
		if(description.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else {
		ResponseBean responseBean=new ResponseBean();
		ProductDescriptions productdescriptions=this.productdescriptionrepository.save(new ProductDescriptions(description));
		if(productdescriptions.getDescription().equals(description))
		{
			responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		}}
	}
	
	//update
		@PutMapping (path=URLMapping.PRODUCTDESCRIPTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<ResponseBean> update(
				@RequestParam(name=ConstantValues.DESCRIPTIONID,required=true,defaultValue=ConstantValues.DEFAULT_INT)long descriptionid,
				@RequestParam(name=ConstantValues.DESCRIPTION,required=true,defaultValue=ConstantValues.DEFAULT_STRING)String description){
			if(descriptionid == Long.parseLong(ConstantValues.DEFAULT_INT)|| description.equals(ConstantValues.DEFAULT_STRING)) {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			}
			else {
			Optional<ProductDescriptions> productdescriptionsget = this.productdescriptionrepository.findById(descriptionid);
			if(productdescriptionsget.isPresent())
			{
				productdescriptionsget.get().setDescriptionid(descriptionid);
				productdescriptionsget.get().setDescription(description);
				ProductDescriptions productdescriptionsupdate=this.productdescriptionrepository.save(productdescriptionsget.get());
				if (productdescriptionsupdate.getDescriptionid() == descriptionid)
				  {
						ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
						return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
					}
				  else 
				  {
						throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				  }  
			  }
			  else
				{
					throw new ProductDescriptionsNotFoundException(ConstantValues.DESCRIPTIONID_NOT_FOUND);
				}
				
			}
		}
			
		@GetMapping(path=URLMapping.PRODUCTDESCRIPTIONS_PATH_NOT_IN_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<ProductDescriptions>> getProductDescriptionNotInProduct(){
			return new ResponseEntity<List<ProductDescriptions>>(this.productdescriptionrepository.getProductDescriptionsWhichNotInProductTable(),   HttpStatus.ACCEPTED);
		}

	}