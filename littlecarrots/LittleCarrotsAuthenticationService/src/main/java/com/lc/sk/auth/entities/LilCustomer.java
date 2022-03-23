
package com.lc.sk.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "lilcustomer")
public class LilCustomer implements Serializable{

	@Id
	@Column(name = "userid")
	private long userid;
	
	@Column(name = "uniqueid")
	private String uniqueid;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname; 
	
	@Column(name = "imageurl")
	private String imageurl; 
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "usertype")
	private String usertype; 
	
	@Column(name = "status")
	private boolean status;
	
	

	public LilCustomer() {
		super();
	}



	



	public LilCustomer(String uniqueid, String email, String firstname, String lastname, String imageurl, long phone,
			String usertype, boolean status) {
		super();
		this.uniqueid = uniqueid;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.imageurl = imageurl;
		this.phone = phone;
		this.usertype = usertype;
		this.status = status;
	}





// TODO Remove unused code found by UCDetector
// 	public LilCustomer(long phone) {
// 		super();
// 		this.phone = phone;
// 	}







	public LilCustomer(String uniqueid, String email, String firstname, String lastname,String imgurl,String usertype) {
		super();
		this.uniqueid = uniqueid;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.imageurl = imgurl;
		this.usertype= usertype;
	}







// TODO Remove unused code found by UCDetector
// 	public LilCustomer(String email2, String firstname2, String lastname2, String imageurl2, long phone2) {
// 		// TODO Auto-generated constructor stub
// 	}







// TODO Remove unused code found by UCDetector
// 	public LilCustomer(long phone2, String imageurl2, String lastname2, boolean status2, String usertype2) {
// 		// TODO Auto-generated constructor stub
// 	}







// TODO Remove unused code found by UCDetector
// 	public LilCustomer(long phone2, String imageurl2, String lastname2, boolean status2) {
// 		// TODO Auto-generated constructor stub
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
		return "LilCustomer [userid=" + userid + ", uniqueid=" + uniqueid + ", email=" + email + ", firstname="
				+ firstname + ", lastname=" + lastname + ", imageurl=" + imageurl + ", phone=" + phone + ", usertype="
				+ usertype + ", status=" + status + "]";
	}

	
}
