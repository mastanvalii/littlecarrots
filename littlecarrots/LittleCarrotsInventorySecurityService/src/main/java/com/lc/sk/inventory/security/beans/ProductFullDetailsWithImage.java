package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.List;

public class ProductFullDetailsWithImage implements Serializable {

	private String productid;
	private String descriptionid;
	private String batchid;
	private String genderid;
	private String categoryid;
	private String subcategoryid;
	private String seasionid;
	private String occasionid;
	private String ageid;
	private String materialid;
	private String colorid;
	private String custid;
	private String productstatus;
	private String producttitle;
	private String productsubtitle;
	private String warehouseid;
	private String batchstatus;
	private String sellerid;
	private String category;
	private String subcategory;
	private String season;
	private String occasion;
	private String agedescription;
	private String materialused;
	private String color;
	private String colorhashcode;
	private String quantity;
	private String sizeid;
	private String pieceid;
	private String setofpieces;
	private String patternid;
	private String pattern;
	private String priceid;
	private String mrp;
	private String lcprice;
	private String tax;
	private String sellingprice;
	private String finalprice;
	private String discount;
	private String productdescription;
	
	
	/*approval table code */
	private String approvalid;
	private String approvalstatus;
	private String approvaltext;
	private String approvalcomment;
	
	/*---------------------*/
	
	
	/* Collection sale details */
	//public String collectionsaletitle;
	private String collectionsalebadge;
//	public String collectionsaledisplay;
	
	private FileData imagefile;

	public ProductFullDetailsWithImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductFullDetailsWithImage(ProductFullDetails pfd, FileData imagefile) {
		this.imagefile = imagefile;
		this.productid = pfd.getProductid();
		this.descriptionid = pfd.getDescriptionid();
		this.batchid = pfd.getBatchid();
		this.genderid = pfd.getGenderid();
		this.categoryid = pfd.getCategoryid();
		this.subcategoryid = pfd.getSubcategoryid();
		this.seasionid = pfd.getSeasionid();
		this.occasionid = pfd.getOccasionid();
		this.ageid = pfd.getAgeid();
		this.materialid = pfd.getMaterialid();
		this.colorid = pfd.getColorid();
		this.custid = pfd.getCustid();
		this.productstatus = pfd.getProductstatus();
		this.producttitle = pfd.getProducttitle();
		this.productsubtitle = pfd.getProductsubtitle();
		this.warehouseid = pfd.getWarehouseid();
		this.batchstatus = pfd.getBatchstatus();
		this.sellerid = pfd.getSellerid();
		this.category = pfd.getCategory();
		this.subcategory = pfd.getSubcategory();
		this.season = pfd.getSeason();
		this.occasion = pfd.getOccasion();
		this.agedescription = pfd.getAgedescription();
		this.materialused = pfd.getMaterialused();
		this.color = pfd.getColor();
		this.colorhashcode = pfd.getColorhashcode();
		this.quantity = pfd.getQuantity();
		this.sizeid = pfd.getSizeid();
		this.pieceid = pfd.getPieceid();
		this.setofpieces = pfd.getSetofpieces();
		this.patternid = pfd.getPatternid();
		this.pattern = pfd.getPattern();
		this.priceid = pfd.getPriceid();
		this.mrp = pfd.getMrp();
		this.lcprice = pfd.getLcprice();
		this.tax = pfd.getTax();
		this.sellingprice = pfd.getSellingprice();
		this.finalprice = pfd.getFinalprice();
		this.discount = pfd.getDiscount();
		this.productdescription = pfd.getProductdescription();
		this.approvalid = pfd.getApprovalid();
		this.approvalstatus = pfd.getApprovalstatus();
		this.approvaltext = pfd.getApprovaltext();
		this.approvalcomment = pfd.getApprovalcomment();
		this.collectionsalebadge = collectionsalebadge;
	}

// TODO Remove unused code found by UCDetector
// 	public ProductFullDetailsWithImage(String productid, String descriptionid, String batchid, String genderid,
// 			String categoryid, String subcategoryid, String seasionid, String occasionid, String ageid,
// 			String materialid, String colorid, String custid, String productstatus, String producttitle,
// 			String productsubtitle, String warehouseid, String batchstatus, String sellerid, String category,
// 			String subcategory, String season, String occasion, String agedescription, String materialused,
// 			String color, String colorhashcode, String quantity, String sizeid, String pieceid, String setofpieces,
// 			String patternid, String pattern, String priceid, String mrp, String lcprice, String tax,
// 			String sellingprice, String finalprice, String discount, String productdescription, String approvalid,
// 			String approvalstatus, String approvaltext, String approvalcomment, String collectionsalebadge,
// 			FileData imagefile) {
// 		super();
// 		this.productid = productid;
// 		this.descriptionid = descriptionid;
// 		this.batchid = batchid;
// 		this.genderid = genderid;
// 		this.categoryid = categoryid;
// 		this.subcategoryid = subcategoryid;
// 		this.seasionid = seasionid;
// 		this.occasionid = occasionid;
// 		this.ageid = ageid;
// 		this.materialid = materialid;
// 		this.colorid = colorid;
// 		this.custid = custid;
// 		this.productstatus = productstatus;
// 		this.producttitle = producttitle;
// 		this.productsubtitle = productsubtitle;
// 		this.warehouseid = warehouseid;
// 		this.batchstatus = batchstatus;
// 		this.sellerid = sellerid;
// 		this.category = category;
// 		this.subcategory = subcategory;
// 		this.season = season;
// 		this.occasion = occasion;
// 		this.agedescription = agedescription;
// 		this.materialused = materialused;
// 		this.color = color;
// 		this.colorhashcode = colorhashcode;
// 		this.quantity = quantity;
// 		this.sizeid = sizeid;
// 		this.pieceid = pieceid;
// 		this.setofpieces = setofpieces;
// 		this.patternid = patternid;
// 		this.pattern = pattern;
// 		this.priceid = priceid;
// 		this.mrp = mrp;
// 		this.lcprice = lcprice;
// 		this.tax = tax;
// 		this.sellingprice = sellingprice;
// 		this.finalprice = finalprice;
// 		this.discount = discount;
// 		this.productdescription = productdescription;
// 		this.approvalid = approvalid;
// 		this.approvalstatus = approvalstatus;
// 		this.approvaltext = approvaltext;
// 		this.approvalcomment = approvalcomment;
// 		this.collectionsalebadge = collectionsalebadge;
// 		this.imagefile = imagefile;
// 	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getDescriptionid() {
		return descriptionid;
	}

	public void setDescriptionid(String descriptionid) {
		this.descriptionid = descriptionid;
	}

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getGenderid() {
		return genderid;
	}

	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getSubcategoryid() {
		return subcategoryid;
	}

	public void setSubcategoryid(String subcategoryid) {
		this.subcategoryid = subcategoryid;
	}

	public String getSeasionid() {
		return seasionid;
	}

	public void setSeasionid(String seasionid) {
		this.seasionid = seasionid;
	}

	public String getOccasionid() {
		return occasionid;
	}

	public void setOccasionid(String occasionid) {
		this.occasionid = occasionid;
	}

	public String getAgeid() {
		return ageid;
	}

	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}

	public String getMaterialid() {
		return materialid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

	public String getColorid() {
		return colorid;
	}

	public void setColorid(String colorid) {
		this.colorid = colorid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getProductstatus() {
		return productstatus;
	}

	public void setProductstatus(String productstatus) {
		this.productstatus = productstatus;
	}

	public String getProducttitle() {
		return producttitle;
	}

	public void setProducttitle(String producttitle) {
		this.producttitle = producttitle;
	}

	public String getProductsubtitle() {
		return productsubtitle;
	}

	public void setProductsubtitle(String productsubtitle) {
		this.productsubtitle = productsubtitle;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getBatchstatus() {
		return batchstatus;
	}

	public void setBatchstatus(String batchstatus) {
		this.batchstatus = batchstatus;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getAgedescription() {
		return agedescription;
	}

	public void setAgedescription(String agedescription) {
		this.agedescription = agedescription;
	}

	public String getMaterialused() {
		return materialused;
	}

	public void setMaterialused(String materialused) {
		this.materialused = materialused;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColorhashcode() {
		return colorhashcode;
	}

	public void setColorhashcode(String colorhashcode) {
		this.colorhashcode = colorhashcode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSizeid() {
		return sizeid;
	}

	public void setSizeid(String sizeid) {
		this.sizeid = sizeid;
	}

	public String getPieceid() {
		return pieceid;
	}

	public void setPieceid(String pieceid) {
		this.pieceid = pieceid;
	}

	public String getSetofpieces() {
		return setofpieces;
	}

	public void setSetofpieces(String setofpieces) {
		this.setofpieces = setofpieces;
	}

	public String getPatternid() {
		return patternid;
	}

	public void setPatternid(String patternid) {
		this.patternid = patternid;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPriceid() {
		return priceid;
	}

	public void setPriceid(String priceid) {
		this.priceid = priceid;
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

	public String getProductdescription() {
		return productdescription;
	}

	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}

	public String getApprovalid() {
		return approvalid;
	}

	public void setApprovalid(String approvalid) {
		this.approvalid = approvalid;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public String getApprovaltext() {
		return approvaltext;
	}

	public void setApprovaltext(String approvaltext) {
		this.approvaltext = approvaltext;
	}

	public String getApprovalcomment() {
		return approvalcomment;
	}

	public void setApprovalcomment(String approvalcomment) {
		this.approvalcomment = approvalcomment;
	}

	public String getCollectionsalebadge() {
		return collectionsalebadge;
	}

	public void setCollectionsalebadge(String collectionsalebadge) {
		this.collectionsalebadge = collectionsalebadge;
	}

	public FileData getImagefile() {
		return imagefile;
	}

	public void setImagefile(FileData imagefile) {
		this.imagefile = imagefile;
	}

	@Override
	public String toString() {
		return "ProductFullDetailsWithImage [productid=" + productid + ", descriptionid=" + descriptionid + ", batchid="
				+ batchid + ", genderid=" + genderid + ", categoryid=" + categoryid + ", subcategoryid=" + subcategoryid
				+ ", seasionid=" + seasionid + ", occasionid=" + occasionid + ", ageid=" + ageid + ", materialid="
				+ materialid + ", colorid=" + colorid + ", custid=" + custid + ", productstatus=" + productstatus
				+ ", producttitle=" + producttitle + ", productsubtitle=" + productsubtitle + ", warehouseid="
				+ warehouseid + ", batchstatus=" + batchstatus + ", sellerid=" + sellerid + ", category=" + category
				+ ", subcategory=" + subcategory + ", season=" + season + ", occasion=" + occasion + ", agedescription="
				+ agedescription + ", materialused=" + materialused + ", color=" + color + ", colorhashcode="
				+ colorhashcode + ", quantity=" + quantity + ", sizeid=" + sizeid + ", pieceid=" + pieceid
				+ ", setofpieces=" + setofpieces + ", patternid=" + patternid + ", pattern=" + pattern + ", priceid="
				+ priceid + ", mrp=" + mrp + ", lcprice=" + lcprice + ", tax=" + tax + ", sellingprice=" + sellingprice
				+ ", finalprice=" + finalprice + ", discount=" + discount + ", productdescription=" + productdescription
				+ ", approvalid=" + approvalid + ", approvalstatus=" + approvalstatus + ", approvaltext=" + approvaltext
				+ ", approvalcomment=" + approvalcomment + ", collectionsalebadge=" + collectionsalebadge
				+ ", imagefile=" + imagefile + "]";
	}
	
	
	
}
