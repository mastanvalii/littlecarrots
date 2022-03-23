package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductDescriptions implements Serializable {

	private long descriptionid;

	private String description;

	public ProductDescriptions() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductDescriptions(long descriptionid, String description) {
// 		super();
// 		this.descriptionid = descriptionid;
// 		this.description = description;
// 	}

	public long getDescriptionid() {
		return descriptionid;
	}

	public void setDescriptionid(long descriptionid) {
		this.descriptionid = descriptionid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductDescriptions [descriptionid=" + descriptionid + ", description=" + description + "]";
	}

}
