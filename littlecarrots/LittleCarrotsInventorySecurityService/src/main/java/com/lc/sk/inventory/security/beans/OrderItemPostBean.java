package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class OrderItemPostBean implements Serializable{

 
	
	private String productid;
	
	private String quantity;
	
	private String productprice;

	public OrderItemPostBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductprice() {
		return productprice;
	}

	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}

// TODO Remove unused code found by UCDetector
// 	public OrderItemPostBean(String productid, String quantity, String productprice) {
// 		super();
// 		this.productid = productid;
// 		this.quantity = quantity;
// 		this.productprice = productprice;
// 	}

	@Override
	public String toString() {
		return "OrderItemPostBean [productid=" + productid + ", quantity=" + quantity + ", productprice=" + productprice
				+ "]";
	}


	
}
