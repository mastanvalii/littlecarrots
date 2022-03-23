package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SoldProductsPostBean implements Serializable {
	
	private String prid;
	private String qty;
	private String netamount;
	private String gst;
	private String discount;
	private String totalproductprice;
	public SoldProductsPostBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public SoldProductsPostBean(String prid, String qty, String netamount, String gst, String discount,
// 			String totalproductprice) {
// 		super();
// 		this.prid = prid;
// 		this.qty = qty;
// 		this.netamount = netamount;
// 		this.gst = gst;
// 		this.discount = discount;
// 		this.totalproductprice = totalproductprice;
// 	}
	public String getPrid() {
		return prid;
	}
	public void setPrid(String prid) {
		this.prid = prid;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getNetamount() {
		return netamount;
	}
	public void setNetamount(String netamount) {
		this.netamount = netamount;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTotalproductprice() {
		return totalproductprice;
	}
	public void setTotalproductprice(String totalproductprice) {
		this.totalproductprice = totalproductprice;
	}
	@Override
	public String toString() {
		return "SoldProductsPostBean [prid=" + prid + ", qty=" + qty + ", netamount=" + netamount + ", gst=" + gst
				+ ", discount=" + discount + ", totalproductprice=" + totalproductprice + "]";
	}
	
	
	
	

}
