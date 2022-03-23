package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Selectedproducts implements Serializable {
private String spid;
private String genderid;
private String productid;
private String startdate;
private String enddate;
public String getSpid() {
	return spid;
}
public void setSpid(String spid) {
	this.spid = spid;
}
public String getGenderid() {
	return genderid;
}
public void setGenderid(String genderid) {
	this.genderid = genderid;
}
public String getProductid() {
	return productid;
}
public void setProductid(String productid) {
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
// TODO Remove unused code found by UCDetector
// public Selectedproducts(String spid, String genderid, String productid, String startdate, String enddate) {
// 	super();
// 	this.spid = spid;
// 	this.genderid = genderid;
// 	this.productid = productid;
// 	this.startdate = startdate;
// 	this.enddate = enddate;
// }
public Selectedproducts() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Selectedproducts [spid=" + spid + ", genderid=" + genderid + ", productid=" + productid + ", startdate="
			+ startdate + ", enddate=" + enddate + "]";
}


}
