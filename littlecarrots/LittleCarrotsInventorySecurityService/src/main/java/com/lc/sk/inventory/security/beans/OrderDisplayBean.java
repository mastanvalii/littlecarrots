package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.List;

public class OrderDisplayBean implements Serializable{
	
	private String orderid;
	private String orderdate;
	private String customername;
	private String totalprice;
	private String order_status;
	private String gateway_orderid;
	private String addrestype;
	private String ordercodeid;
	private OrderItemsBean[] orderitems;
	private long addressid;
	private long customerid;
	
	
	public OrderDisplayBean() {
		super();
		// TODO Auto-generated constructor stub
	}





// TODO Remove unused code found by UCDetector
// 	public OrderDisplayBean(String orderid, String orderdate, String customername, String totalprice,
// 			String order_status, String gateway_orderid, String addrestype, String ordercodeid,
// 			OrderItemsBean[] orderitems, long addressid, long customerid) {
// 		super();
// 		this.orderid = orderid;
// 		this.orderdate = orderdate;
// 		this.customername = customername;
// 		this.totalprice = totalprice;
// 		this.order_status = order_status;
// 		this.gateway_orderid = gateway_orderid;
// 		this.addrestype = addrestype;
// 		this.ordercodeid = ordercodeid;
// 		this.orderitems = orderitems;
// 		this.addressid = addressid;
// 		this.customerid = customerid;
// 	}





	public String getOrdercodeid() {
		return ordercodeid;
	}



	public void setOrdercodeid(String ordercodeid) {
		this.ordercodeid = ordercodeid;
	}














	public OrderItemsBean[] getOrderitems() {
		return orderitems;
	}





	public void setOrderitems(OrderItemsBean[] orderitems) {
		this.orderitems = orderitems;
	}





	public long getAddressid() {
		return addressid;
	}



	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}



	public long getCustomerid() {
		return customerid;
	}



	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}



	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getGateway_orderid() {
		return gateway_orderid;
	}

	public void setGateway_orderid(String gateway_orderid) {
		this.gateway_orderid = gateway_orderid;
	}

	public String getAddrestype() {
		return addrestype;
	}

	public void setAddrestype(String addrestype) {
		this.addrestype = addrestype;
	}

	@Override
	public String toString() {
		return "OrderDisplayBean [orderid=" + orderid + ", orderdate=" + orderdate + ", customername=" + customername
				+ ", totalprice=" + totalprice + ", order_status=" + order_status + ", gateway_orderid="
				+ gateway_orderid + ", addrestype=" + addrestype + "]";
	}
	
	

}
