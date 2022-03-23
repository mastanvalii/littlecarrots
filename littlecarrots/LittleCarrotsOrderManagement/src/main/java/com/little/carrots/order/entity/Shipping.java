package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="shipping")
public class Shipping implements Serializable {

	@Id
	@Column(name="shippingid")
	private long shippingid;
	
	@Column(name="orderid")
	private long orderid;
	
	@Column(name="customerid")
	private long customerid;
	
	@Column(name="shippingstatus")
	private String shippingstatus;
	
	@Column(name="courierid")
	private long courierid;
	
	@Column(name="couriercompany")
	private String couriercompany;
	
	@Column(name="shippingdate")
	private Timestamp shippingdate;
	
	@Column(name="deliverydate")
	private Timestamp deliverydate;

	public Shipping() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Shipping(long orderid, long customerid, String shippingstatus, long courierid,
			String couriercompany, Timestamp shippingdate, Timestamp deliverydate) {
		super();
		this.orderid = orderid;
		this.customerid = customerid;
		this.shippingstatus = shippingstatus;
		this.courierid = courierid;
		this.couriercompany = couriercompany;
		this.shippingdate = shippingdate;
		this.deliverydate = deliverydate;
	}



	public long getShippingid() {
		return shippingid;
	}

	public void setShippingid(long shippingid) {
		this.shippingid = shippingid;
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

	public String getShippingstatus() {
		return shippingstatus;
	}

	public void setShippingstatus(String shippingstatus) {
		this.shippingstatus = shippingstatus;
	}

	public long getCourierid() {
		return courierid;
	}

	public void setCourierid(long courierid) {
		this.courierid = courierid;
	}

	public String getCouriercompany() {
		return couriercompany;
	}

	public void setCouriercompany(String couriercompany) {
		this.couriercompany = couriercompany;
	}

	

	public Timestamp getShippingdate() {
		return shippingdate;
	}



	public void setShippingdate(Timestamp shippingdate) {
		this.shippingdate = shippingdate;
	}



	public Timestamp getDeliverydate() {
		return deliverydate;
	}



	public void setDeliverydate(Timestamp deliverydate) {
		this.deliverydate = deliverydate;
	}



	@Override
	public String toString() {
		return "Shipping [shippingid=" + shippingid + ", orderid=" + orderid + ", customerid=" + customerid
				+ ", shippingstatus=" + shippingstatus + ", courierid=" + courierid + ", couriercompany="
				+ couriercompany + ", shippingdate=" + shippingdate + ", deliverydate=" + deliverydate + "]";
	}
	
}
