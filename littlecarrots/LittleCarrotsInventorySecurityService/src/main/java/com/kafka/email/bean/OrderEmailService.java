package com.kafka.email.bean;

import java.io.Serializable;
import java.util.Arrays;

public class OrderEmailService implements Serializable {

	private String orderid;
	private String transactionid;
	private String dateoforder;
	private String dateofdelivery;
	private String orderstatus;
	private String fullname;
	private String address;
	private String total;
	private OrderedItems[] orderitems;
	private String contact1;
	private String contact2;
	private String email;
	public OrderEmailService() {
		super();
	}
// TODO Remove unused code found by UCDetector
// 	public OrderEmailService(String orderid, String transactionid, String dateoforder, String dateofdelivery,
// 			String orderstatus, String fullname, String address, String total, OrderedItems[] orderitems,
// 			String contact1, String contact2, String email) {
// 		super();
// 		this.orderid = orderid;
// 		this.transactionid = transactionid;
// 		this.dateoforder = dateoforder;
// 		this.dateofdelivery = dateofdelivery;
// 		this.orderstatus = orderstatus;
// 		this.fullname = fullname;
// 		this.address = address;
// 		this.total = total;
// 		this.orderitems = orderitems;
// 		this.contact1 = contact1;
// 		this.contact2 = contact2;
// 		this.email = email;
// 	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getDateoforder() {
		return dateoforder;
	}
	public void setDateoforder(String dateoforder) {
		this.dateoforder = dateoforder;
	}
	public String getDateofdelivery() {
		return dateofdelivery;
	}
	public void setDateofdelivery(String dateofdelivery) {
		this.dateofdelivery = dateofdelivery;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public OrderedItems[] getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(OrderedItems[] orderitems) {
		this.orderitems = orderitems;
	}
	public String getContact1() {
		return contact1;
	}
	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}
	public String getContact2() {
		return contact2;
	}
	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "OrderEmailService [orderid=" + orderid + ", transactionid=" + transactionid + ", dateoforder="
				+ dateoforder + ", dateofdelivery=" + dateofdelivery + ", orderstatus=" + orderstatus + ", fullname="
				+ fullname + ", address=" + address + ", total=" + total + ", orderitems=" + Arrays.toString(orderitems)
				+ ", contact1=" + contact1 + ", contact2=" + contact2 + ", email=" + email + "]";
	}
	
	
	
}
