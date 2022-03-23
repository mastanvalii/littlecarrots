package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class RazorpayOrderedSecurityBean implements Serializable{
	
	private String razorpay_payment_id;
	private String message;
	
	public RazorpayOrderedSecurityBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public RazorpayOrderedSecurityBean(String razorpay_payment_id, String message) {
// 		super();
// 		this.razorpay_payment_id = razorpay_payment_id;
// 		this.message = message;
// 	}
	public String getRazorpay_payment_id() {
		return razorpay_payment_id;
	}
	public void setRazorpay_payment_id(String razorpay_payment_id) {
		this.razorpay_payment_id = razorpay_payment_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "RazorpayOrderedSecurityBean [razorpay_payment_id=" + razorpay_payment_id + ", message=" + message + "]";
	}

	

}
