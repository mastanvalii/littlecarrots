package com.lc.sk.auth.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "contest")
public class Contest implements Serializable{
	/*
	 * ccreate table contest(
emailid varchar(200) unique,
phone bigint(11) unique,
instaid varchar(200) unique,
childname varchar(200),
age varchar(200),
tc boolean,
images longblob,
dateinsertion	DATEtime,
contestmonthyear varchar(200),
constraint contest_pk primary key (emailid)
);
	 */
	@Id
	@Column(name = "emailid")
	private String emailid;

	@Column(name = "phone")
	private long phone;
	
	@Column(name = "instaid")
	private String instaid;
	
	@Column(name = "childname")
	private String childname;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "tc")
	private boolean tc;
	
	@Column(name="images")
	private String images;
	
	@Column(name="dateinsertion")
	private Timestamp dateinsertion;
	
	@Column(name = "contestmonthyear")
	private String contestmonthyear;

	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contest(String emailid, long phone, String instaid, String childname, String age, boolean tc, String images,
			Timestamp dateinsertion, String contestmonthyear) {
		super();
		this.emailid = emailid;
		this.phone = phone;
		this.instaid = instaid;
		this.childname = childname;
		this.age = age;
		this.tc = tc;
		this.images = images;
		this.dateinsertion = dateinsertion;
		this.contestmonthyear = contestmonthyear;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getInstaid() {
		return instaid;
	}

	public void setInstaid(String instaid) {
		this.instaid = instaid;
	}

	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isTc() {
		return tc;
	}

	public void setTc(boolean tc) {
		this.tc = tc;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Timestamp getDateinsertion() {
		return dateinsertion;
	}

	public void setDateinsertion(Timestamp dateinsertion) {
		this.dateinsertion = dateinsertion;
	}

	public String getContestmonthyear() {
		return contestmonthyear;
	}

	public void setContestmonthyear(String contestmonthyear) {
		this.contestmonthyear = contestmonthyear;
	}

	@Override
	public String toString() {
		return "Contest [emailid=" + emailid + ", phone=" + phone + ", instaid=" + instaid + ", childname=" + childname
				+ ", age=" + age + ", tc=" + tc + ", images=" + images + ", dateinsertion=" + dateinsertion
				+ ", contestmonthyear=" + contestmonthyear + "]";
	}

	
	
}

