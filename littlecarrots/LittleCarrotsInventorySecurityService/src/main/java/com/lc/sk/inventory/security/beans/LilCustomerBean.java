package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class LilCustomerBean implements Serializable{

	private long userid;
	
	private String uniqueid;
	
	private String email;
	
	private String firstname;
	
	
	private String lastname;
	
	private String imageurl;
	
	private long  phone;
	
	private String usertype;
	
	private boolean status;

	
	
	
	public LilCustomerBean() {
		super();
	}




// TODO Remove unused code found by UCDetector
// 	public LilCustomerBean(long userid, String uniqueid, String email, String firstname, String lastname, String imageurl,
// 			long phone, String usertype, boolean status) {
// 		super();
// 		this.userid = userid;
// 		this.uniqueid = uniqueid;
// 		this.email = email;
// 		this.firstname = firstname;
// 		this.lastname = lastname;
// 		this.imageurl = imageurl;
// 		this.phone = phone;
// 		this.usertype = usertype;
// 		this.status = status;
// 	}




	public long getUserid() {
		return userid;
	}




	public void setUserid(long userid) {
		this.userid = userid;
	}




	public String getUniqueid() {
		return uniqueid;
	}




	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getFirstname() {
		return firstname;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public String getLastname() {
		return lastname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public String getImageurl() {
		return imageurl;
	}




	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}




	public long getPhone() {
		return phone;
	}




	public void setPhone(long phone) {
		this.phone = phone;
	}




	public String getUsertype() {
		return usertype;
	}




	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}




	public boolean isStatus() {
		return status;
	}




	public void setStatus(boolean status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "LilCustomerBean [userid=" + userid + ", uniqueid=" + uniqueid + ", email=" + email + ", firstname="
				+ firstname + ", lastname=" + lastname + ", imageurl=" + imageurl + ", phone=" + phone + ", usertype="
				+ usertype + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
}
