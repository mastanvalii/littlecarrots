package com.little.carrots.order.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.bean.ResponseBean;



import com.little.carrots.order.util.SecurityHttpStatus;

import com.little.carrots.order.exception.subexceptions.CartmanagementException;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;

import com.little.carrots.order.entity.Cartmanagement;
import com.little.carrots.order.repositories.CartmanagementRepository;
import com.little.carrots.order.util.URLMapping;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class CartmanagementService {
	@Autowired
	CartmanagementRepository cartmanagrepo;
	
	
	
	@GetMapping(path = URLMapping.CART_MANAGEMENT_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<List<Cartmanagement>> getAllCart() {
		List<Cartmanagement> cart = (List<Cartmanagement>) cartmanagrepo.getAllCart();
		if (!cart.isEmpty()) {
			return new ResponseEntity<>(cart,  HttpStatus.ACCEPTED);

		} else {
			throw new CartmanagementException(ConstantValues.CART_NOT_FOUND);
		}

	}
	
	@GetMapping(path = URLMapping.CARTMANAGEMENT_MAPPING_PATH_WITH_PATH_VARIABLE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Cartmanagement>> getCartById(@PathVariable long cartid) {
		Optional<Cartmanagement> cart = cartmanagrepo.getCartById(cartid);
		if (!cart.isPresent()||cart.get()==null) {
			
			throw new CartmanagementException(ConstantValues.CART_NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Cartmanagement>>(cart,  HttpStatus.ACCEPTED);
		}

	}
	
	@GetMapping(path=URLMapping.CARTMANAGEMENT_MAPPING_PATH_VARIABLE_CUSTOMERID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Cartmanagement>> getByCustomerId(@PathVariable long customerid) {
		List<Cartmanagement> cart = cartmanagrepo.getByCustomerId(customerid);
		if (!cart.isEmpty()) {
			return new ResponseEntity<List<Cartmanagement>>(cart,  HttpStatus.ACCEPTED);
			
		} else {
			
			throw new CartmanagementException(ConstantValues.CART_NOT_FOUND);
		}
	}
	
//	@PostMapping(path = URLMapping.CART_MANAGEMENT_MAPPING_PATH)
//	@ResponseBody	
//	public ResponseEntity<ResponseBean> addList(
//			@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long customerid,
//			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
//			@RequestParam(name = ConstantValues.PRODUCT_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) double productprice,
//			@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long quantity,
//
//			@RequestParam(name = ConstantValues.CART_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) Date cartdate,
//			@RequestParam(name = ConstantValues.TOTAL_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long totalprice
//			) {
//		if (customerid == Long.parseLong(ConstantValues.DEFAULT_INT) 
//				|| productid == Long.parseLong(ConstantValues.DEFAULT_INT)
//				|| productprice == Long.parseLong(ConstantValues.DEFAULT_FLOAT)
//				|| quantity == Long.parseLong(ConstantValues.DEFAULT_INT)
//				||cartdate.equals(ConstantValues.DEFAULT_STRING)
//				|| totalprice == Long.parseLong(ConstantValues.DEFAULT_INT)) {
//			throw new NullRequestRecievedException(ConstantValues.RECEIVED_NULL_VALUES);
//		}
//		else {
//			
//		ResponseBean responseBean = new ResponseBean();
//		Cartmanagement cart=cartmanagrepo.save(new Cartmanagement(customerid,productid,productprice,quantity,cartdate,totalprice));
//		if( cart.getCustomerid()==(customerid))
//		{
//			responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
//			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
//			responseBean.setTiemstamp(System.currentTimeMillis());
//			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
//		}else {
//			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
//		}
//		}
//	}
	
	@DeleteMapping(path=URLMapping.CARTMANANGEMENT_BY_CARTID)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteByCartid(@PathVariable long cartid)
	{
		ResponseBean response=new ResponseBean();
		Optional<Cartmanagement> cart = cartmanagrepo.deleteByCartid(cartid);
		if(cart.isPresent())
		{
			cartmanagrepo.delete(cart.get());
		response.setMessage(ConstantValues.CARTMANAGEMENT_CARTID);
		response.setResponsecode(SecurityHttpStatus.ACCEPTED);
		response.setTiemstamp(System.currentTimeMillis());
		return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		}
		else
		{
			throw new CartmanagementException(ConstantValues.CART_NOT_FOUND);
		}
	}
	
	@DeleteMapping(path=URLMapping.CARTMANAGEMENT_BY_CUSTOMERID)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteByProductid(@PathVariable long cartid,@PathVariable long productid,@PathVariable long customerid)
	{
		ResponseBean response=new ResponseBean();
		Optional<Cartmanagement> cart = cartmanagrepo.deleteByCustomerid(cartid,productid,customerid);
		if(cart.isPresent())
		{
			cartmanagrepo.delete(cart.get());
			response.setMessage(ConstantValues.CARTMANAGEMENT_PRODUCTID);
			response.setResponsecode(SecurityHttpStatus.ACCEPTED);
			response.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
		}
		else
		{
			throw new CartmanagementException(ConstantValues.CART_NOT_FOUND);
		}
	}
	
	@PutMapping(path = URLMapping.CART_MANAGEMENT_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateQty(@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long customerid,
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.PRODUCT_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) double productprice,
			@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long quantity,
			@RequestParam(name = "cartid", required = true, defaultValue = ConstantValues.DEFAULT_INT)long cartid,
			@RequestParam(name = ConstantValues.TOTAL_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long totalprice
			){
				ResponseBean response=new ResponseBean();				
				Optional<Cartmanagement> cart = cartmanagrepo.deleteByCustomerid(cartid,productid,customerid);
				if(cart.isPresent())
				{
					cart.get().setQuantity(quantity);
					cart.get().setTotalprice(totalprice);
					cartmanagrepo.save(cart.get());
					response.setMessage("Cart Quantity Updated");
					response.setResponsecode(SecurityHttpStatus.ACCEPTED);
					response.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
				}
				else {
					throw new CartmanagementException(ConstantValues.CART_NOT_FOUND);
				}
			}
	@PostMapping(path = URLMapping.CART_MANAGEMENT_MAPPING_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name = ConstantValues.CUSTOMER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long customerid,
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.PRODUCT_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) double productprice,
			@RequestParam(name = ConstantValues.QUANTITY, required = true, defaultValue = ConstantValues.DEFAULT_INT) long quantity,

			//@RequestParam(name = ConstantValues.CART_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String cartdate,
			@RequestParam(name = ConstantValues.TOTAL_PRICE, required = true, defaultValue = ConstantValues.DEFAULT_INT) long totalprice
			) {
		if (customerid == Long.parseLong(ConstantValues.DEFAULT_INT) 
				|| productid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| productprice == Long.parseLong(ConstantValues.DEFAULT_FLOAT)
				|| quantity == Long.parseLong(ConstantValues.DEFAULT_INT)
			//	||cartdate.equals(ConstantValues.DEFAULT_STRING)
				|| totalprice == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			System.err.println(customerid);
			System.err.println(productid);
			System.err.println(productprice);
			System.err.println(quantity);
			System.err.println(totalprice);
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else {
			/*java.sql.Timestamp dateinsertion = new java.sql.Timestamp(new java.util.Date().getTime());
			Date date1 = null;
			try {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(cartdate);
				
			} catch (ParseException e) {
				
				date1 = Date.from(Instant.parse(cartdate));
				
			}
			java.sql.Timestamp cartdate1 = new java.sql.Timestamp(date1.getTime());
			*/
		ResponseBean responseBean = new ResponseBean();
		java.sql.Timestamp cartdate = new java.sql.Timestamp(new java.util.Date().getTime());

		Cartmanagement cart=cartmanagrepo.save(new Cartmanagement(customerid,productid,productprice,quantity,cartdate,totalprice));
		if( cart.getCustomerid()==(customerid))
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
}
