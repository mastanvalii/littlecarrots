package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class CartmanagementPostBean implements Serializable {
	
	private String cartid;
	private String customerid;
	private String productid;
	private String productprice;
	private String quantity;
	private String totalprice;
	
	public CartmanagementPostBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public CartmanagementPostBean(String cartid, String customerid, String productid, String productprice,
// 			String quantity, String totalprice) {
// 		super();
// 		this.cartid = cartid;
// 		this.customerid = customerid;
// 		this.productid = productid;
// 		this.productprice = productprice;
// 		this.quantity = quantity;
// 		this.totalprice = totalprice;
// 	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductprice() {
		return productprice;
	}

	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "CartmanagementPostBean [cartid=" + cartid + ", customerid=" + customerid + ", productid=" + productid
				+ ", productprice=" + productprice + ", quantity=" + quantity + ", totalprice=" + totalprice + "]";
	}
	
	
	

}
