package com.lc.sk.inventory.security.beans;

import java.sql.Timestamp;

public class ContestBean {
	
	private String emailid;
	private long phone;
	private String instaid;
	private String childname;
	private String age;
	private boolean tc;
	private String images;
	private Timestamp dateinsertion;
	private String contestmonthyear;
	
	public ContestBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContestBean(String emailid, long phone, String instaid, String childname, String age, boolean tc, String images,
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
