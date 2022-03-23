package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class CombosProductdetailsBean implements Serializable {

	private long combo_subid;
	private long comboid;
	private long productid;
	private float finalPrice;
	public CombosProductdetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public CombosProductdetailsBean(long combo_subid, long comboid, long productid, float finalPrice) {
// 		super();
// 		this.combo_subid = combo_subid;
// 		this.comboid = comboid;
// 		this.productid = productid;
// 		this.finalPrice = finalPrice;
// 	}
	public long getCombo_subid() {
		return combo_subid;
	}
	public void setCombo_subid(long combo_subid) {
		this.combo_subid = combo_subid;
	}
	public long getComboid() {
		return comboid;
	}
	public void setComboid(long comboid) {
		this.comboid = comboid;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public float getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}
	@Override
	public String toString() {
		return "CombosProductdetailsBean [combo_subid=" + combo_subid + ", comboid=" + comboid + ", productid="
				+ productid + ", finalPrice=" + finalPrice + "]";
	}
	
	
	
}
