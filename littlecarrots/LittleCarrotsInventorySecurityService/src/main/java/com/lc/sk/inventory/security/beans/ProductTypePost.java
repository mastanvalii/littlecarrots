package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductTypePost implements Serializable {
	
	private long subcatid;

	private String producttypename;

	public ProductTypePost() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductTypePost(long subcatid, String producttypename) {
// 		super();
// 		this.subcatid = subcatid;
// 		this.producttypename = producttypename;
// 	}

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

	@Override
	public String toString() {
		return "ProductTypePost [subcatid, producttypename]";
	}
	
	

}
