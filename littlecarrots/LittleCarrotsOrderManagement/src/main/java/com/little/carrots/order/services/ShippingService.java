package com.little.carrots.order.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.entity.Shipping;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;
import com.little.carrots.order.exception.subexceptions.ShippingNotFoundException;
import com.little.carrots.order.repositories.ShippingRepository;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.URLMapping;

@Validated
@RestController
@RequestMapping(path=URLMapping.ORDER_ROOT_PATH)
public class ShippingService {

	
	@Autowired
	ShippingRepository shippingRepository;
	
	
	@PostMapping(path=URLMapping.SHIPPING,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertshipping(
			@RequestParam(name=ConstantValues.ORDER_ID,required = true, defaultValue = ConstantValues.INTEGER) long orderid,
			@RequestParam(name=ConstantValues.CUSTOMER_ID,required = true, defaultValue = ConstantValues.INTEGER) long customerid,
			//@RequestParam(name=ConstantValues.STATUS,required = true, defaultValue = ConstantValues.INTEGER)boolean status,
			@RequestParam(name=ConstantValues.SHIPPING_STATUS,required = true, defaultValue = ConstantValues.INTEGER)String shippingstatus,
			@RequestParam(name=ConstantValues.COURIER_ID,required = true, defaultValue = ConstantValues.INTEGER)long courierid,
			@RequestParam(name=ConstantValues.COURIER_COMPANY,required = true, defaultValue = ConstantValues.INTEGER)String couriercompany,
			@RequestParam(name=ConstantValues.SHIPPING_DATE,required = true, defaultValue = ConstantValues.INTEGER)String shippingdate,
			@RequestParam(name=ConstantValues.DELIVERY_DATE,required = true, defaultValue = ConstantValues.INTEGER)String deliverydate)
	{
		if( orderid==Long.parseLong("0") || 
				customerid == Long.parseLong("0") 
				|| shippingstatus.equals(0) || courierid==Long.parseLong("0") 
				|| couriercompany.equals(0) || shippingdate.equals(0) || deliverydate.equals(0)
				)
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else
		{
			ResponseBean response=new ResponseBean();
			Date shippingdate1=null;
			Date deliverydate1=null;
			try {
				shippingdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(shippingdate);
				deliverydate1 = new SimpleDateFormat("dd/MM/yyyy").parse(deliverydate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Timestamp shippingdate2 = new java.sql.Timestamp(shippingdate1.getTime());
			java.sql.Timestamp deliverydate2 = new java.sql.Timestamp(deliverydate1.getTime());
			Shipping ship=new Shipping(orderid,customerid,shippingstatus,courierid,couriercompany,shippingdate2,deliverydate2);
			shippingRepository.save(ship);
			if(ship.getCourierid()==courierid)
			{
				response.setMessage("Data inserted in DB");
				response.setResponsecode(2000);
				response.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
			}
			else
			{
				throw new DBInsertException("DB insert exception");
			}
		}
	}
	
	
	@PutMapping(path=URLMapping.SHIPPING)
	@ResponseBody
	public ResponseEntity<ResponseBean> putmethod(
			@RequestParam(name=ConstantValues.SHIPPING_ID,required = true, defaultValue = ConstantValues.INTEGER) long shippingid,
			@RequestParam(name=ConstantValues.ORDER_ID,required = true, defaultValue = ConstantValues.INTEGER) long orderid,
			@RequestParam(name=ConstantValues.CUSTOMER_ID,required = true, defaultValue = ConstantValues.INTEGER) long customerid,
			//@RequestParam(name=ConstantValues.STATUS,required = true, defaultValue = ConstantValues.INTEGER)boolean status,
			@RequestParam(name=ConstantValues.SHIPPING_STATUS,required = true, defaultValue = ConstantValues.INTEGER)String shippingstatus,
			@RequestParam(name=ConstantValues.COURIER_ID,required = true, defaultValue = ConstantValues.INTEGER)long courierid,
			@RequestParam(name=ConstantValues.COURIER_COMPANY,required = true, defaultValue = ConstantValues.INTEGER)String couriercompany,
			@RequestParam(name=ConstantValues.SHIPPING_DATE,required = true, defaultValue = ConstantValues.INTEGER)String shippingdate,
			@RequestParam(name=ConstantValues.DELIVERY_DATE,required = true, defaultValue = ConstantValues.INTEGER)String deliverydate)
	{
		if( orderid==Long.parseLong("0") || 
				customerid == Long.parseLong("0") 
				|| shippingstatus.equals("0") || courierid==Long.parseLong("0") 
				|| couriercompany.equals("0") || shippingdate.equals("0") || deliverydate.equals("0")
				)
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else
		{
			ResponseBean response=new ResponseBean();
			Optional<Shipping> ship=shippingRepository.findById(shippingid);
			Date shippingdate1=null;
			Date deliverydate1=null;
			try {
				shippingdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(shippingdate);
				deliverydate1 = new SimpleDateFormat("dd/MM/yyyy").parse(deliverydate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			java.sql.Timestamp shippingdate2 = new java.sql.Timestamp(shippingdate1.getTime());
			java.sql.Timestamp deliverydate2 = new java.sql.Timestamp(deliverydate1.getTime());
			if(ship.isPresent())
			{
			ship.get().setCouriercompany(couriercompany);
			ship.get().setCourierid(courierid);
			ship.get().setCustomerid(customerid);
			ship.get().setDeliverydate(deliverydate2);
			ship.get().setShippingdate(shippingdate2);;
			ship.get().setShippingid(shippingid);
			ship.get().setShippingstatus(shippingstatus);
			//ship.get().setStatus(status);
			Shipping ship2=shippingRepository.save(ship.get());
			if(ship2.getShippingid()==shippingid)
			{
				response.setMessage(ConstantValues.DATA_UPDATED_IN_DB);
				response.setResponsecode(2000);
				response.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(response, HttpStatus.ACCEPTED);
			}
			else
			{
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
			}
			else
			{
				throw new ShippingNotFoundException(ConstantValues.SHIPPING_ID_NOT_FOUND);
			}
		}
	}
	
	
	
	@GetMapping(path=URLMapping.SHIPPING,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Shipping>> getall()
	{
		List<Shipping> ship=(List<Shipping>) shippingRepository.getAllShipping();
		if(!ship.isEmpty())
		{
			return new ResponseEntity<List<Shipping>>(ship,HttpStatus.ACCEPTED);
		}
		else
		{
			throw new ShippingNotFoundException(ConstantValues.SHIPPING_DATA_NOT_FOUND);
		}
	}
	
	@GetMapping(path=URLMapping.SHIPPING_BY_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Shipping>> getByShippingId(@PathVariable long shippingid)
	{
		Optional<Shipping> ship=shippingRepository.findById(shippingid);
		if(ship.isPresent())
		{
			return new ResponseEntity<Optional<Shipping>>(ship,HttpStatus.ACCEPTED);
		}
		else
		{
			throw new ShippingNotFoundException(ConstantValues.SHIPPING_ID_NOT_FOUND);
		}
	}
	
	@GetMapping(path=URLMapping.SHIPPING_BY_CUSTOMER_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Shipping>> getByCustomerId(@PathVariable long customerid)
	{
		Optional<Shipping> ship=shippingRepository.getByCustomerId(customerid);
		if(ship.isPresent())
		{
			return new ResponseEntity<Optional<Shipping>>(ship,HttpStatus.ACCEPTED);
		}
		else
		{
			throw new ShippingNotFoundException(ConstantValues.CUSTOMER_ID_NOT_FOUND);
		}
	}
	
	@GetMapping(path=URLMapping.SHIPPING_BY_COURIER_ID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Shipping>> getByCourierId(@PathVariable long courierid)
	{
		Optional<Shipping> ship=shippingRepository.getByCourierId(courierid);
		if(ship.isPresent())
		{
			return new ResponseEntity<Optional<Shipping>>(ship,HttpStatus.ACCEPTED);
		}
		else
		{
			throw new ShippingNotFoundException(ConstantValues.COURIER_ID_NOT_FOUND);
		}
	}
	
	

}
