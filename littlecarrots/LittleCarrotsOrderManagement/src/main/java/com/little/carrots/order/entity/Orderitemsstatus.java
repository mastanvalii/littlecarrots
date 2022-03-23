package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderitemsstatus")
public class Orderitemsstatus implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemstatusid")
	private long itemstatusid;
	
	@Column(name="orderitemsid")
	private long orderitemsid;
	
	@Column(name="orderid")
	private long orderid;
	
	@Column(name="ordercodeid")
	private long ordercodeid;
	
	@Column(name="ondate")
	private Timestamp ondate;
	
	@Column(name="information")
	private String information;

	public Orderitemsstatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orderitemsstatus( long orderitemsid, long orderid, long ordercodeid, Timestamp ondate,
			String information) {
		super();
	//	this.itemstatusid = itemstatusid;
		this.orderitemsid = orderitemsid;
		this.orderid = orderid;
		this.ordercodeid = ordercodeid;
		this.ondate = ondate;
		this.information = information;
	}

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
