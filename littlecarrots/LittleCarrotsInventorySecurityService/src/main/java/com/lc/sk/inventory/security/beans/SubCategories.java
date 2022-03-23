package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SubCategories implements Serializable {

	private long subcatid;

	private long catid;

	private String categoryname;

	public SubCategories() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SubCategories(long subcatid, long catid, String categoryname) {
// 		super();
// 		this.subcatid = subcatid;
// 		this.catid = catid;
// 		this.categoryname = categoryname;
// 	}

	@Override
	public String toString() {
		return "SubCategories [subcatid=" + subcatid + ", catid=" + catid + ", categoryname=" + categoryname + "]";
	}

	public long getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

}
