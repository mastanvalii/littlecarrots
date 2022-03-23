package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class CustomerAddressPostBean implements Serializable{
	
	private String name;
	private String email;
	private String flat;
	private String streetAddress;
	private String landmark;
	private long pincode;
	private String city;
	private String state;
	private String country;
	private long mobile1;
	private long mobile2;
	private String addressType;
	
	public CustomerAddressPostBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public CustomerAddressPostBean(String name, String email, String flat, String streetAddress, String landmark,
// 			long pincode, String city, String state, String country, long mobile1, long mobile2, String addressType) {
// 		super();
// 		this.name = name;
// 		this.email = email;
// 		this.flat = flat;
// 		this.streetAddress = streetAddress;
// 		this.landmark = landmark;
// 		this.pincode = pincode;
// 		this.city = city;
// 		this.state = state;
// 		this.country = country;
// 		this.mobile1 = mobile1;
// 		this.mobile2 = mobile2;
// 		this.addressType = addressType;
// 	}

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
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "CustomerAddressPostBean [name=" + name + ", email=" + email + ", flat=" + flat + ", streetAddress="
				+ streetAddress + ", landmark=" + landmark + ", pincode=" + pincode + ", city=" + city + ", state="
				+ state + ", country=" + country + ", mobile1=" + mobile1 + ", mobile2=" + mobile2 + ", addressType="
				+ addressType + "]";
	}
	
	

}
