package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="occasionalwear")

public class OccasionalWear implements Serializable {
	
	
	/*
	 * create table occasionalwear(
occasionid int(10) auto_increment,
occaName varchar(50),
subcatid bigint,
constraint occasionalwear_pk primary key (occasionid),
constraint occasionalwear_fk1 foreign key (subcatid) references subcategories(subcatid)
);
	 * 
	 */
	@Id
	@Column (name="occasionid")
	private int occasionid;
	
	@Column (name="occaname")
	private String occaname;
	
	@Column(name="subcatid")
	private long subcatid;
	

	public OccasionalWear() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OccasionalWear(String occaname, long subcatid) {
		super();
		this.occaname = occaname;
		this.subcatid = subcatid;
	}


	@Override
	public String toString() {
		return "OccasionalWear [occasionid=" + occasionid + ", occaname=" + occaname + ", subcatid=" + subcatid + "]";
	}


	public int getOccasionid() {
		return occasionid;
	}


	public void setOccasionid(int occasionid) {
		this.occasionid = occasionid;
	}


	public String getOccaname() {
		return occaname;
	}


	public void setOccaname(String occaname) {
		this.occaname = occaname;
	}


	public long getSubcatid() {
		return subcatid;
	}


	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}

	
	

	

}
