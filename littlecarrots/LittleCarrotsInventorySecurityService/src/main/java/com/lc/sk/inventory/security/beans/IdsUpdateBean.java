package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class IdsUpdateBean implements Serializable{
	
	private String orderid;
	private String userid;
	private String addressid;
	public IdsUpdateBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public IdsUpdateBean(String orderid, String userid, String addressid) {
// 		super();
// 		this.orderid = orderid;
// 		this.userid = userid;
// 		this.addressid = addressid;
// 	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	@Override
	public String toString() {
		return "IdsUpdateBean [orderid=" + orderid + ", userid=" + userid + ", addressid=" + addressid + "]";
	}
	
	

}
