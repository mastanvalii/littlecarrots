package com.lc.sk.inventory.security.beans;


import java.io.Serializable;
import java.util.Arrays;

public class ComboProdDetailsBean implements Serializable
{
	
	private String comboid;
	private ProductidsfinalidsBean[] productidfinalprice;
	public ComboProdDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public ComboProdDetailsBean(String comboid, ProductidsfinalidsBean[] productidfinalprice) {
// 		super();
// 		this.comboid = comboid;
// 		this.productidfinalprice = productidfinalprice;
// 	}
	public String getComboid() {
		return comboid;
	}
	public void setComboid(String comboid) {
		this.comboid = comboid;
	}
	public ProductidsfinalidsBean[] getProductidfinalprice() {
		return productidfinalprice;
	}
	public void setProductidfinalprice(ProductidsfinalidsBean[] productidfinalprice) {
		this.productidfinalprice = productidfinalprice;
	}
	@Override
	public String toString() {
		return "ComboProdDetailsBean [comboid=" + comboid + ", productidfinalprice="
				+ Arrays.toString(productidfinalprice) + "]";
	}
	
	

}
