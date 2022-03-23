package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductidsBean implements Serializable {
	
	private Long productid;

	public ProductidsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductidsBean(Long productid) {
// 		super();
// 		this.productid = productid;
// 	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "ProductidsBean [productid=" + productid + "]";
	}
	
	

}
