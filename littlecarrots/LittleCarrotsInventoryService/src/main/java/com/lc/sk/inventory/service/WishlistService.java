package com.lc.sk.inventory.service;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Wishlist;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;

import com.lc.sk.inventory.exception.subexception.WishlistNotFoundException;
import com.lc.sk.inventory.repositories.WishlistRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class WishlistService {

	@Autowired
	WishlistRepository wishlistRepository;
	

	 
	
	
	
	@PostMapping(path = URLMapping.WISHLIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public ResponseEntity<ResponseBean> addWishlist(
			@RequestParam(name = ConstantValues.CUSTOMER_ID1, required = true, defaultValue = ConstantValues.DEFAULT_INT)long customerid,
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid) {
		ResponseBean responseBean = new ResponseBean();

		if(customerid==Long.parseLong(ConstantValues.DEFAULT_INT)||productid==Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}else {
			Wishlist wishlist=this.wishlistRepository.save(new Wishlist(customerid,productid));
			if(wishlist.getCustomerid()==customerid)
			{
				responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);
			}
			else {
			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
			}
		}
	
 
	
	
	@GetMapping(path=URLMapping.WISHLIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Wishlist>> getAllWishlist()
	{
		List<Wishlist> wishlist = (List<Wishlist>) this.wishlistRepository.findAll();
		if(!wishlist.isEmpty())
		{
			return new ResponseEntity<List<Wishlist>>(wishlist,   HttpStatus.ACCEPTED);
		}
		else
		{
			throw new WishlistNotFoundException(ConstantValues.NO_WISHLIST_FOUND);
		}
	}
	
	
	
	 @GetMapping(path=URLMapping.WISHLIST_BY_CUSTOMERID, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<Wishlist>> getWishlistById(@PathVariable long customerid)
		{
		 List<Wishlist> wishlist = this.wishlistRepository.byCustomerId(customerid);
			if(wishlist.isEmpty())
			{
				throw new WishlistNotFoundException(ConstantValues.NO_WISHLIST_FOUND);
			}
			else
			{				
				return new ResponseEntity<List<Wishlist>>(wishlist,   HttpStatus.ACCEPTED);
			}
		}
	
	 	 
	

	@DeleteMapping(path=URLMapping.WISHLIST_BY_PRODUCTID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> deleteWishlistByProductId(@PathVariable long productid,@PathVariable long customerid)
	{
		ResponseBean response=new ResponseBean();
		Optional<Wishlist> wishlist = this.wishlistRepository.getByProductId(productid,customerid);
		if(wishlist.isPresent())
		{
			this.wishlistRepository.delete(wishlist.get());
			response.setMessage(ConstantValues.WISHLIST_PRODUCT);
			response.setResponsecode(SecurityHttpStatus.ACCEPTED);
			response.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(response,   HttpStatus.ACCEPTED);
		}
		else
		{
			throw new WishlistNotFoundException(ConstantValues.NO_WISHLIST_FOUND);
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

	
//	@DeleteMapping(path=URLMapping.WISHLIST_BY_CUSTOMERID)
//	@ResponseBody
//	public ResponseEntity<ResponseBean> deleteWishlistById(@PathVariable long customerid)
//	{
//     GarbageCollector.clearMemory();
//		ResponseBean response=new ResponseBean();
//		Optional<Wishlist> wishlist = wishlistRepository.byCustomerId(customerid);
//		if(wishlist.isPresent())
//		{
//		wishlistRepository.delete(wishlist.get());
//		response.setMessage(ConstantValues.WISHLIST_CUSTOMER);
//		response.setResponsecode(SecurityHttpStatus.ACCEPTED);
//		response.setTiemstamp(System.currentTimeMillis());
//		return new ResponseEntity<ResponseBean>(response, hearders.getHeader(), HttpStatus.ACCEPTED);
//		}
//		else
//		{
//			throw new WishlistNotFoundException(ConstantValues.NO_WISHLIST_FOUND);
//		}
//	}
	
	
	
	
	
//	@GetMapping(path=URLMapping.WISHLIST_BY_PRODUCTID)
//	@ResponseBody
//	public ResponseEntity<Optional<Wishlist>> getByProductId(@PathVariable long productid,@PathVariable long customerid) 
//	{
//		GarbageCollector.clearMemory();
//		Optional<Wishlist> wishlist = wishlistRepository.getByProductId(productid,customerid);
//		if(wishlist.isPresent())
//		{
//			return new ResponseEntity<Optional<Wishlist>>(wishlist, hearders.getHeader(), HttpStatus.ACCEPTED);
//		}
//		else
//		{
//			throw new WishlistNotFoundException(ConstantValues.NO_WISHLIST_FOUND);
//		}
//	}
	
	
//	@GetMapping(path=URLMapping.WISHLIST_BY_CUSTOMER_PID)
//	@ResponseBody
//	public ResponseEntity<List<Wishlist>> getByWishlistProductId(@PathVariable long productid) 
//	{
//		GarbageCollector.clearMemory();
//		List<Wishlist> wishlist = wishlistRepository.getbyWishlistProductId(productid);
//		if(!wishlist.isEmpty())
//		{
//			return new ResponseEntity<List<Wishlist>>(wishlist, hearders.getHeader(), HttpStatus.ACCEPTED);
//		}
//		else
//		{
//			throw new WishlistNotFoundException(ConstantValues.NO_WISHLIST_FOUND);
//		}
//	}
	
	
	
	    
}
