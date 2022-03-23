package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="packageshipping")
public class Packageshipping implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	private long sid;
	
	@Column(name="orderid")
	private long orderid;
	
	@Column(name="orderitemsid")
	private long orderitemsid;
	
	@Column(name="shippingorderid")
	private String shippingorderid;
	
	@Column(name="shippingservice")
	private String shippingservice;
	
	@Column(name="awb")
	private String awb;
	
	@Column(name="trackingurl")
	private String trackingurl;
	
	@Column(name="information")
	private String information;

	public Packageshipping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Packageshipping( long orderid, long orderitemsid, String shippingorderid, String shippingservice,
			String awb, String trackingurl, String information) {
		super();
		//this.sid = sid;
		this.orderid = orderid;
		this.orderitemsid = orderitemsid;
		this.shippingorderid = shippingorderid;
		this.shippingservice = shippingservice;
		this.awb = awb;
		this.trackingurl = trackingurl;
		this.information = information;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public long getOrderitemsid() {
		return orderitemsid;
	}

	public void setOrderitemsid(long orderitemsid) {
		this.orderitemsid = orderitemsid;
	}

	public String getShippingorderid() {
		return shippingorderid;
	}

	public void setShippingorderid(String shippingorderid) {
		this.shippingorderid = shippingorderid;
	}

	public String getShippingservice() {
		return shippingservice;
	}

	public void setShippingservice(String shippingservice) {
		this.shippingservice = shippingservice;
	}

	public String getAwb() {
		return awb;
	}

	public void setAwb(String awb) {
		this.awb = awb;
	}

	public String getTrackingurl() {
		return trackingurl;
	}

	public void setTrackingurl(String trackingurl) {
		this.trackingurl = trackingurl;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Packageshipping [sid=" + sid + ", orderid=" + orderid + ", orderitemsid=" + orderitemsid
				+ ", shippingorderid=" + shippingorderid + ", shippingservice=" + shippingservice + ", awb=" + awb
				+ ", trackingurl=" + trackingurl + ", information=" + information + "]";
	}
	
	
}
