package com.lc.sk.inventory.service;

import java.util.Date;
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
import com.lc.sk.inventory.entities.Approval;
import com.lc.sk.inventory.exception.subexception.ApprovalException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.ApprovalRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class ApprovalService {

	@Autowired
	ApprovalRepository approvalRepository;
	

	
	
	@SuppressWarnings("boxing")
	@PostMapping(path = URLMapping.APPROVAL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertapprove(
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.INSERTED_USER, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String inserteduser,
//			@RequestParam(name = ConstantValues.APPROVAL_USER, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String approvaluser,
//			@RequestParam(name = ConstantValues.APPROVAL_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) date approvaldate,
//			@RequestParam(name = ConstantValues.QC_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) date qcdate,
			@RequestParam(name = ConstantValues.STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN+"") boolean status,
			@RequestParam(name = ConstantValues.STATUS_TEXT, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String statustext,
			@RequestParam(name = ConstantValues.COMMENT, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String comment)
	{
		if(productid == Long.parseLong(ConstantValues.DEFAULT_INT)
				//||approvaluser.equals(ConstantValues.DEFAULT_STRING)
				//||status == new Boolean(ConstantValues.DEFAULT_BOOLEAN+"")
				||statustext.equals(ConstantValues.DEFAULT_STRING))
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			Date qcdate=new Date();
			Approval approve = null;
			 approve=this.approvalRepository.getApprovalByProductId(productid);
			 if(approve!=null&&approve.getProductid()==productid) {
				 approve.setProductid(productid);
				 approve.setInserteduser(inserteduser);
				 approve.setQcdate(qcdate);
				 approve.setStatus(status);
				 approve.setStatustext(statustext);
				 approve.setComment(comment);
				 approve=this.approvalRepository.save(approve);
					if(approve.getProductid()==productid)
					{
						ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
								SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
						return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
					} else {
						throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
					}
			 }else {
				approve=this.approvalRepository.save(new Approval(productid,inserteduser,qcdate,status,statustext,comment));
				if(approve.getProductid()==productid)
				{
					ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
							SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
				} else {
					throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				}
			 }
			 
		}
		
	}
	
	
	@SuppressWarnings("boxing")
	@PutMapping(path = URLMapping.APPROVAL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> updateapproval(
			@RequestParam(name = ConstantValues.SERIAL_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long serialid,
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long productid,
//			@RequestParam(name = ConstantValues.INSERTED_USER, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String inserteduser,
			@RequestParam(name = ConstantValues.APPROVAL_USER, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String approvaluser,
//			@RequestParam(name = ConstantValues.APPROVAL_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) date approvaldate,
//			@RequestParam(name = ConstantValues.QC_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) date qcdate,
			@RequestParam(name = ConstantValues.STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN+"") boolean status,
			@RequestParam(name = ConstantValues.STATUS_TEXT, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String statustext,
			@RequestParam(name = ConstantValues.COMMENT, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String comment)
	{
		if(productid == Long.parseLong(ConstantValues.DEFAULT_INT)
				//||approvaluser.equals(ConstantValues.DEFAULT_STRING)
			//	||status == new Boolean(ConstantValues.DEFAULT_BOOLEAN+"")
				||statustext.equals(ConstantValues.DEFAULT_STRING))
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} 
		else
		{
			//Optional<Approval> approve=approvalRepository.findById(serialid);
			Optional<Approval> approve=this.approvalRepository.getApprovalBySerialId(serialid);
			if(approve.isPresent())
			{
				Date approvaldate=new Date();
				if(approve.get().getSerialid()==serialid && approve.get().getProductid()==productid) {
					approve.get().setApprovaluser(approvaluser);
					approve.get().setStatus(status);
					approve.get().setStatustext(statustext);
					approve.get().setComment(comment);
					approve.get().setApprovedate(approvaldate);
				Approval finalapproval=this.approvalRepository.save(approve.get());
				if(finalapproval.getSerialid() == serialid)
				{
					ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
							SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
				}
				else {
					throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				}
				}else {
					throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
				}
				
			}
			else
			{
				throw new ApprovalException(ConstantValues.NO_SERIAL_ID_FOUND);
			}
		}
	}
	
	@GetMapping(path = URLMapping.APPROVAL_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Approval>> approvalgetbyID(@PathVariable Long serialid, @PathVariable String dummy)
	{
		long a = System.currentTimeMillis();
		Optional<Approval> approve=this.approvalRepository.getApprovalBySerialId(serialid);
		long b = System.currentTimeMillis();
		System.err.println("FindByID:"+(b-a)); //$NON-NLS-1$
		if(!approve.isPresent() || approve.get() == null)
		{
			throw new ApprovalException(ConstantValues.NO_SERIAL_ID_FOUND);
		}
		else
		{
			return new ResponseEntity<Optional<Approval>>(approve,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path = URLMapping.APPROVAL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Approval>> listOfApproval()
	{
		List<Approval> approve=(List<Approval>) this.approvalRepository.getAllDetails();
		if(approve.isEmpty())
		{
			throw new ApprovalException(ConstantValues.NO_APPROVALS_FOUND);
			
		}
		return new ResponseEntity<List<Approval>>(approve,  HttpStatus.ACCEPTED);		
	}
	
	@SuppressWarnings("boxing")
	@GetMapping(path = URLMapping.APPROVAL_BY_PRODUCT_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Approval> getApprovalByProductId(@PathVariable long productid)
	{
		return new ResponseEntity<Approval>(this.approvalRepository.getApprovalByProductId(productid),  HttpStatus.ACCEPTED);
	}
}
