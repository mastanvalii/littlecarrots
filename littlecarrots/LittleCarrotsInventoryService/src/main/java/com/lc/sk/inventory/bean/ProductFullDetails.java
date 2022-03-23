package com.lc.sk.inventory.bean; 
public interface ProductFullDetails {
	public String getProductid();
	public String getDescriptionid();
	public String getBatchid();
	public String getGenderid();
	public String getCategoryid();
	public String getSubcategoryid();
	public String getSeasionid();
	public String getOccasionid();
	public String getAgeid();
	public String getMaterialid();
	public String getColorid();
	public String getCustid();
	public String getProductstatus();
	public String getProducttitle();
	public String getProductsubtitle();
	public String getWarehouseid();
	public String getBatchstatus();
	public String getSellerid();
	public String getCategory();
	public String getSubcategory();
	public String getSeason();
	public String getOccasion();
	public String getAgedescription();
	public String getMaterialused();
	public String getColor();
	public String getColorhashcode();
	public String getQuantity();
	public String getSizeid();
	public String getPieceid();
	public String getSetofpieces();
	public String getPatternid();
	public String getPattern();
	public String getPriceid();
	public String getMrp();
	public String getLcprice();
	public String getTax();
	public String getSellingprice();
	public String getFinalprice();
	public String getDiscount();
	public String getProductdescription();
	
	/*Approval code to connect table */
	public String getApprovalid();
	public String getApprovalstatus();
	public String getApprovaltext();
	public String getApprovalcomment();
	
	/*collection information */
//	public String getCollectionsaletitle();
	public String getCollectionsalebadge();
//	public String getCollectionsaledisplay();
}
