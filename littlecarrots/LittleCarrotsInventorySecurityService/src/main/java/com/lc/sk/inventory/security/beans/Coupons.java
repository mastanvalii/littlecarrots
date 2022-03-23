package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class Coupons implements Serializable {

	
	private long couponid;
	private String coupon;
	private String startdate;
	private String enddate;
	private long discount;
	private long minbill;
	private String title;
	public Coupons() {
		super();
	}
	public Coupons(long couponid, String coupon, String startdate, String enddate, long discount, long minbill,
			String title) {
		super();
		this.couponid = couponid;
		this.coupon = coupon;
		this.startdate = startdate;
		this.enddate = enddate;
		this.discount = discount;
		this.minbill = minbill;
		this.title = title;
	}
	public long getCouponid() {
		return couponid;
	}
	public void setCouponid(long couponid) {
		this.couponid = couponid;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public long getMinbill() {
		return minbill;
	}
	public void setMinbill(long minbill) {
		this.minbill = minbill;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Coupons [couponid=" + couponid + ", coupon=" + coupon + ", startdate=" + startdate + ", enddate="
				+ enddate + ", discount=" + discount + ", minbill=" + minbill + ", title=" + title + "]";
	}
	
	
}
