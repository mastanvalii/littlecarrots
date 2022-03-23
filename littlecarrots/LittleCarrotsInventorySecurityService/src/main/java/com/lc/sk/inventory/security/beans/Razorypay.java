package com.lc.sk.inventory.security.beans;

import java.io.Serializable;


public class Razorypay implements Serializable {

	
	private String razorpay_order_id;	
	
	private String razorpay_signature;	
	
	private String razorpay_payment_id;

	public Razorypay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Razorypay(String razorpay_order_id, String razorpay_signature, String razorpay_payment_id) {
		super();
		this.razorpay_order_id = razorpay_order_id;
		this.razorpay_signature = razorpay_signature;
		this.razorpay_payment_id = razorpay_payment_id;
	}

	public String getRazorpay_order_id() {
		return razorpay_order_id;
	}

	public void setRazorpay_order_id(String razorpay_order_id) {
		this.razorpay_order_id = razorpay_order_id;
	}

	public String getRazorpay_signature() {
		return razorpay_signature;
	}

	public void setRazorpay_signature(String razorpay_signature) {
		this.razorpay_signature = razorpay_signature;
	}

	public String getRazorpay_payment_id() {
		return razorpay_payment_id;
	}

	public void setRazorpay_payment_id(String razorpay_payment_id) {
		this.razorpay_payment_id = razorpay_payment_id;
	}

	@Override
	public String toString() {
		return "Razorypay [razorpay_order_id=" + razorpay_order_id + ", razorpay_signature=" + razorpay_signature
				+ ", razorpay_payment_id=" + razorpay_payment_id + "]";
	}
	
	
	
}
