package com.little.carrots.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupons")
public class Coupons implements Serializable {
//
//	create table coupons(
//			couponid bigint auto_increment,
//			coupon varchar(200),
//			startdate DATEtime,
//			enddate DATEtime,
//			discount int,
//			minbill int,
//			title varchar(200),
//			constraint coupons_pk primary key (couponid)
//			);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="couponid")
	private long couponid;
	
	@Column(name="coupon")
	private String coupon;
	
	@Column(name="startdate")
	private Timestamp startdate;
	
	@Column(name="enddate")
	private Timestamp enddate;
	
	@Column(name="discount")
	private long discount;
	
	@Column(name="minbill")
	private long minbill;
	
	@Column(name="title")
	private String title;
	

	public Coupons() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Coupons(String coupon, Timestamp startdate, Timestamp enddate, long discount, long minbill, String title) {
		super();
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


	public Timestamp getStartdate() {
		return startdate;
	}


	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}


	public Timestamp getEnddate() {
		return enddate;
	}


	public void setEnddate(Timestamp enddate) {
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


	public void setMinbill(int minbill) {
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
