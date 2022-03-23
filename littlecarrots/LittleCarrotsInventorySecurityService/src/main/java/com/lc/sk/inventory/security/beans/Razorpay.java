package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

import javax.persistence.Column;


public class Razorpay implements Serializable {

	
	private String razorpay_order_id;	
	
	private String razorpay_signature;	
	
	private String razorpay_payment_id;	
		
	private long customerid;
	
	private long orderid;	

	public Razorpay() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Razorpay(String razorpay_order_id, String razorpay_signature, String razorpay_payment_id, long customerid,
// 			long orderid) {
// 		super();
// 		this.razorpay_order_id = razorpay_order_id;
// 		this.razorpay_signature = razorpay_signature;
// 		this.razorpay_payment_id = razorpay_payment_id;
// 		this.customerid = customerid;
// 		this.orderid = orderid;
// 	}

	
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

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "Razorpay [razorpay_order_id=" + razorpay_order_id + ", razorpay_signature=" + razorpay_signature
				+ ", razorpay_payment_id=" + razorpay_payment_id + ", customerid=" + customerid + ", orderid=" + orderid
				+ "]";
	}

	

	
	
	
	
}
