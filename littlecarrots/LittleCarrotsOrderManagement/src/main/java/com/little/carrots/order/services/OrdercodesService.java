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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.entity.Ordercodes;
import com.little.carrots.order.exception.subexceptions.DBValueInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;
import com.little.carrots.order.exception.subexceptions.OrderNotFoundException;
import com.little.carrots.order.repositories.OrdercodesRepository;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class OrdercodesService {
	
	
	@Autowired
	OrdercodesRepository orercodesRepository;
	

	//get all order codes
	@GetMapping(path=URLMapping.GET_ORDERCODES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Ordercodes>> getAll() {
		List<Ordercodes> orders = (List<Ordercodes>) orercodesRepository.getOrdercodes();
		if (!orders.isEmpty()) {
			return new ResponseEntity<List<Ordercodes>>(orders,  HttpStatus.ACCEPTED);
		} else {
			throw new OrderNotFoundException("No order codes available");
		}
	}
	
	@PostMapping(path=URLMapping.GET_ORDERCODES,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> createOrderCodes(
			@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long ordercodeid,
			@RequestParam(name = ConstantValues.REMARK, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String remark
    ){
		ResponseBean response=new ResponseBean();
		if(ordercodeid==Long.parseLong(ConstantValues.DEFAULT_INT)
	||remark.equals(ConstantValues.DEFAULT_STRING)){
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} 
		else {
			Ordercodes oc= orercodesRepository.save(new Ordercodes(ordercodeid,remark));
			if(oc.getOrdercodeid()==ordercodeid) {
				response.setMessage("Data inserted in DB");
				response.setResponsecode(2000);
				response.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
				
			}
			else
		    {
		    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
		    }
			
		}
			
	}
	
	
	@PutMapping(path=URLMapping.GET_ORDERCODES)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateOrderCodes(
			@RequestParam(name = ConstantValues.ORDERCODEID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long ordercodeid,
			@RequestParam(name = ConstantValues.REMARK, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String remark
    ){
		if(ordercodeid==Long.parseLong(ConstantValues.DEFAULT_INT)
				||remark.equals(ConstantValues.DEFAULT_STRING)){
						throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
					} 
		else {
			Optional<Ordercodes> code=orercodesRepository.getOrdercodesByOrderCodeid(ordercodeid);
			if(code.isPresent()) {
				code.get().setRemark(remark);
				Ordercodes codes1= orercodesRepository.save(code.get());
				
				if(codes1.getOrdercodeid()==ordercodeid) {
					ResponseBean response=new ResponseBean();
					response.setMessage("Data updated in DB");
					response.setResponsecode(2000);
					response.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);	
				}
				else {
					throw new  DBValueInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				}
			}
			else {
				throw new OrderNotFoundException("No order codes available");
			}
		}
		
	}

}
