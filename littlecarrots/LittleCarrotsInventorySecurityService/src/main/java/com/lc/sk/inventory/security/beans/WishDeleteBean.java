package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;

public class WishDeleteBean implements Serializable {

	private String uniqueid;
	private String[] productids;
	public WishDeleteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public WishDeleteBean(String uniqueid, String[] productids) {
// 		super();
// 		this.uniqueid = uniqueid;
// 		this.productids = productids;
// 	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String[] getProductids() {
		return productids;
	}
	public void setProductids(String[] productids) {
		this.productids = productids;
	}
	@Override
	public String toString() {
		return "WishDeleteBean [uniqueid=" + uniqueid + ", productids=" + Arrays.toString(productids) + "]";
	}
	
	
}
