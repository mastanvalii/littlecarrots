package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="combos")
public class Combos implements Serializable{

//	create table combos(
//			comboid bigint auto_increment,
//			total_amount double,
//			discount double,
//			 double,
//			startdate datetime,
//			enddate datetime,
//			status boolean,
//			title varchar(100),
//			description varchar(500),
//			ageid varchar(50),
//			genderid varchar(10),
//			constraint cmb_pk primary key (comboid) ,
//			constraint cmb_fk1 foreign key (ageid) references productage(ageid),
//			constraint cmb_fk2 foreign key (genderid) references genders(genderid)
//			);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comboid")
	private long comboid;
	
	@Column(name="total_amount")
	private float total_amount;
	
	@Column(name="discount")
	private float discount;
	
	@Column(name="total_amt_after_discount")
	private float total_amt_after_discount;
	
	@Column(name="startdate")
	private Timestamp startdate;
	
	
	@Column(name="enddate")
	private Timestamp enddate;
	

	@Column(name="status")
	private boolean status;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column (name="ageid")
	private String ageid;
	
	@Column (name="genderid")
	private String genderid;

	public Combos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Combos( float total_amount, float discount, float total_amt_after_discount, Timestamp startdate,
			Timestamp enddate, boolean status, String title, String description, String ageid, String genderid) {
		super();
//		this.comboid = comboid;
		this.total_amount = total_amount;
		this.discount = discount;
		this.total_amt_after_discount = total_amt_after_discount;
		this.startdate = startdate;
		this.enddate = enddate;
		this.status = status;
		this.title = title;
		this.description = description;
		this.ageid = ageid;
		this.genderid = genderid;
	}

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

	@Override
	public String toString() {
		return "Combos [comboid=" + comboid + ", total_amount=" + total_amount + ", discount=" + discount
				+ ", total_amt_after_discount=" + total_amt_after_discount + ", startdate=" + startdate + ", enddate="
				+ enddate + ", status=" + status + ", title=" + title + ", description=" + description + ", ageid="
				+ ageid + ", genderid=" + genderid + "]";
	}


}
