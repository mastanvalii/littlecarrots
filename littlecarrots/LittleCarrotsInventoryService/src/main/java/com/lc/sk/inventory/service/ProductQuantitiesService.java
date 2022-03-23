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

import com.lc.sk.inventory.entities.ProductQuantities;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.ProductQuantityException;
import com.lc.sk.inventory.repositories.ProductQuantitiesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)

public class ProductQuantitiesService {

	@Autowired
	ProductQuantitiesRepository productquantitiesRepository;



	public ProductQuantitiesService() {

	}

	// Get all ProductQuantities
	@GetMapping(path = URLMapping.PRODUCTQUANTITIES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductQuantities>> getAllProductCustidDetails() {
		List<ProductQuantities> getproductqty = (List<ProductQuantities>) this.productquantitiesRepository.getallcustid();

		if (getproductqty.isEmpty()) {
			throw new ProductQuantityException(ConstantValues.NO_PRODUCTQUANTITIES_LIST_FOUND);
		} else {
			return new ResponseEntity<List<ProductQuantities>>(getproductqty,  
					HttpStatus.ACCEPTED);
		}

	}

	// Get by custid
	@GetMapping(path = URLMapping.PRODUCTQUANTITIES_PATH_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<ProductQuantities>> getProductCustById(@PathVariable long custid) {
		Optional<ProductQuantities> getproductqty = this.productquantitiesRepository.getcustid(custid);
		if (getproductqty.isPresent()) {
			return new ResponseEntity<Optional<ProductQuantities>>(getproductqty,  
					HttpStatus.ACCEPTED);
		} else {
			throw new ProductQuantityException(ConstantValues.NO_PRODUCTQUANTITIES_LIST_FOUND);
		}
	}

	// insertion
	@PostMapping(path = URLMapping.PRODUCTQUANTITIES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long quantity,
			@RequestParam(name = ConstantValues.SIZEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long sizeid) {
		ResponseBean responseBean = new ResponseBean();

		if (quantity == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| sizeid == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			ProductQuantities postproductqty = this.productquantitiesRepository
					.save(new ProductQuantities(quantity, sizeid));

			if (postproductqty.getSizeid() == sizeid) {
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
	@PutMapping(path = URLMapping.PRODUCTQUANTITIES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> update(
			@RequestParam(name = ConstantValues.CUSTID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long custid,
			@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long quantity,
			@RequestParam(name = ConstantValues.SIZEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long sizeid) {
		if (custid == Long.parseLong(ConstantValues.DEFAULT_INT)
				// ||quantity == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| sizeid == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			// ResponseBean responseBean = new ResponseBean();
			Optional<ProductQuantities> updateproductqty = this.productquantitiesRepository.findById(custid);
			if (updateproductqty.isPresent()) {
				updateproductqty.get().setQuantity(quantity);
				updateproductqty.get().setSizeid(sizeid);
				ProductQuantities updateproductqty1 = this.productquantitiesRepository.save(updateproductqty.get());
				if (updateproductqty1.getCustid() == custid) {
					ResponseBean responseBean1 = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
							SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean1,   HttpStatus.ACCEPTED);
				} else {
					throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				}

			} else {
				throw new ProductQuantityException(ConstantValues.NO_PRODUCTQUANTITIES_LIST_FOUND);
			}
		}
	}

	@GetMapping(path = URLMapping.PRODUCTQUANTITIES_PATH1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductQuantities>> getAllProductDescriptionNotInProducts() {
		List<ProductQuantities> getproductqty = (List<ProductQuantities>) this.productquantitiesRepository
				.getProductQuantityWhichNotInProductTable();

		if (getproductqty.isEmpty()) {
			throw new ProductQuantityException(ConstantValues.NO_PRODUCTQUANTITIES_LIST_FOUND);
		} else {
			return new ResponseEntity<List<ProductQuantities>>(getproductqty,  
					HttpStatus.ACCEPTED);
		}

	}

	// update
	@PutMapping(path = URLMapping.PRODUCTQUANTITIES_PATH1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateOnlyQuantity(
			@RequestParam(name = ConstantValues.CUSTID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long custid,
			@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long quantity)
	// @RequestParam(name = ConstantValues.SIZEID, required = true, defaultValue =
	// ConstantValues.DEFAULT_INT) long sizeid) {

	{
		if (custid == Long.parseLong(ConstantValues.DEFAULT_INT))

		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			// ResponseBean responseBean = new ResponseBean();
			Optional<ProductQuantities> updateproductqty = this.productquantitiesRepository.findById(custid);
			if (updateproductqty.isPresent() && updateproductqty.get().getQuantity() >= quantity) {
				updateproductqty.get().setQuantity(updateproductqty.get().getQuantity() - quantity);
				ProductQuantities updateproductqty1 = this.productquantitiesRepository.save(updateproductqty.get());
				if (updateproductqty1.getCustid() == custid) {
					ResponseBean responseBean1 = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
							SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean1,   HttpStatus.ACCEPTED);
				} else {
					throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				}

			} else {
				throw new ProductQuantityException(ConstantValues.NO_PRODUCTQUANTITIES_LIST_FOUND);
			}
		}
	}

}
