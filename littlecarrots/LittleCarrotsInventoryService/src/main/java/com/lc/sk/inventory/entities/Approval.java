package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="approval")
public class Approval implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3118240467854360563L;

	@Id
	@Column(name="serialid")
	private long serialid;
	
	@Column(name="productid")
	private long productid;
	
	@Column(name="inserteduser")
	private String inserteduser;
	
	@Column(name="approvaluser")
	private String approvaluser;
	
	@Column(name="approvedate")
	private Date approvedate;
	
	@Column(name="qcdate")
	private Date qcdate;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="statustext")
	private String statustext;
	
	@Column(name="comment")
	private String comment;

	public Approval() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Approval(long productid, String inserteduser, Date qcdate,
			boolean status, String statustext, String comment) {
		super();
		this.productid = productid;
		this.inserteduser = inserteduser;
		//this.approvaluser = approvaluser;
		//this.approvedate = approvedate;
		this.qcdate = qcdate;
		this.status = status;
		this.statustext = statustext;
		this.comment = comment;
	}
	
	

// TODO Remove unused code found by UCDetector
// 	public Approval(long serialid, long productid,String approvaluser, Date approvedate,
// 			boolean status, String statustext, String comment) {
// 		super();
// 		this.serialid = serialid;
// 		this.productid = productid;
// 		//this.inserteduser = inserteduser;
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
				+ ", approvaluser=" + approvaluser + ", approvedate=" + approvedate + ", qcdate=" + qcdate + ", status=" //$NON-NLS-1$
				+ status + ", statustext=" + statustext + ", comment=" + comment + "]"; //$NON-NLS-2$
	}
	
	
	
	
}
