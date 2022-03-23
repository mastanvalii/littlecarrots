package com.lc.sk.auth.bean;

import java.io.Serializable;

public class CustADDBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2052189655461838932L;
	private String userid;
	private String addressid;
	private String msg;
	public CustADDBean() {
		super();
	}
// TODO Remove unused code found by UCDetector
// 	public CustADDBean(String userid, String addressid, String msg) {
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
		return "CustADDBean [userid=" + userid + ", addressid=" + addressid + ", msg=" + msg + "]";
	}
	
	
}
