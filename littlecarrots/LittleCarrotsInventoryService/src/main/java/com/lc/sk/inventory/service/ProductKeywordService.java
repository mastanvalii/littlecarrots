package com.lc.sk.inventory.service;


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
import com.lc.sk.inventory.entities.ProductKeywords;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.ProductKeywordsException;
import com.lc.sk.inventory.repositories.ProductKeywordsRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class ProductKeywordService {

	@Autowired
	ProductKeywordsRepository productkeywordsRepository;
	

	
	
	public ProductKeywordService() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/* product keywords details by productid */
	@GetMapping(path = URLMapping.PRODUCT_KEYWORDS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<ProductKeywords>> getById(@PathVariable long productid) {
		Optional<ProductKeywords> ProdKeywords = this.productkeywordsRepository.getProductKeywordsbypid(productid);
		if (ProdKeywords.isPresent()) {
			
			return new ResponseEntity<Optional<ProductKeywords>>(ProdKeywords,   HttpStatus.ACCEPTED);
			
		} else {			
			throw new ProductKeywordsException(ConstantValues.PRODUCT_KEYWORDS_LIST_NOT_FOUND_GIVEN_PID);
		}
	}
	
	
	

	// insertion
	@PostMapping(path = URLMapping.PRODUCT_KEYWORDS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(
	        @RequestParam(name = ConstantValues. PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.KEYWORDS, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String keywords)
			 {
		ResponseBean responseBean = new ResponseBean();
		if (productid == Long.parseLong(ConstantValues.DEFAULT_INT) || keywords.equals(ConstantValues.DEFAULT_STRING)) {
			
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			ProductKeywords ProdKeywords = this.productkeywordsRepository.save(new ProductKeywords(productid, keywords));
			if (ProdKeywords.getKeywords().equals(keywords)) {
				responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());

				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}
	}

	// update
	@PutMapping(path = URLMapping.PRODUCT_KEYWORDS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(
			@PathVariable long productid,
			//@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.KEYWORDS, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String keywords)
			 {
		
		if (productid == Long.parseLong(ConstantValues.DEFAULT_INT) || keywords.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
		Optional<ProductKeywords> ProdKeywordsget = this.productkeywordsRepository.getProductKeywordsbypid(productid);
		
		if (ProdKeywordsget.isPresent()) {
			ProdKeywordsget.get().setKeywords(keywords);
			
			ProductKeywords ProdKeywordsupdate = this.productkeywordsRepository.save(ProdKeywordsget.get());

			if (ProdKeywordsupdate.getProductid() == productid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());

				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
		} else {
			throw new ProductKeywordsException(ConstantValues.PRODUCT_KEYWORDS_LIST_NOT_FOUND_GIVEN_PID);
		}
	}}
	
	
	
	
	
	
	
	
	
}
