package com.lilttle.carrots.imgs.beans;

public class DbBean {
	private String productid;
	private String image;
	public DbBean() {
		super();
	}
	public DbBean(String productid, String image) {
		super();
		this.productid = productid;
		this.image = image;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String images) {
		this.image = images;
	}
	@Override
	public String toString() {
		return "DbBean [productid=" + productid + ", images=" + image + "]";
	}
	
	
	
}
