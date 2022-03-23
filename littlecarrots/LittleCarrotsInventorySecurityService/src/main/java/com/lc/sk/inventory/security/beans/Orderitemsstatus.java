package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;



public class Orderitemsstatus implements Serializable {

	private long itemstatusid;
	
	private long orderitemsid;
	
	private long orderid;
	
	private long ordercodeid;
	
	private Timestamp ondate;
	
	private String information;

	public Orderitemsstatus() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Orderitemsstatus(long itemstatusid, long orderitemsid, long orderid, long ordercodeid, Timestamp ondate,
// 			String information) {
// 		super();
// 		this.itemstatusid = itemstatusid;
// 		this.orderitemsid = orderitemsid;
// 		this.orderid = orderid;
// 		this.ordercodeid = ordercodeid;
// 		this.ondate = ondate;
// 		this.information = information;
// 	}

	public long getItemstatusid() {
		return itemstatusid;
	}

	public void setItemstatusid(long itemstatusid) {
		this.itemstatusid = itemstatusid;
	}

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

	public long getOrdercodeid() {
		return ordercodeid;
	}

	public void setOrdercodeid(long ordercodeid) {
		this.ordercodeid = ordercodeid;
	}

	public Timestamp getOndate() {
		return ondate;
	}

	public void setOndate(Timestamp ondate) {
		this.ondate = ondate;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Orderitemsstatus [itemstatusid=" + itemstatusid + ", orderitemsid=" + orderitemsid + ", orderid="
				+ orderid + ", ordercodeid=" + ordercodeid + ", ondate=" + ondate + ", information=" + information
				+ "]";
	}


	
}
