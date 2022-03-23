package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class CustAddBean implements Serializable{
	private String userid;
	private String addressid;
	private String msg;
	public CustAddBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public CustAddBean(String userid, String addressid, String msg) {
// 		super();
// 		this.userid = userid;
// 		this.addressid = addressid;
// 		this.msg = msg;
// 	}
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "CustAddBean [userid=" + userid + ", addressid=" + addressid + ", msg=" + msg + "]";
	}
	
	

}
