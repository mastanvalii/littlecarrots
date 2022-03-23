package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductDescriptionsPost implements Serializable{

	private String description;

	public ProductDescriptionsPost() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductDescriptionsPost(String description) {
// 		super();
// 		this.description = description;
// 	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductDescriptionsPost [description]";
	}
	
	
}
