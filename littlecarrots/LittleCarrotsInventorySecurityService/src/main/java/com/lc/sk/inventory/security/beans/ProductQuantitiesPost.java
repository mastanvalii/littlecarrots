package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductQuantitiesPost implements Serializable {
	
	private long quantity;
	private long sizeid;
	public ProductQuantitiesPost() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public ProductQuantitiesPost(long quantity, long sizeid) {
// 		super();
// 		this.quantity = quantity;
// 		this.sizeid = sizeid;
// 	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getSizeid() {
		return sizeid;
	}
	public void setSizeid(long sizeid) {
		this.sizeid = sizeid;
	}
	@Override
	public String toString() {
		return "ProductQuantitiesPost [quantity, sizeid]";
	}
	
	

}
