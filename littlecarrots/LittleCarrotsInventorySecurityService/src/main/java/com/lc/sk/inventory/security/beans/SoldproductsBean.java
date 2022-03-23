package com.lc.sk.inventory.security.beans;

import java.io.Serializable;



public class SoldproductsBean implements Serializable{

	private long saleprid;
	
	private long inid;
	
	private long prid;
	
	private long qty;
	
	private float netamount;
	
	private float gst;
	
	private float discount;
	
	private float totalproductprice;
	
	private String status;

	public SoldproductsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SoldproductsBean(long saleprid, long inid, long prid, long qty, float netamount, float gst, float discount,
// 			float totalproductprice, String status) {
// 		super();
// 		this.saleprid = saleprid;
// 		this.inid = inid;
// 		this.prid = prid;
// 		this.qty = qty;
// 		this.netamount = netamount;
// 		this.gst = gst;
// 		this.discount = discount;
// 		this.totalproductprice = totalproductprice;
// 		this.status = status;
// 	}

	public long getSaleprid() {
		return saleprid;
	}

	public void setSaleprid(long saleprid) {
		this.saleprid = saleprid;
	}

	public long getInid() {
		return inid;
	}

	public void setInid(long inid) {
		this.inid = inid;
	}

	public long getPrid() {
		return prid;
	}

	public void setPrid(long prid) {
		this.prid = prid;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public float getNetamount() {
		return netamount;
	}

	public void setNetamount(float netamount) {
		this.netamount = netamount;
	}

	public float getGst() {
		return gst;
	}

	public void setGst(float gst) {
		this.gst = gst;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getTotalproductprice() {
		return totalproductprice;
	}

	public void setTotalproductprice(float totalproductprice) {
		this.totalproductprice = totalproductprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SoldproductsBean [saleprid=" + saleprid + ", inid=" + inid + ", prid=" + prid + ", qty=" + qty
				+ ", netamount=" + netamount + ", gst=" + gst + ", discount=" + discount + ", totalproductprice="
				+ totalproductprice + ", status=" + status + "]";
	}
	
	
}
