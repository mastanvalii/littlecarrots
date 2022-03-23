package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductvariationBean implements Serializable {

	private long pvid;
	private String pvtype;
	
	public ProductvariationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductvariationBean(long pvid, String pvtype) {
// 		super();
// 		this.pvid = pvid;
// 		this.pvtype = pvtype;
// 	}

	public long getPvid() {
		return pvid;
	}

	public void setPvid(long pvid) {
		this.pvid = pvid;
	}

	public String getPvtype() {
		return pvtype;
	}

	public void setPvtype(String pvtype) {
		this.pvtype = pvtype;
	}

	@Override
	public String toString() {
		return "ProductvariationBean [pvid=" + pvid + ", pvtype=" + pvtype + "]";
	}
	
	
}
