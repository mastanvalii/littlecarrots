package com.little.carrots.order.bean;

import java.io.Serializable;

public class RazorpayOrderedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -879064659214284967L;
	private String razorpay_payment_id;
	private String message;
	
	public RazorpayOrderedBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public RazorpayOrderedBean(String razorpay_payment_id, String message) {
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
		return "RazorpayOrderedBean [razorpay_payment_id=" + razorpay_payment_id + ", message=" + message + "]";
	}
	
	

}
