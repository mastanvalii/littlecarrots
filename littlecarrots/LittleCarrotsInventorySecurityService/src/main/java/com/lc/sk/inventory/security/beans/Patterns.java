package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Patterns implements Serializable {

	private long patid;

	private String description;

	private long productid;

	public Patterns() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Patterns(long patid, String description, long productid) {
// 		super();
// 		this.patid = patid;
// 		this.description = description;
// 		this.productid = productid;
// 	}

	@Override
	public String toString() {
		return "Patterns [patid=" + patid + ", description=" + description + ", productid=" + productid + "]";
	}

	public long getPatid() {
		return patid;
	}

	public void setPatid(long patid) {
		this.patid = patid;
	}

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

}
