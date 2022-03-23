package com.lc.sk.inventory.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="selectedproducts")
public class Selectedproducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="spid")
	private long spid;
	
	@Column(name="genderid")
	private String genderid;
	
	@Column(name="productid")
	private long productid;
	
	@Column(name="startdate")
	private Timestamp startdate;
	
	@Column(name="enddate")
	private Timestamp enddate;

	public long getSpid() {
		return spid;
	}

	public void setSpid(long spid) {
		this.spid = spid;
	}

	public String getGenderid() {
		return genderid;
	}

	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
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

// TODO Remove unused code found by UCDetector
//  public Selectedproducts(long spid, String genderid, long productid, Timestamp startdate, Timestamp enddate) {
// 		super();
// 		this.spid = spid;
// 		this.genderid = genderid;
// 		this.productid = productid;
// 		this.startdate = startdate;
// 		this.enddate = enddate;
// 	}

	public Selectedproducts(String genderid, long productid, Timestamp startdate, Timestamp enddate) {
	super();
	this.genderid = genderid;
	this.productid = productid;
	this.startdate = startdate;
	this.enddate = enddate;
}

	@Override
	public String toString() {
		return "Selectedproducts [spid=" + spid + ", genderid=" + genderid + ", productid=" + productid + ", startdate="
				+ startdate + ", enddate=" + enddate + "]";
	}

	public Selectedproducts() {
		super();
		// TODO Auto-generated constructor stub
	}

}
	
	
	
	
	

