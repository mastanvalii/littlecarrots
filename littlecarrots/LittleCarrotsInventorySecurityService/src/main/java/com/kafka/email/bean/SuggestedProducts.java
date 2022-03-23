package com.kafka.email.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;

public class SuggestedProducts implements Serializable {

	private String title;
	private String message1;
	private List<ProductFullDetailsWithImage> productid;
	public SuggestedProducts() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public SuggestedProducts(String title, String message1, List<ProductFullDetailsWithImage> productid) {
// 		super();
// 		this.title = title;
// 		this.message1 = message1;
// 		this.productid = productid;
// 	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public List<ProductFullDetailsWithImage> getProductid() {
		return productid;
	}
	public void setProductid(List<ProductFullDetailsWithImage> productid) {
		this.productid = productid;
	}
	@Override
	public String toString() {
		return "SuggestedProducts [title=" + title + ", message1=" + message1 + ", productid=" + productid + "]";
	}

	
}
