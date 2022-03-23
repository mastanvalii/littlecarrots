package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class ApprovalBean implements Serializable{
	
	private long serialid;
	
	private long productid;
	
	private String inserteduser;
	
	private String approvaluser;
	
	private Date approvedate;
	
	private Date qcdate;
	
	private boolean status;
	
	private String statustext;
	
	private String comment;

	public ApprovalBean() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public ApprovalBean(long productid, String inserteduser, Date qcdate,
// 			boolean status, String statustext, String comment) {
// 		super();
// 		this.productid = productid;
// 		this.inserteduser = inserteduser;
// 		this.qcdate = qcdate;
// 		this.status = status;
// 		this.statustext = statustext;
// 		this.comment = comment;
// 	}
	
	

// TODO Remove unused code found by UCDetector
// 	public ApprovalBean(long serialid, long productid,String approvaluser, Date approvedate,
// 			boolean status, String statustext, String comment) {
// 		super();
// 		this.serialid = serialid;
// 		this.productid = productid;
// 		this.approvaluser = approvaluser;
// 		this.approvedate = approvedate;
// 		this.status = status;
// 		this.statustext = statustext;
// 		this.comment = comment;
// 	}

	public long getSerialid() {
		return serialid;
	}

	public void setSerialid(long serialid) {
		this.serialid = serialid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getInserteduser() {
		return inserteduser;
	}

	public void setInserteduser(String inserteduser) {
		this.inserteduser = inserteduser;
	}

	public String getApprovaluser() {
		return approvaluser;
	}

	public void setApprovaluser(String approvaluser) {
		this.approvaluser = approvaluser;
	}

	public Date getApprovedate() {
		return approvedate;
	}

	public void setApprovedate(Date approvedate) {
		this.approvedate = approvedate;
	}

	public Date getQcdate() {
		return qcdate;
	}

	public void setQcdate(Date qcdate) {
		this.qcdate = qcdate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getStatustext() {
		return statustext;
	}

	public void setStatustext(String statustext) {
		this.statustext = statustext;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Approval [serialid=" + serialid + ", productid=" + productid + ", inserteduser=" + inserteduser
				+ ", approvaluser=" + approvaluser + ", approvedate=" + approvedate + ", qcdate=" + qcdate + ", status="
				+ status + ", statustext=" + statustext + ", comment=" + comment + "]";
	}
	
	
	
	
}

