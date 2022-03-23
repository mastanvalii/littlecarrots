package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Date;

public class CodBean implements Serializable{

	private long paymenttransactionid;
	
	private Date orderdate;
	
	private long customerid;
	
	private long totalamount;
	
	private Date deliverydate;

	public CodBean() {
		super();
	}

	public CodBean(long paymenttransactionid, Date orderdate, long customerid, long totalamount, Date deliverydate) {
		super();
		this.paymenttransactionid = paymenttransactionid;
		this.orderdate = orderdate;
		this.customerid = customerid;
		this.totalamount = totalamount;
		this.deliverydate = deliverydate;
	}

	public long getPaymenttransactionid() {
		return paymenttransactionid;
	}

	public void setPaymenttransactionid(long paymenttransactionid) {
		this.paymenttransactionid = paymenttransactionid;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
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

	public Date getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	@Override
	public String toString() {
		return "CodBean [paymenttransactionid=" + paymenttransactionid + ", orderdate=" + orderdate + ", customerid="
				+ customerid + ", totalamount=" + totalamount + ", deliverydate=" + deliverydate + "]";
	}
	
	
	
	
}
