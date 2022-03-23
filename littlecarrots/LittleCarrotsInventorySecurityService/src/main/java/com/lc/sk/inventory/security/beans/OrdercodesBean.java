package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

import javax.persistence.Column;

public class OrdercodesBean implements Serializable {
	
	private long ordercodeid;
	
	private String remark;

	public OrdercodesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public OrdercodesBean(long ordercodeid, String remark) {
// 		super();
// 		this.ordercodeid = ordercodeid;
// 		this.remark = remark;
// 	}

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
		return "ordercodes [ordercodeid=" + ordercodeid + ", remark=" + remark + "]";
	}
	
	

}
