package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class UnFilledProductsBean implements Serializable {

	private Long productid;
	private String title;
	private String subtitle;
	
	
	public UnFilledProductsBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UnFilledProductsBean(Long productid, String title, String subtitle) {
		super();
		this.productid = productid;
		this.title = title;
		this.subtitle = subtitle;
	}


	public Long getProductid() {
		return productid;
	}


	public void setProductid(Long productid) {
		this.productid = productid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	@Override
	public String toString() {
		return "UnFilledProductsBean [productid=" + productid + ", title=" + title + ", subtitle=" + subtitle + "]";
	}
	
	
	
}
