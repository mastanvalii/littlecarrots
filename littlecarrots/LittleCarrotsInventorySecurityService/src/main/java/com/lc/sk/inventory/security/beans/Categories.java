package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Categories implements Serializable {

	private long catid;

	private String category;

	private String gender;

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Categories(long catid, String category, String gender) {
// 		super();
// 		this.catid = catid;
// 		this.category = category;
// 		this.gender = gender;
// 	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Categories [catid=" + catid + ", category=" + category + ", gender=" + gender + "]";
	}



}
