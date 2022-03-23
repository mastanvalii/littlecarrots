package com.kafka.email.bean;

import java.io.Serializable;

public class OrderedItems implements Serializable {

	private String orderitemid;
	private String orderitemtitle;
	private String quantity;
	private String price;
	private String size;
// TODO Remove unused code found by UCDetector
// 	public OrderedItems(String orderitemid, String orderitemtitle, String quantity, String price, String size) {
// 		super();
// 		this.orderitemid = orderitemid;
// 		this.orderitemtitle = orderitemtitle;
// 		this.quantity = quantity;
// 		this.price = price;
// 		this.size = size;
// 	}
	public OrderedItems() {
		super();
	}
	public String getOrderitemid() {
		return orderitemid;
	}
	public void setOrderitemid(String orderitemid) {
		this.orderitemid = orderitemid;
	}
	public String getOrderitemtitle() {
		return orderitemtitle;
	}
	public void setOrderitemtitle(String orderitemtitle) {
		this.orderitemtitle = orderitemtitle;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "OrderedItems [orderitemid=" + orderitemid + ", orderitemtitle=" + orderitemtitle + ", quantity="
				+ quantity + ", price=" + price + ", size=" + size + "]";
	}
	
	
}
