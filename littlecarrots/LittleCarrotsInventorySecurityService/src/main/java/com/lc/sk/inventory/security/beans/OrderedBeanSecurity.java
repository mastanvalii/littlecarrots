package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class OrderedBeanSecurity implements Serializable{
	
private String razorpayOrder_id;
	
	private long orderid;

	private int totalprice;
	
	private String key;
	
	private long customerid;

	public OrderedBeanSecurity() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public OrderedBeanSecurity(String razorpayOrder_id, long orderid, int totalprice, String key, long customerid) {
// 		super();
// 		this.razorpayOrder_id = razorpayOrder_id;
// 		this.orderid = orderid;
// 		this.totalprice = totalprice;
// 		this.key = key;
// 		this.customerid = customerid;
// 	}

	public String getRazorpayOrder_id() {
		return razorpayOrder_id;
	}

	public void setRazorpayOrder_id(String razorpayOrder_id) {
		this.razorpayOrder_id = razorpayOrder_id;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "OrderedBeanSecurity [razorpayOrder_id=" + razorpayOrder_id + ", orderid=" + orderid + ", totalprice="
				+ totalprice + ", key=" + key + ", customerid=" + customerid + "]";
	}
	
	

}
