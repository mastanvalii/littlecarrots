package com.little.carrots.order.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.little.carrots.order.entity.OrderItems;

public class Order_Full_Details1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -430132102256136916L;

	private String orderid;
	
	private Timestamp orderdate;
	
	private String customerid;
	
	private String totalprice;
	
	private String ordercodeid;
	
	private String gatewayorderid;
	
	private String paymenttransactionid;
	private String addressid;
	
	private List<OrderItems> orderitems;

	public Order_Full_Details1() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public Order_Full_Details1(String orderid, Timestamp orderdate, String customerid, String totalprice,
// 			String ordercodeid, String gatewayorderid, String paymenttransactionid, String addressid,
// 			List<OrderItems> orderitems) {
// 		super();
// 		this.orderid = orderid;
// 		this.orderdate = orderdate;
// 		this.customerid = customerid;
// 		this.totalprice = totalprice;
// 		this.ordercodeid = ordercodeid;
// 		this.gatewayorderid = gatewayorderid;
// 		this.paymenttransactionid = paymenttransactionid;
// 		this.addressid = addressid;
// 		this.orderitems = orderitems;
// 	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getOrdercodeid() {
		return ordercodeid;
	}

	public void setOrdercodeid(String ordercodeid) {
		this.ordercodeid = ordercodeid;
	}

	public String getGatewayorderid() {
		return gatewayorderid;
	}

	public void setGatewayorderid(String gatewayorderid) {
		this.gatewayorderid = gatewayorderid;
	}

	public String getPaymenttransactionid() {
		return paymenttransactionid;
	}

	public void setPaymenttransactionid(String paymenttransactionid) {
		this.paymenttransactionid = paymenttransactionid;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public List<OrderItems> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItems> orderitems) {
		this.orderitems = orderitems;
	}

	@Override
	public String toString() {
		return "Order_Full_Details1 [orderid=" + orderid + ", orderdate=" + orderdate + ", customerid=" + customerid
				+ ", totalprice=" + totalprice + ", ordercodeid=" + ordercodeid + ", gatewayorderid=" + gatewayorderid
				+ ", paymenttransactionid=" + paymenttransactionid + ", addressid=" + addressid + ", orderitems="
				+ orderitems + "]";
	}

	
	
}
