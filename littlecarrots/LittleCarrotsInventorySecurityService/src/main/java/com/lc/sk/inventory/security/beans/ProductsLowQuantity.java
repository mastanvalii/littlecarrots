package com.lc.sk.inventory.security.beans;

public class ProductsLowQuantity {

	private long productid;
	
	private String message;

	public ProductsLowQuantity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductsLowQuantity(long productid, String message) {
		super();
		this.productid = productid;
		this.message = message;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ProductsLowQuantity [productid=" + productid + ", message=" + message + "]";
	}
	
	
	
	
}
