package com.little.carrots.order.bean;

import java.io.Serializable;


public class SoldProducts implements Serializable {

	private long itemssoldid;
	private long skuid;
	private long qty;
	private double netamount;
	private long invoiceid;
	
	
	public SoldProducts() {
		// TODO Auto-generated constructor stub
	}


	public SoldProducts(long itemssoldid, long skuid, long qty, double netamount,long invoiceid ) {
		super();
		this.itemssoldid = itemssoldid;
		this.skuid = skuid;
		this.qty = qty;
		this.netamount = netamount;
		this.invoiceid = invoiceid;
	}


	public long getInvoiceid() {
		return invoiceid;
	}


	public void setInvoiceid(long invoiceid) {
		this.invoiceid = invoiceid;
	}


	public long getItemssoldid() {
		return itemssoldid;
	}


	public void setItemssoldid(long itemssoldid) {
		this.itemssoldid = itemssoldid;
	}


	public long getSkuid() {
		return skuid;
	}


	public void setSkuid(long skuid) {
		this.skuid = skuid;
	}


	public long getQty() {
		return qty;
	}


	public void setQty(long qty) {
		this.qty = qty;
	}


	public double getNetamount() {
		return netamount;
	}


	public void setNetamount(double netamount) {
		this.netamount = netamount;
	}


	@Override
	public String toString() {
		return "SoldProducts [itemssoldid=" + itemssoldid + ", skuid=" + skuid + ", qty=" + qty + ", netamount="
				+ netamount + "]";
	}
	
	

}
