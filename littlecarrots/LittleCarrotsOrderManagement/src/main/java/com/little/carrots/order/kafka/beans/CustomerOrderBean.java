package com.little.carrots.order.kafka.beans;

import java.io.Serializable;

public class CustomerOrderBean implements Serializable {

	private String razorpay_order_id;
	private String razorpay_signature;
	private String razorpay_payment_id;
	private Long customerid;
	private Long orderid;
	private Long addressid;
	private boolean flag;
	public CustomerOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerOrderBean(String razorpay_order_id, String razorpay_signature, String razorpay_payment_id,
			Long customerid, Long orderid, Long addressid, boolean flag) {
		super();
		this.razorpay_order_id = razorpay_order_id;
		this.razorpay_signature = razorpay_signature;
		this.razorpay_payment_id = razorpay_payment_id;
		this.customerid = customerid;
		this.orderid = orderid;
		this.addressid = addressid;
		this.flag = flag;
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
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Long getAddressid() {
		return addressid;
	}
	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "CustomerOrderBean [razorpay_order_id=" + razorpay_order_id + ", razorpay_signature="
				+ razorpay_signature + ", razorpay_payment_id=" + razorpay_payment_id + ", customerid=" + customerid
				+ ", orderid=" + orderid + ", addressid=" + addressid + ", flag=" + flag + "]";
	}
	
	
	
	
}
