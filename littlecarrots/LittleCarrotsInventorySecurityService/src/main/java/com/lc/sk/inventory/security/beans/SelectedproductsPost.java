package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;

public class SelectedproductsPost implements Serializable {

	private String genderid;
	private String productid[];
	private String startdate;
	private String enddate;
	
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
	public SelectedproductsPost() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public SelectedproductsPost( String genderid, String[] productid, String startdate, String enddate) {
// 		super();
// 		this.genderid = genderid;
// 		this.productid = productid;
// 		this.startdate = startdate;
// 		this.enddate = enddate;
// 	}
	@Override
	public String toString() {
		return "SelectedproductsPost [ genderid=" + genderid + ", productid="
				+ Arrays.toString(productid) + ", startdate=" + startdate + ", enddate=" + enddate + "]";
	}
	
}
