package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductidsfinalidsBean  implements Serializable {

	private String productid;
	private String finalprice;
	public ProductidsfinalidsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public ProductidsfinalidsBean(String productid, String finalprice) {
// 		super();
// 		this.productid = productid;
// 		this.finalprice = finalprice;
// 	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getFinalprice() {
		return finalprice;
	}
	public void setFinalprice(String finalprice) {
		this.finalprice = finalprice;
	}
	@Override
	public String toString() {
		return "Productids_finalidsBean [productid=" + productid + ", finalprice=" + finalprice + "]";
	}
	
	
	
}
