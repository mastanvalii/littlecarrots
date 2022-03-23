package com.lc.sk.inventory.security.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class ShippingBean {
	
	private String shippingid;
	
	private String orderid;
	
	private String customerid;
	
	private String shippingstatus;
	
	private String courierid;
	
	private String couriercompany;
	
	private String shippingdate;
	
	private String deliverydate;

	public ShippingBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ShippingBean(String shippingid, String orderid, String customerid, String shippingstatus, String courierid,
// 			String couriercompany, String shippingdate, String deliverydate) {
// 		super();
// 		this.shippingid = shippingid;
// 		this.orderid = orderid;
// 		this.customerid = customerid;
// 		this.shippingstatus = shippingstatus;
// 		this.courierid = courierid;
// 		this.couriercompany = couriercompany;
// 		this.shippingdate = shippingdate;
// 		this.deliverydate = deliverydate;
// 	}

	public String getShippingid() {
		return shippingid;
	}

	public void setShippingid(String shippingid) {
		this.shippingid = shippingid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getShippingstatus() {
		return shippingstatus;
	}

	public void setShippingstatus(String shippingstatus) {
		this.shippingstatus = shippingstatus;
	}

	public String getCourierid() {
		return courierid;
	}

	public void setCourierid(String courierid) {
		this.courierid = courierid;
	}

	public String getCouriercompany() {
		return couriercompany;
	}

	public void setCouriercompany(String couriercompany) {
		this.couriercompany = couriercompany;
	}

	public String getShippingdate() {
		return shippingdate;
	}

	public void setShippingdate(String shippingdate) {
		this.shippingdate = shippingdate;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	@Override
	public String toString() {
		return "ShippingBean [shippingid=" + shippingid + ", orderid=" + orderid + ", customerid=" + customerid
				+ ", shippingstatus=" + shippingstatus + ", courierid=" + courierid + ", couriercompany="
				+ couriercompany + ", shippingdate=" + shippingdate + ", deliverydate=" + deliverydate + "]";
	}

	
	
}
