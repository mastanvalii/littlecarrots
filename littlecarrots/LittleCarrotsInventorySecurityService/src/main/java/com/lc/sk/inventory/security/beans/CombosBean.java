package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class CombosBean implements Serializable{
	
	private String comboid;
	private String total_amount;
	private String discount;
	private String total_amt_after_discount;
	private String startdate;
	private String enddate;
	private String status;
	private String title;
	private String description;
	private String ageid;
	private String genderid;
	public CombosBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public CombosBean(String comboid, String total_amount, String discount, String total_amt_after_discount,
// 			String startdate, String enddate, String status, String title, String description, String ageid,
// 			String genderid) {
// 		super();
// 		this.comboid = comboid;
// 		this.total_amount = total_amount;
// 		this.discount = discount;
// 		this.total_amt_after_discount = total_amt_after_discount;
// 		this.startdate = startdate;
// 		this.enddate = enddate;
// 		this.status = status;
// 		this.title = title;
// 		this.description = description;
// 		this.ageid = ageid;
// 		this.genderid = genderid;
// 	}
	public String getComboid() {
		return comboid;
	}
	public void setComboid(String comboid) {
		this.comboid = comboid;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTotal_amt_after_discount() {
		return total_amt_after_discount;
	}
	public void setTotal_amt_after_discount(String total_amt_after_discount) {
		this.total_amt_after_discount = total_amt_after_discount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAgeid() {
		return ageid;
	}
	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}
	public String getGenderid() {
		return genderid;
	}
	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}
	@Override
	public String toString() {
		return "CombosBean [comboid=" + comboid + ", total_amount=" + total_amount + ", discount=" + discount
				+ ", total_amt_after_discount=" + total_amt_after_discount + ", startdate=" + startdate + ", enddate="
				+ enddate + ", status=" + status + ", title=" + title + ", description=" + description + ", ageid="
				+ ageid + ", genderid=" + genderid + "]";
	}
		
}

