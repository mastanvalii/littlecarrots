package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

import javax.persistence.Column;

public class CollectionsaleprodBean implements Serializable{
	
	private long csid;
	private String id;
	private long productid;
	
	public CollectionsaleprodBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public CollectionsaleprodBean(long csid, String id, long productid) {
// 		super();
// 		this.csid = csid;
// 		this.id = id;
// 		this.productid = productid;
// 	}
	
	

	public long getCsid() {
		return csid;
	}

	public void setCsid(long csid) {
		this.csid = csid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "CollectionsaleprodBean [csid=" + csid + ", id=" + id + ", productid=" + productid + "]";
	}

	
	
}
