package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductType implements Serializable {

	private long protypeid;

	private long subcatid;

	private String producttypename;

	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductType(long protypeid, long subcatid, String producttypename) {
// 		super();
// 		this.protypeid = protypeid;
// 		this.subcatid = subcatid;
// 		this.producttypename = producttypename;
// 	}

	@Override
	public String toString() {
		return "ProductType [protypeid=" + protypeid + ", subcatid=" + subcatid + ", producttypename=" + producttypename
				+ "]";
	}

	public long getProtypeid() {
		return protypeid;
	}

	public void setProtypeid(long protypeid) {
		this.protypeid = protypeid;
	}

	public long getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}

	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

}
