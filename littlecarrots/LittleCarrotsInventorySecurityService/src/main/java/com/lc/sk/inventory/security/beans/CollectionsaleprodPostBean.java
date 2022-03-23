package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;

public class CollectionsaleprodPostBean implements Serializable {
	
	private String csid;
	private String id;
	private String productid[];
	
	public CollectionsaleprodPostBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public CollectionsaleprodPostBean(String csid, String id, String[] productid) {
// 		super();
// 		this.csid = csid;
// 		this.id = id;
// 		this.productid = productid;
// 	}

	public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getProductid() {
		return productid;
	}

	public void setProductid(String[] productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "CollectionsaleprodPostBean [csid=" + csid + ", id=" + id + ", productid=" + Arrays.toString(productid)
				+ "]";
	}
	
}