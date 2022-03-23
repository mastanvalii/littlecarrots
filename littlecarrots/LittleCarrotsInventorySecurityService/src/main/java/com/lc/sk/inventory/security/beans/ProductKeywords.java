package com.lc.sk.inventory.security.beans;

import java.io.Serializable;



public class ProductKeywords implements Serializable {

	
	private long keywordid;	
	
	private long productid;	
	
	private String keywords;

	public ProductKeywords() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public long getKeywordid() {
		return keywordid;
	}



	public void setKeywordid(long keywordid) {
		this.keywordid = keywordid;
	}

	public long getProductid() {
		return productid;
	}


	public void setProductid(long productid) {
		this.productid = productid;
	}



	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

// TODO Remove unused code found by UCDetector
// 	public ProductKeywords(long productid, String keywords) {
// 		super();
// 		this.productid = productid;
// 		this.keywords = keywords;
// 	}

	@Override
	public String toString() {
		return "ProductKeywords [keywordid=\" + keywordid + \",productid=" + productid + ", keywords=" + keywords + "]";
	}
	
	
	
	
	
	

}
