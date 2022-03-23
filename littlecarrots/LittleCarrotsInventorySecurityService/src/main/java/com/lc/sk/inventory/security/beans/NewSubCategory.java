package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class NewSubCategory implements Serializable {
	//subcategory
	private long catid;

	private String categoryname;

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

	public NewSubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public NewSubCategory(long catid, String categoryname) {
// 		super();
// 		this.catid = catid;
// 		this.categoryname = categoryname;
// 	}

	@Override
	public String toString() {
		return "NewSubCategory [catid, categoryname]";
	}

}
