package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SubCatDetails implements Serializable{
	
	public String details ;
	
	public String subcatid;

	
	public SubCatDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


// TODO Remove unused code found by UCDetector
// 	public SubCatDetails(String details, String subcatid) {
// 		super();
// 		this.details = details;
// 		this.subcatid = subcatid;
// 	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public String getSubcatid() {
		return subcatid;
	}


	public void setSubcatid(String subcatid) {
		this.subcatid = subcatid;
	}


	@Override
	public String toString() {
		return "SubCatDetails [details=" + details + ", subcatid=" + subcatid + "]";
	}
	
	
}
