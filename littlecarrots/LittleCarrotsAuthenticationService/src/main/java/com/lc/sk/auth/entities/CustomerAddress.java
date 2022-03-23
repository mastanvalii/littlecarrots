package com.lc.sk.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name= "customeraddress")
public class CustomerAddress implements Serializable{

	/*create table customeraddress (
			addressid  bigint auto_increment,
            name varchar(100),
			email varchar(150),
			flat varchar(500),
			streetaddress varchar(500),
			landmark varchar(300),
			pincode bigint(7),
			city varchar(200),
			state varchar(200),
            country varchar(50),
			mobile1 bigint(11),
			mobile2 bigint(11),
			addresstype varchar(100),
			constraint customeraddress_pk primary key (addressid),
			constraint customeraddress_fk foreign key (email) references lilcustomer(email)
			);*/


	@Id
	@Column(name="addressid")
	private long addressid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="flat")
	private String flat;
	
	@Column(name="streetaddress")
	private String streetaddress;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="pincode")
	private long pincode;
	
	@Column(name="city")
	private String city;
	
	@Column (name="state")
	private String state;
	
	@Column (name="country")
	private String country;
	
	@Column(name="mobile1")
	private long mobile1;
	
	@Column(name="mobile2")
	private long mobile2;
	
	@Column(name="addresstype")
	private String addresstype;

	public CustomerAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerAddress(String name, String email, String flat, String streetaddress, String landmark, long pincode,
			String city, String state, String country, long mobile1, long mobile2, String addresstype) {
		super();
		this.name = name;
		this.email = email;
		this.flat = flat;
		this.streetaddress = streetaddress;
		this.landmark = landmark;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.addresstype = addresstype;
	}

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getStreetAddress() {
		return streetaddress;
	}

	public void setStreetAddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getMobile1() {
		return mobile1;
	}

	public void setMobile1(long mobile1) {
		this.mobile1 = mobile1;
	}

	public long getMobile2() {
		return mobile2;
	}

	public void setMobile2(long mobile2) {
		this.mobile2 = mobile2;
	}

	public String getAddressType() {
		return addresstype;
	}

	public void setAddressType(String addresstype) {
		this.addresstype = addresstype;
	}

	@Override
	public String toString() {
		return "CustomerAddress [addressid=" + addressid + ", name=" + name + ", email=" + email + ", flat=" + flat
				+ ", streetaddress=" + streetaddress + ", landmark=" + landmark + ", pincode=" + pincode + ", city="
				+ city + ", state=" + state + ", country=" + country + ", mobile1=" + mobile1 + ", mobile2=" + mobile2
				+ ", addresstype=" + addresstype + "]";
	}

	
	
	
	

}

