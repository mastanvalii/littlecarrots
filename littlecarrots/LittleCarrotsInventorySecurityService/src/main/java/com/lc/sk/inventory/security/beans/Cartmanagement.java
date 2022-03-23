package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Cartmanagement implements Serializable{

	private String cartid;
	private String customerid;
	private String productid;
	private String productprice;
	private String quantity;
	private String cartdate;
	private String totalprice;
	public Cartmanagement() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getCartdate() {
		return cartdate;
	}
	public void setCartdate(String cartdate) {
		this.cartdate = cartdate;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
// TODO Remove unused code found by UCDetector
// 	public Cartmanagement(String cartid, String customerid, String productid, String productprice, String quantity,
// 			String cartdate, String totalprice) {
// 		super();
// 		this.cartid = cartid;
// 		this.customerid = customerid;
// 		this.productid = productid;
// 		this.productprice = productprice;
// 		this.quantity = quantity;
// 		this.cartdate = cartdate;
// 		this.totalprice = totalprice;
// 	}
	
	@Override
	public String toString() {
		return "Cartmanagement [cartid=" + cartid + ", customerid=" + customerid + ", productid=" + productid
				+ ", productprice=" + productprice + ", quantity=" + quantity + ", cartdate=" + cartdate
				+ ", totalprice=" + totalprice + "]";
	}
	
	
}
