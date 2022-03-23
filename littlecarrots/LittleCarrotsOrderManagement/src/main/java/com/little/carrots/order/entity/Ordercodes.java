package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ordercodes")
public class Ordercodes implements Serializable {
	
	/*
	 create table ordercodes(
	ordercodeid bigint,
	remark text,
	constraint ordercode_pk primary key (ordercodeid)
	);
	 */
	
	@Id
	@Column(name="ordercodeid")
	private long ordercodeid;
	
	@Column(name="remark")
	private String remark;

	public Ordercodes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ordercodes(long ordercodeid, String remark) {
		super();
		this.ordercodeid = ordercodeid;
		this.remark = remark;
	}

	public long getOrdercodeid() {
		return ordercodeid;
	}

	public void setOrdercodeid(long ordercodeid) {
		this.ordercodeid = ordercodeid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Ordercodes [ordercodeid=" + ordercodeid + ", remark=" + remark + "]";
	}

	
	
	

}
