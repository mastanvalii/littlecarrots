package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductVariationFullInfoBean implements Serializable  {
	
	private String productid;
	private String sizeid;
	private String ageid;
	private String hashcode;
	public ProductVariationFullInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public ProductVariationFullInfoBean(String productid, String sizeid, String ageid, String hashcode) {
// 		super();
// 		this.productid = productid;
// 		this.sizeid = sizeid;
// 		this.ageid = ageid;
// 		this.hashcode = hashcode;
// 	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getSizeid() {
		return sizeid;
	}
	public void setSizeid(String sizeid) {
		this.sizeid = sizeid;
	}
	public String getAgeid() {
		return ageid;
	}
	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	@Override
	public String toString() {
		return "ProductVariationFullInfoBean [productid=" + productid + ", sizeid=" + sizeid + ", ageid=" + ageid
				+ ", hashcode=" + hashcode + "]";
	}
	

	
}
