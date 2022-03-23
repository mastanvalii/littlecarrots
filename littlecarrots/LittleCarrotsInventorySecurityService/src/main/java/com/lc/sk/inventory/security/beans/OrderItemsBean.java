package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

public class OrderItemsBean implements Serializable{

	
	private long orderitemsid;
	
	private long orderid;
	
	private long productid;
	
	private long qty;
	
	private long productprice;

	private Calendar  deliverydate;
	
	private long ordercodeid;

	public OrderItemsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public OrderItemsBean(long orderitemsid, long orderid, long productid, long qty, long productprice,
// 			Calendar deliverydate, long ordercodeid) {
// 		super();
// 		this.orderitemsid = orderitemsid;
// 		this.orderid = orderid;
// 		this.productid = productid;
// 		this.qty = qty;
// 		this.productprice = productprice;
// 		this.deliverydate = deliverydate;
// 		this.ordercodeid = ordercodeid;
// 	}

	public long getOrderitemsid() {
		return orderitemsid;
	}

	public void setOrderitemsid(long orderitemsid) {
		this.orderitemsid = orderitemsid;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getProductprice() {
		return productprice;
	}

	public void setProductprice(long productprice) {
		this.productprice = productprice;
	}

	public Calendar getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Calendar deliverydate) {
		this.deliverydate = deliverydate;
	}

	public long getOrdercodeid() {
		return ordercodeid;
	}

	public void setOrdercodeid(long ordercodeid) {
		this.ordercodeid = ordercodeid;
	}

	@Override
	public String toString() {
		return "OrderItemsBean [orderitemsid=" + orderitemsid + ", orderid=" + orderid + ", productid=" + productid
				+ ", qty=" + qty + ", productprice=" + productprice + ", deliverydate=" + deliverydate
				+ ", ordercodeid=" + ordercodeid + "]";
	}

	
	
	
	
}
