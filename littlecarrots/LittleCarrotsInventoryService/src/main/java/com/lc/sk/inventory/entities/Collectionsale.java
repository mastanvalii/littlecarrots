package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collectionsale")
public class Collectionsale implements Serializable {
	
	/**
	CREATE TABLE collectionsale(
id bigint auto_increment,
title varchar(150),
genderid varchar(10),
badge varchar(10),
display varchar(20),
startdate datetime,
enddate datetime,
constraint collectionsale1_pk primary key (id),
constraint collectionsale1_fk_genders_pk foreign key (genderid) references genders (genderid),
constraint collectionsale1_fk_badges_pk foreign key (badge) references badges(badge),
constraint collectionsale1_fk_displaytype_pk foreign key (display) references displaytype(display)
);

	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="genderid")
	private String genderid;
	

	@Column(name="badge")
	private String badge;

	@Column(name="display")
	private String display;
	
	@Column(name="startdate")
	private Timestamp startdate;
	
	
	@Column(name="enddate")
	private Timestamp enddate;


	public Collectionsale() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Collectionsale(String title, String genderid, String badge, String display,
			Timestamp startdate, Timestamp enddate) {
		super();
		this.title = title;
		this.genderid = genderid;
		this.badge = badge;
		this.display = display;
		this.startdate = startdate;
		this.enddate = enddate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGenderid() {
		return genderid;
	}


	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}


	

	public String getBadge() {
		return badge;
	}


	public void setBadge(String badge) {
		this.badge = badge;
	}


	public String getDisplay() {
		return display;
	}


	public void setDisplay(String display) {
		this.display = display;
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


	@Override
	public String toString() {
		return "Collectionsale [id=" + id + ", title=" + title + ", genderid=" + genderid + ", productid=" +", badge=" + badge + ", display=" + display + ", startdate=" + startdate + ", enddate=" + enddate
				+ "]";
	}

			
}
