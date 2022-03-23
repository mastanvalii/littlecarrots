package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="razorpaytransaction")
public class Razorpaytransaction implements Serializable {
	
	/*
	create table razorpaytransaction(
			razorpay_order_id varchar(500),
			razorpay_signature varchar(500),
			razorpay_payment_id varchar(500),
			customerid bigint,
			orderid bigint,
			constraint razorpay_pk primary key (razorpay_order_id)
			);
   */
	
	@Id
	@Column(name="razorpay_order_id")
	private String razorpay_order_id;
	
	@Column (name="razorpay_signature")
	private String razorpay_signature;
	
	@Column(name="razorpay_payment_id")
	private String razorpay_payment_id;
	
	@Column(name = "orderid")
	private long orderid;
	
	@Column(name = "customerid")
	private long customerid;

	public Razorpaytransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Razorpaytransaction(String razorpay_order_id, String razorpay_signature, String razorpay_payment_id,
			long orderid, long customerid) {
		super();
		this.razorpay_order_id = razorpay_order_id;
		this.razorpay_signature = razorpay_signature;
		this.razorpay_payment_id = razorpay_payment_id;
		this.orderid = orderid;
		this.customerid = customerid;
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

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Razorpaytransaction [razorpay_order_id=" + razorpay_order_id + ", razorpay_signature="
				+ razorpay_signature + ", razorpay_payment_id=" + razorpay_payment_id + ", orderid=" + orderid
				+ ", customerid=" + customerid + "]";
	}
	
	
}
