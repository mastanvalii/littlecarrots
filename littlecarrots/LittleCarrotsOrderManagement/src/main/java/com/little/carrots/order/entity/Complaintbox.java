package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="complaintbox")
public class Complaintbox implements Serializable {

	/*create table complaintbox(
		complaintid bigint auto_increment,
		fullname varchar(100),
		email varchar(100),
		phone bigint,
		issuecategory varchar(100),
		issuesubcategory varchar(100),
		#reffile blob,
		details varchar(1000),
		status varchar(1000),
		constraint complaintbox_pk primary key (complaintid)
		);
		*/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaintid")
	private long complaintid;
	
	
	@Column(name= "fullname")
	private String fullname;
	
	@Column(name= "email")
	private String email;
	
	@Column(name="phone")
	private long phone;
	
	@Column(name="issuecategory")
	private String issuecategory;
	
	@Column(name="issuesubcategory")
	private String issuesubcategory;
	
	/*@Column(name="reffile")
	private byte[] reffile;*/
	
	@Column(name="dateinsertion")
	private Timestamp dateinsertion;
	
	@Column(name="details")
	private String details;
	
	@Column(name="status")
	private String status;

	public Complaintbox() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Complaintbox( String fullname, String email, long phone, String issuecategory,
			String issuesubcategory, Timestamp dateinsertion, String details, String status) {
		super();
	
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.issuecategory = issuecategory;
		this.issuesubcategory = issuesubcategory;
		this.dateinsertion = dateinsertion;
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
		return "Complaintbox [complaintid=" + complaintid + ", fullname=" + fullname + ", email=" + email + ", phone="
				+ phone + ", issuecategory=" + issuecategory + ", issuesubcategory=" + issuesubcategory
				+ ", dateinsertion=" + dateinsertion + ", details=" + details + ", status=" + status + "]";
	}

	

	
	
	
	
	
}
