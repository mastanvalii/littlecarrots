package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class CollectionSalePackBean implements Serializable{
	
	private long id;
	private String title;
	private String genderid;
	private String badge;
	private String display;
	private Timestamp startdate;
	private Timestamp enddate;
	private byte[] icon;
	private byte[] mobileview1;
	private byte[] mobileview2;
	private byte[] desktopview1;
	private byte[] desktopview2;
	public CollectionSalePackBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public CollectionSalePackBean(long id, String title, String genderid, String badge, String display,
// 			Timestamp startdate, Timestamp enddate, byte[] icon, byte[] mobileview1, byte[] mobileview2,
// 			byte[] desktopview1, byte[] desktopview2) {
// 		super();
// 		this.id = id;
// 		this.title = title;
// 		this.genderid = genderid;
// 		this.badge = badge;
// 		this.display = display;
// 		this.startdate = startdate;
// 		this.enddate = enddate;
// 		this.icon = icon;
// 		this.mobileview1 = mobileview1;
// 		this.mobileview2 = mobileview2;
// 		this.desktopview1 = desktopview1;
// 		this.desktopview2 = desktopview2;
// 	}
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
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public byte[] getMobileview1() {
		return mobileview1;
	}
	public void setMobileview1(byte[] mobileview1) {
		this.mobileview1 = mobileview1;
	}
	public byte[] getMobileview2() {
		return mobileview2;
	}
	public void setMobileview2(byte[] mobileview2) {
		this.mobileview2 = mobileview2;
	}
	public byte[] getDesktopview1() {
		return desktopview1;
	}
	public void setDesktopview1(byte[] desktopview1) {
		this.desktopview1 = desktopview1;
	}
	public byte[] getDesktopview2() {
		return desktopview2;
	}
	public void setDesktopview2(byte[] desktopview2) {
		this.desktopview2 = desktopview2;
	}
	@Override
	public String toString() {
		return "CollectionSalePackBean [id=" + id + ", title=" + title + ", genderid=" + genderid + ", badge=" + badge
				+ ", display=" + display + ", startdate=" + startdate + ", enddate=" + enddate + ", icon="
				+ Arrays.toString(icon) + ", mobileview1=" + Arrays.toString(mobileview1) + ", mobileview2="
				+ Arrays.toString(mobileview2) + ", desktopview1=" + Arrays.toString(desktopview1) + ", desktopview2="
				+ Arrays.toString(desktopview2) + "]";
	}
	
	

}
