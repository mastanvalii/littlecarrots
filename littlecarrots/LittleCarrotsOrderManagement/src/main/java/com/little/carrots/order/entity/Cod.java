package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="cod")
public class Cod implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6084508722241547141L;

	@Id
	@Column(name = "paymenttransactionid")
	private long paymenttransactionid;
	
	@Column(name = "orderdate")
	private Timestamp orderdate;
	
	@Column(name = "customerid")
	private long customerid;
	
	
	@Column(name = "totalamount")
	private long totalamount;
	
	@Column(name = "deliverydate")
	private Timestamp deliverydate;

	public Cod() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public Cod(long paymenttransactionid, Timestamp orderdate, long customerid, long totalamount, Timestamp deliverydate) {
// 		super();
// 		this.paymenttransactionid = paymenttransactionid;
// 		this.orderdate = orderdate;
// 		this.customerid = customerid;
// 		this.totalamount = totalamount;
// 		this.deliverydate = deliverydate;
// 	}

	public Cod(Date orderdate2, long customerid2, long totalamount2, Date deliverydate2) {
		
	}

	
	public long getPaymenttransactionid() {
		return paymenttransactionid;
	}

	public void setPaymenttransactionid(long paymenttransactionid) {
		this.paymenttransactionid = paymenttransactionid;
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

	public long getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(long totalamount) {
		this.totalamount = totalamount;
	}

	public Timestamp getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Timestamp deliverydate) {
		this.deliverydate = deliverydate;
	}

	@Override
	public String toString() {
		return "Cod [paymenttransactionid=" + paymenttransactionid + ", orderdate=" + orderdate + ", customerid="
				+ customerid + ", totalamount=" + totalamount + ", deliverydate=" + deliverydate + "]";
	}

	
	
}
