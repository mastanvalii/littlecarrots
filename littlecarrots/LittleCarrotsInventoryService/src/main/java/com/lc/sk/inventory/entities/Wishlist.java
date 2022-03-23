package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wishlist")
public class Wishlist implements Serializable {

	@Id
	@Column(name="wishlistid")
	private long wishlistid;
	
	@Column(name="customerid")
	private long customerid;
	
	@Column(name="productid")
	private long productid;
	
	public Wishlist() {
		// TODO Auto-generated constructor stub
	}

	public Wishlist(long customerid, long productid) {
		super();
		this.customerid = customerid;
		this.productid = productid;
	}

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
		return "Wishlist [wishlistid=" + wishlistid + ", customerid=" + customerid + ", productid=" + productid + "]";
	}
	
	
	

}
