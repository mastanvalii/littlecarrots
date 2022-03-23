package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class PricesTablePost implements Serializable {

	private long priceId;

	private float mrp;

	private long productid;

	private float lcPrice;

	private float tax;

	private float sellingPrice;
	
	private float discount;

	private float finalPrice;

	public PricesTablePost() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public PricesTablePost(long priceId, float mrp, long productid, float lcPrice, float tax, float sellingPrice,float discount,
// 			float finalPrice) {
// 		super();
// 		this.priceId = priceId;
// 		this.mrp = mrp;
// 		this.productid = productid;
// 		this.lcPrice = lcPrice;
// 		this.tax = tax;
// 		this.sellingPrice = sellingPrice;
// 		this.discount = discount;
// 		this.finalPrice = finalPrice;
// 	}

	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductId(long productid) {
		this.productid = productid;
	}

	public float getLcPrice() {
		return lcPrice;
	}

	public void setLcPrice(float lcPrice) {
		this.lcPrice = lcPrice;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

	
	public String toString1() {
		return "PricesTable [priceId, mrp, productid, lcPrice,tax, sellingPrice,discount, finalPrice]";
	}

	@Override
	public String toString() {
		return "PricesTable [priceId=" + priceId + ", mrp=" + mrp + ", productid=" + productid + ", lcPrice=" + lcPrice
				+ ", tax=" + tax + ", sellingPrice=" + sellingPrice + ",discount=" + discount+", finalPrice=" + finalPrice + "]";
	}

}
