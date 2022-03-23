package com.little.carrots.order.services;


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


import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.entity.Packageshipping;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;

import com.little.carrots.order.exception.subexceptions.PackageShippingNotFoundException;
import com.little.carrots.order.repositories.PackageshippingRepository;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.SecurityHttpStatus;
import com.little.carrots.order.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class PackageShippingService {

	@Autowired
	PackageshippingRepository packageshippingRepo;
	

	
	//get Package shipping 
	@GetMapping(path=URLMapping.GET_PACKAGE_SHIIPING,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Packageshipping>> getAll() {
		List<Packageshipping> packageShipping = (List<Packageshipping>) packageshippingRepo.getPackageshippingList();
		if (!packageShipping.isEmpty()) {
			return new ResponseEntity<List<Packageshipping>>(packageShipping,  HttpStatus.ACCEPTED);
		} else {
			throw new PackageShippingNotFoundException(ConstantValues.PACKAGE_SHIPPING_NOT_FOUND);
		}
	}
		
	//get by sid from Package shipping 
	
	
	@GetMapping(path=URLMapping.GET_PACKAGE_SHIIPING_BY_SID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Packageshipping>> getSID(@PathVariable long sid) {
		Optional<Packageshipping> packageShipping = packageshippingRepo.getPackageshippingListBySid(sid);
				
		if (!packageShipping.isPresent()) {
			throw new PackageShippingNotFoundException(ConstantValues.PACKAGE_SHIPPING_NOT_FOUND);
			
			
		} else {
			
			return new ResponseEntity<Optional<Packageshipping>>(packageShipping,  HttpStatus.ACCEPTED);
		}
	}
	
	//get by orderitemsid
	
	@GetMapping(path=URLMapping.GET_PACKAGE_SHIIPING_BY_ORDERITEMSID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Packageshipping>> getOrderByOrderItemsId(@PathVariable long orderitemsid) {
		Optional<Packageshipping> packageShipping = packageshippingRepo.getPackageshippingListByOrderItemsID(orderitemsid);	
		if (!packageShipping.isPresent()) {
			throw new PackageShippingNotFoundException(ConstantValues.PACKAGE_SHIPPING_NOT_FOUND);
			
			
		} else {
			
			return new ResponseEntity<Optional<Packageshipping>>(packageShipping,  HttpStatus.ACCEPTED);
		}
	}
	
	//get by orderid
	
		@GetMapping(path=URLMapping.GET_PACKAGE_SHIIPING_BY_ORDERID,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<Packageshipping>> getOrderId(@PathVariable long orderid) {
			List<Packageshipping> packageShipping = packageshippingRepo.getPackageshippingListByOrderID(orderid);	
			if (packageShipping.isEmpty()) {
				throw new PackageShippingNotFoundException(ConstantValues.PACKAGE_SHIPPING_NOT_FOUND);
				
				
			} else {
				
				return new ResponseEntity<List<Packageshipping>>(packageShipping,  HttpStatus.ACCEPTED);
			}
		}
	
	@PostMapping(path=URLMapping.GET_PACKAGE_SHIIPING,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> newPackage(
			@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderid,
			@RequestParam(name = ConstantValues.ORDER_ITEMSID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderitemsid,
			@RequestParam(name = ConstantValues.SHIPPING_ORDERID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String shippingorderid,
			@RequestParam(name = ConstantValues.SHIPPING_SERVICE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String shippingservice,
			@RequestParam(name = ConstantValues.AWB, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String awb,
			@RequestParam(name = ConstantValues.TRACKING_URL, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String trackingurl,
			@RequestParam(name = ConstantValues.INFORMATION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String information
			){
		ResponseBean responseBean = new ResponseBean();
		if(orderid == Long.parseLong(ConstantValues.DEFAULT_INT)
				||orderitemsid == Long.parseLong(ConstantValues.DEFAULT_INT)
				||shippingorderid.equals(ConstantValues.DEFAULT_STRING)
				||shippingservice.equals(ConstantValues.DEFAULT_STRING)
				||awb.equals(ConstantValues.DEFAULT_STRING)
			//	||trackingurl.equals(ConstantValues.DEFAULT_STRING)
				||information.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			
		}else {
		
			Packageshipping pack =packageshippingRepo.save(new Packageshipping(orderid,orderitemsid,shippingorderid,shippingservice,awb,trackingurl,information));
			if(pack.getOrderid()==(orderid)) {
			responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			}
			else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
			}
		
	}
	
	@PutMapping(path=URLMapping.GET_PACKAGE_SHIIPING)
	@ResponseBody
	public ResponseEntity<ResponseBean> updatePackaging(
			@RequestParam(name = ConstantValues.SID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long sid,
			//@RequestParam(name = ConstantValues.ORDER_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderid,
		//	@RequestParam(name = ConstantValues.ORDER_ITEMSID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long orderitemsid,
			@RequestParam(name = ConstantValues.SHIPPING_ORDERID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String shippingorderid,
			@RequestParam(name = ConstantValues.SHIPPING_SERVICE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String shippingservice,
			@RequestParam(name = ConstantValues.AWB, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String awb,
			@RequestParam(name = ConstantValues.TRACKING_URL, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String trackingurl,
			@RequestParam(name = ConstantValues.INFORMATION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String information
			){
		if(sid == Long.parseLong(ConstantValues.DEFAULT_INT)
				//||orderitemsid == Long.parseLong(ConstantValues.DEFAULT_INT)
				||shippingorderid.equals(ConstantValues.DEFAULT_STRING)
				||shippingservice.equals(ConstantValues.DEFAULT_STRING)
				||awb.equals(ConstantValues.DEFAULT_STRING)
				||trackingurl.equals(ConstantValues.DEFAULT_STRING)
				||information.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			
		}else {
			ResponseBean response=new ResponseBean();
			Optional<Packageshipping> packageShipping = packageshippingRepo.getPackageshippingListBySid(sid);
			
			if (packageShipping.isPresent()) {
				packageShipping.get().setShippingorderid(shippingorderid);
				packageShipping.get().setShippingservice(shippingservice);
				packageShipping.get().setAwb(awb);
				packageShipping.get().setTrackingurl(trackingurl);
				packageShipping.get().setInformation(information);
				packageshippingRepo.save(packageShipping.get());
				response.setMessage("Information Updated Succesfully");
				response.setResponsecode(SecurityHttpStatus.ACCEPTED);
				response.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
			}
			else {
				throw new PackageShippingNotFoundException(ConstantValues.PACKAGE_SHIPPING_NOT_FOUND);

			}
			
			}
		
	}
}
