package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Products implements Serializable {

	private long productid;

	private long descriptionid;

	private long batchid;

	private String genderid;

	private long catid;

	private long subcatid;

	private long seasonid;

	private long occasionid;

	private String ageid;

	private long materialid;

	private long colorid;

	private long custid;

	private boolean status;
	
	private String title;
	
	private String subtitle;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Products(long productid, long descriptionid, long batchid, String genderid, long catid, long subcatid,
			long seasonid, long occasionid, String ageid, long materialid, long colorid, long custid, boolean status,
			String title,String subtitle) {
		super();
		this.productid = productid;
		this.descriptionid = descriptionid;
		this.batchid = batchid;
		this.genderid = genderid;
		this.catid = catid;
		this.subcatid = subcatid;
		this.seasonid = seasonid;
		this.occasionid = occasionid;
		this.ageid = ageid;
		this.materialid = materialid;
		this.colorid = colorid;
		this.custid = custid;
		this.status = status;
		this.title = title;
		this.subtitle = subtitle;
	}



	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public long getDescriptionid() {
		return descriptionid;
	}

	public void setDescriptionid(long descriptionid) {
		this.descriptionid = descriptionid;
	}

	public long getBatchid() {
		return batchid;
	}

	public void setBatchid(long batchid) {
		this.batchid = batchid;
	}

	public String getGenderid() {
		return genderid;
	}

	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public long getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}

	public long getSeasonid() {
		return seasonid;
	}

	public void setSeasonid(long seasonid) {
		this.seasonid = seasonid;
	}

	public long getOccasionid() {
		return occasionid;
	}

	public void setOccasionid(long occasionid) {
		this.occasionid = occasionid;
	}

	public String getAgeid() {
		return ageid;
	}

	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}

	public long getMaterialid() {
		return materialid;
	}

	public void setMaterialid(long materialid) {
		this.materialid = materialid;
	}

	public long getColorid() {
		return colorid;
	}

	public void setColorid(long colorid) {
		this.colorid = colorid;
	}

	public long getCustid() {
		return custid;
	}

	public void setCustid(long custid) {
		this.custid = custid;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubtitle() {
		return subtitle;
	}



	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}



	@Override
	public String toString() {
		return "Products [productid=" + productid + ", descriptionid=" + descriptionid + ", batchid=" + batchid
				+ ", genderid=" + genderid + ", catid=" + catid + ", subcatid=" + subcatid + ", seasonid=" + seasonid
				+ ", occasionid=" + occasionid + ", ageid=" + ageid + ", materialid=" + materialid + ", colorid="
				+ colorid + ", custid=" + custid + ", status=" + status + ", title=" + title + ", subtitle=" + subtitle
				+ "]";
	}






}
