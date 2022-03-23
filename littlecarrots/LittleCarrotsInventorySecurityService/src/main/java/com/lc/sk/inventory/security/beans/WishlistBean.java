package com.lc.sk.inventory.security.beans;


public class WishlistBean {
	
	private long wishlistid;
	
	private long customerid;
	
	private long productid;

	public WishlistBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public WishlistBean(long customerid, long productid) {
// 		super();
// 		this.customerid = customerid;
// 		this.productid = productid;
// 	}

	public long getWishlistid() {
		return wishlistid;
	}

	public void setWishlistid(long wishlistid) {
		this.wishlistid = wishlistid;
	}

	public long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "WishlistBean [wishlistid=" + wishlistid + ", customerid=" + customerid + ", productid=" + productid
				+ "]";
	}
	
	
	
}
