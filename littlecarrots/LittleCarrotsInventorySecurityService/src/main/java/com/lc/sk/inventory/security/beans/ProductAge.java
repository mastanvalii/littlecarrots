package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductAge implements Serializable {

	private String ageid;

	private String des;

	public ProductAge() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public ProductAge(String ageid, String des) {
// 		super();
// 		this.ageid = ageid;
// 		this.des = des;
// 	}

	public String getAgeid() {
		return ageid;
	}

	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "ProductAge [ageid=" + ageid + ", des=" + des + "]";
	}



}
