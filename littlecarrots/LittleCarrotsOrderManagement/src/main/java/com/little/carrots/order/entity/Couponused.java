package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="couponused")
public class Couponused implements Serializable {

	/*
	create table couponused(
			couponusedid bigint auto_increment,
		    couponid bigint,
		    customerid bigint,
		    constraint couponused_pk primary key (couponusedid),
		    constraint couponused_fk foreign key (couponid) references coupons(couponid)
		    );
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="couponusedid")
	private long couponusedid;
	
	@Column(name="couponid")
	private long couponid;
	
	@Column(name="customerid")
	private long customerid;

	public Couponused() {
		super();
	}

	public long getCouponusedid() {
		return couponusedid;
	}

	public void setCouponusedid(long couponusedid) {
		this.couponusedid = couponusedid;
	}

	public long getCouponid() {
		return couponid;
	}

	public void setCouponid(long couponid) {
		this.couponid = couponid;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	@Override
	public String toString() {
		return "Couponused [couponusedid=" + couponusedid + ", couponid=" + couponid + ", customerid=" + customerid
				+ "]";
	}
	
	
}
