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
import org.springframework.web.multipart.MultipartFile;


import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.entity.Complaintbox;
import com.little.carrots.order.exception.subexceptions.CartmanagementException;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.DBValueInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;
import com.little.carrots.order.repositories.ComplaintboxRepository;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.SecurityHttpStatus;
import com.little.carrots.order.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class ComplaintboxService {

	
	@Autowired
	ComplaintboxRepository complaintRepo;
	

	
	@PostMapping(path=URLMapping.GET_ALL_COMPLAINTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> newComplaint(
			@RequestParam(name="fullname" )String fullname, 
			@RequestParam(name="email")String email,
			@RequestParam(name="phone")long phone,
			@RequestParam(name="issuecategory")String issuecategory,
			@RequestParam(name="issuesubcategory")String issuesubcategory,			
			//@RequestParam(name="reffile")MultipartFile reffile,
			@RequestParam(name="details")String details,
			@RequestParam(name="status")String status
			){
		ResponseBean responseBean = new ResponseBean();
		if(fullname.isEmpty()||email.isEmpty()||details.isEmpty()) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			
		}else {
			//try {
			java.sql.Timestamp dateinsertion = new java.sql.Timestamp(new java.util.Date().getTime());
			Complaintbox cr=complaintRepo.save(new Complaintbox(fullname, email, phone, issuecategory, issuesubcategory,dateinsertion, /*reffile.getBytes(),*/ details, status));
			if(cr.getFullname()==fullname) {
			responseBean.setMessage(ConstantValues.COMPLAIN_RAISE_SUCCESS );
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			}
			else
			{
		    	throw new  DBValueInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
			/*}
			catch(IOException e) {
				throw new DBInsertException(e.getMessage());
			}*/
			}
		
	}
	
	@GetMapping(path=URLMapping.GET_ALL_COMPLAINTS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Complaintbox>> getAllComplaints(){
		List<Complaintbox> box = (List<Complaintbox>) complaintRepo.findAll();
		if(box.isEmpty()) {
			throw new CartmanagementException("No Complaints Found");
		}else {
			return new ResponseEntity<List<Complaintbox>>(box, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path=URLMapping.GET_ALL_COMPLAINTS_BY_ID)
	@ResponseBody
	public ResponseEntity<Complaintbox> getComplaintById(@PathVariable long complaintid){
		Optional<Complaintbox> box = complaintRepo.findById(complaintid);
		if (!box.isPresent()||box.get()==null) {
			
			throw new CartmanagementException("No Complaints Found");
		} else {
			return new ResponseEntity<Complaintbox>(box.get(),  HttpStatus.ACCEPTED);
		}
	}
	
	@PutMapping(path = URLMapping.GET_ALL_COMPLAINTS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> UpdateStatus(
			@RequestParam(name="complaintid")long complaintid,
			@RequestParam(name="email")String email,
			@RequestParam(name="status")String status
			) {
		if (complaintid == Long.parseLong(ConstantValues.DEFAULT_INT) || email.isEmpty()|| status.isEmpty()) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {

			Optional<Complaintbox> box = complaintRepo.findById(complaintid);
		if (box.isPresent()) {
			box.get().setStatus(status);
			
			Complaintbox box1 = complaintRepo.save(box.get());
			if (box1.getComplaintid() == complaintid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
		} else {
			throw new CartmanagementException("No Complaints Found");

		}

	}}
}
