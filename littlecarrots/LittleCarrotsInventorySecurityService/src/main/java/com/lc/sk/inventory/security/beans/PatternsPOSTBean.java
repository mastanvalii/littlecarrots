package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class PatternsPOSTBean implements Serializable{
	
	private String description;
	private long productid;
	
	public PatternsPOSTBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public PatternsPOSTBean(String description, long productid) {
// 		super();
// 		this.description = description;
// 		this.productid = productid;
// 	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "PatternsPOSTBean [description, productid]";
	}

	

}
