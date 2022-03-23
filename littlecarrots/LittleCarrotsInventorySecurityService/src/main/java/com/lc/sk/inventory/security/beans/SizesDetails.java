package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SizesDetails implements Serializable {
	
	private String sizeid;
	private String ageid;
	private String sizeno;
	private String height;
	private String weight;
	private String chest;
	private String waist;
	private String hip;
	private String gender;
	private String subcategory;
	private String isocode;
	public SizesDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public SizesDetails(String sizeid, String ageid, String sizeno, String height, String weight, String chest,
// 			String waist, String hip, String gender, String subcategory, String isocode) {
// 		super();
// 		this.sizeid = sizeid;
// 		this.ageid = ageid;
// 		this.sizeno = sizeno;
// 		this.height = height;
// 		this.weight = weight;
// 		this.chest = chest;
// 		this.waist = waist;
// 		this.hip = hip;
// 		this.gender = gender;
// 		this.subcategory = subcategory;
// 		this.isocode = isocode;
// 	}
	public String getSizeid() {
		return sizeid;
	}
	public void setSizeid(String sizeid) {
		this.sizeid = sizeid;
	}
	public String getAgeid() {
		return ageid;
	}
	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}
	public String getSizeno() {
		return sizeno;
	}
	public void setSizeno(String sizeno) {
		this.sizeno = sizeno;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getChest() {
		return chest;
	}
	public void setChest(String chest) {
		this.chest = chest;
	}
	public String getWaist() {
		return waist;
	}
	public void setWaist(String waist) {
		this.waist = waist;
	}
	public String getHip() {
		return hip;
	}
	public void setHip(String hip) {
		this.hip = hip;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getIsocode() {
		return isocode;
	}
	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}
	@Override
	public String toString() {
		return "SizesDetails [sizeid=" + sizeid + ", ageid=" + ageid + ", sizeno=" + sizeno + ", height=" + height
				+ ", weight=" + weight + ", chest=" + chest + ", waist=" + waist + ", hip=" + hip + ", gender=" + gender
				+ ", subcategory=" + subcategory + ", isocode=" + isocode + "]";
	}
	
	

}
