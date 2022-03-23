package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SimilarproductsBean implements Serializable {

	private long uid;
	private long pvid;
	private String productid;
	
	public SimilarproductsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SimilarproductsBean(long uid, long pvid, String productid) {
// 		super();
// 		this.uid = uid;
// 		this.pvid = pvid;
// 		this.productid = productid;
// 	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getPvid() {
		return pvid;
	}

	public void setPvid(long pvid) {
		this.pvid = pvid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "SimilarproductsBean [uid=" + uid + ", pvid=" + pvid + ", productid=" + productid + "]";
	}
	
	
	
}
