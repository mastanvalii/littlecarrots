package com.little.carrots.order.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.little.carrots.order.entity.OrderItems;

public class Order_Full_Details implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2758945989068509115L;

	private long orderid;
	
	private Timestamp orderdate;
	
	private long customerid;
	
	private double totalprice;
	
	private long ordercodeid;
	
	private String gateway_orderid;
	
	private long addressid;
	
	private List<OrderItems> orderitems;

	public Order_Full_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Order_Full_Details(long orderid, Timestamp orderdate, long customerid, double totalprice, long ordercodeid,
// 			String gateway_orderid, long addressid, List<OrderItems> orderitems) {
// 		super();
// 		this.orderid = orderid;
// 		this.orderdate = orderdate;
// 		this.customerid = customerid;
// 		this.totalprice = totalprice;
// 		this.ordercodeid = ordercodeid;
// 		this.gateway_orderid = gateway_orderid;
// 		this.addressid = addressid;
// 		this.orderitems = orderitems;
// 	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public long getOrdercodeid() {
		return ordercodeid;
	}

	public void setOrdercodeid(long ordercodeid) {
		this.ordercodeid = ordercodeid;
	}

	public String getGateway_orderid() {
		return gateway_orderid;
	}

	public void setGateway_orderid(String gateway_orderid) {
		this.gateway_orderid = gateway_orderid;
	}

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
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
		return "Order_Full_Details [orderid=" + orderid + ", orderdate=" + orderdate + ", customerid=" + customerid
				+ ", totalprice=" + totalprice + ", ordercodeid=" + ordercodeid + ", gateway_orderid=" + gateway_orderid
				+ ", addressid=" + addressid + ", orderitems=" + orderitems + "]";
	}

	

	
	
	

}
