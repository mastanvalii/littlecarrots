package com.lc.sk.inventory.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.lc.sk.inventory.entities.CombosProductdetails;

public class ComboFullDetailsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 54191238410596984L;
	private long comboid;
	private float total_amount;
	private float discount;
	private float total_amt_after_discount;
	private Timestamp startdate;
	private Timestamp enddate;
	private boolean status;
	private String title;
	private String description;
	private String ageid;
	private String genderid;
	private List<CombosProductdetails>  comboproddetails;
	public ComboFullDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public ComboFullDetailsBean(long comboid, float total_amount, float discount, float total_amt_after_discount,
// 			Timestamp startdate, Timestamp enddate, boolean status, String title, String description, String ageid,
// 			String genderid, List<CombosProductdetails> comboproddetails) {
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
// 		this.comboproddetails = comboproddetails;
// 	}
	public long getComboid() {
		return comboid;
	}
	public void setComboid(long comboid) {
		this.comboid = comboid;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getTotal_amt_after_discount() {
		return total_amt_after_discount;
	}
	public void setTotal_amt_after_discount(float total_amt_after_discount) {
		this.total_amt_after_discount = total_amt_after_discount;
	}
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}
	public boolean isStatus() {
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
	public List<CombosProductdetails> getComboproddetails() {
		return comboproddetails;
	}
	public void setComboproddetails(List<CombosProductdetails> comboproddetails) {
		this.comboproddetails = comboproddetails;
	}
	@Override
	public String toString() {
		return "ComboFullDetailsBean [comboid=" + comboid + ", total_amount=" + total_amount + ", discount=" + discount
				+ ", total_amt_after_discount=" + total_amt_after_discount + ", startdate=" + startdate + ", enddate="
				+ enddate + ", status=" + status + ", title=" + title + ", description=" + description + ", ageid="
				+ ageid + ", genderid=" + genderid + ", comboproddetails=" + comboproddetails + "]";
	}
	
	
	
}
