package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products implements Serializable {
	
@Id
@Column(name="productid")
private long productid;

@Column(name="descriptionid")
private long descriptionid;

@Column(name="BatchID")
private long batchid;

@Column(name="genderid")
private String genderid;

@Column(name="catid")
private long catid;

@Column(name="subcatid")
private long subcatid;

@Column(name="seasonid")
private long seasonid;

@Column(name="occasionid")
private long occasionid;

@Column(name="ageid")
private String ageid;

@Column(name="materialid")
private long materialid;

@Column(name="colorid")
private long colorid;

@Column(name="custid")
private long custid;

@Column(name="status")
private boolean status;

@Column(name="title")
private String title;

@Column(name="subtitle")
private String subtitle;

public Products() {
	super();
	// TODO Auto-generated constructor stub
}

public Products(long descriptionid, long batchid, String genderid, long catid, long subcatid, long seasonid,
		long occasionid, String ageid, long materialid, long colorid, long custid, boolean status, String title, String subtitle) {
	super();
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
	this.subtitle=subtitle;
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
			+ ", occasionid=" + occasionid + ", ageid=" + ageid + ", materialid=" + materialid + ", colorid=" + colorid
			+ ", custid=" + custid + ", status=" + status + ", title=" + title + ", subtitle=" + subtitle + "]";
}






}
