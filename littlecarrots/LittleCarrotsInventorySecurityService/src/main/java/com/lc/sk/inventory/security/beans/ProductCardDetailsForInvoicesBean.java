package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductCardDetailsForInvoicesBean implements Serializable{
	
	private String productid;
	private String genderid;
	private String ageid;
	private String quantity;
	private String mrp;
	private String lcprice;
	private String tax;
	private String sellingprice;
	private String finalprice;
	private String discount;
	private String producttitle;
	
	public ProductCardDetailsForInvoicesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public ProductCardDetailsForInvoicesBean(String productid, String genderid, String ageid, String quantity,
// 			String mrp, String lcprice, String tax, String sellingprice, String finalprice, String discount,
// 			String producttitle) {
// 		super();
// 		this.productid = productid;
// 		this.genderid = genderid;
// 		this.ageid = ageid;
// 		this.quantity = quantity;
// 		this.mrp = mrp;
// 		this.lcprice = lcprice;
// 		this.tax = tax;
// 		this.sellingprice = sellingprice;
// 		this.finalprice = finalprice;
// 		this.discount = discount;
// 		this.producttitle = producttitle;
// 	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getGenderid() {
		return genderid;
	}

	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}

	public String getAgeid() {
		return ageid;
	}

	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getLcprice() {
		return lcprice;
	}

	public void setLcprice(String lcprice) {
		this.lcprice = lcprice;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(String sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getFinalprice() {
		return finalprice;
	}

	public void setFinalprice(String finalprice) {
		this.finalprice = finalprice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getProducttitle() {
		return producttitle;
	}

	public void setProducttitle(String producttitle) {
		this.producttitle = producttitle;
	}

	@Override
	public String toString() {
		return "ProductCardDetailsForInvoicesBean [productid=" + productid + ", genderid=" + genderid + ", ageid="
				+ ageid + ", quantity=" + quantity + ", mrp=" + mrp + ", lcprice=" + lcprice + ", tax=" + tax
				+ ", sellingprice=" + sellingprice + ", finalprice=" + finalprice + ", discount=" + discount
				+ ", producttitle=" + producttitle + "]";
	}
	
	

}
