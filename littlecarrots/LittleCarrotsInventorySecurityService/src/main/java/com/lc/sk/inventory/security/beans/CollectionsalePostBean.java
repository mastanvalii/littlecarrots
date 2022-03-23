package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;

public class CollectionsalePostBean implements Serializable{

	private String id;
	private String title;
	private String genderid;
	private String productid[];
	private String badge;
	private String display;
	private String startdate;
	private String enddate;
	public CollectionsalePostBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollectionsalePostBean(String id, String title, String genderid, String[] productid, String badge,
			String display, String startdate, String enddate) {
		super();
		this.id = id;
		this.title = title;
		this.genderid = genderid;
		this.productid = productid;
		this.badge = badge;
		this.display = display;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenderid() {
		return genderid;
	}
	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}
	public String[] getProductid() {
		return productid;
	}
	public void setProductid(String[] productid) {
		this.productid = productid;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	@Override
	public String toString() {
		return "CollectionsalePostBean [id=" + id + ", title=" + title + ", genderid=" + genderid + ", productid="
				+ Arrays.toString(productid) + ", badge=" + badge + ", display=" + display + ", startdate=" + startdate
				+ ", enddate=" + enddate + "]";
	}
	
	
}
