package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class PackageshippingBean implements Serializable {
	
	private long sid;
	private long orderid;
	private long orderitemsid;
	private String shippingorderid;
	private String shippingservice;
	private String awb;
	private String trackingurl;
	private String information;
	
	public PackageshippingBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public PackageshippingBean(long sid, long orderid, long orderitemsid, String shippingorderid,
// 			String shippingservice, String awb, String trackingurl, String information) {
// 		super();
// 		this.sid = sid;
// 		this.orderid = orderid;
// 		this.orderitemsid = orderitemsid;
// 		this.shippingorderid = shippingorderid;
// 		this.shippingservice = shippingservice;
// 		this.awb = awb;
// 		this.trackingurl = trackingurl;
// 		this.information = information;
// 	}

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
		return "PackageshippingBean [sid=" + sid + ", orderid=" + orderid + ", orderitemsid=" + orderitemsid
				+ ", shippingorderid=" + shippingorderid + ", shippingservice=" + shippingservice + ", awb=" + awb
				+ ", trackingurl=" + trackingurl + ", information=" + information + "]";
	}
	
	
}

