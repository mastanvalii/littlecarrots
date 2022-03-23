package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class ComplaintboxBean implements Serializable {
	
	private long complaintid;
	private String fullname;
	private String email;
	private long phone;
	private String issuecategory;
	private String issuesubcategory;
	private Timestamp dateinsertion;
//	private MultipartFile reffile;
	private String details;
	private String status;
	public ComplaintboxBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComplaintboxBean(long complaintid, String fullname, String email, long phone, String issuecategory,
			String issuesubcategory, String details, String status) {
		super();
		this.complaintid = complaintid;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.issuecategory = issuecategory;
		this.issuesubcategory = issuesubcategory;
		this.details = details;
		this.status = status;
	}
	public long getComplaintid() {
		return complaintid;
	}
	public void setComplaintid(long complaintid) {
		this.complaintid = complaintid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getIssuecategory() {
		return issuecategory;
	}
	public void setIssuecategory(String issuecategory) {
		this.issuecategory = issuecategory;
	}
	public String getIssuesubcategory() {
		return issuesubcategory;
	}
	public void setIssuesubcategory(String issuesubcategory) {
		this.issuesubcategory = issuesubcategory;
	}
	public Timestamp getDateinsertion() {
		return dateinsertion;
	}
	public void setDateinsertion(Timestamp dateinsertion) {
		this.dateinsertion = dateinsertion;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ComplaintboxBean [complaintid=" + complaintid + ", fullname=" + fullname + ", email=" + email
				+ ", phone=" + phone + ", issuecategory=" + issuecategory + ", issuesubcategory=" + issuesubcategory
				+ ", dateinsertion=" + dateinsertion + ", details=" + details + ", status=" + status + "]";
	}
	
	
	
}
