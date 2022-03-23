package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ApprovalPaging implements Serializable {

	private String serialid;
	private String productid;
	private String approvaluser;
	private String approvedate;
	private String qcdate;
	private String status;
	private String statustext;
	private String comment;

	public ApprovalPaging() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public ApprovalPaging(String serialid, String productid, String approvaluser, String approvedate, String qcdate,
// 			String status, String statustext, String comment) {
// 		super();
// 		this.serialid = serialid;
// 		this.productid = productid;
// 		this.approvaluser = approvaluser;
// 		this.approvedate = approvedate;
// 		this.qcdate = qcdate;
// 		this.status = status;
// 		this.statustext = statustext;
// 		this.comment = comment;
// 	}

	public String getSerialid() {
		return serialid;
	}

	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getApprovaluser() {
		return approvaluser;
	}

	public void setApprovaluser(String approvaluser) {
		this.approvaluser = approvaluser;
	}

	public String getApprovedate() {
		return approvedate;
	}

	public void setApprovedate(String approvedate) {
		this.approvedate = approvedate;
	}

	public String getQcdate() {
		return qcdate;
	}

	public void setQcdate(String qcdate) {
		this.qcdate = qcdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
		return "ApprovalPaging [serialid=" + serialid + ", productid=" + productid + ", approvaluser=" + approvaluser
				+ ", approvedate=" + approvedate + ", qcdate=" + qcdate + ", status=" + status + ", statustext="
				+ statustext + ", comment=" + comment + "]";
	}

	
}
