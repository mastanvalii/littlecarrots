package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;

public class CollectionsaleImagesBean implements Serializable{
	

	private long imageid;
	private String id;
	private byte[] icon;
	private byte[] mobileview1;
	private byte[] mobileview2;
	private byte[] desktopview1;
	private byte[] desktopview2;
	
	
	public CollectionsaleImagesBean() {
		super();
		// TODO Auto-generated constructor stub
	}


// TODO Remove unused code found by UCDetector
// 	public CollectionsaleImagesBean(long imageid, String id, byte[] icon, byte[] mobileview1, byte[] mobileview2,
// 			byte[] desktopview1, byte[] desktopview2) {
// 		super();
// 		this.imageid = imageid;
// 		this.id = id;
// 		this.icon = icon;
// 		this.mobileview1 = mobileview1;
// 		this.mobileview2 = mobileview2;
// 		this.desktopview1 = desktopview1;
// 		this.desktopview2 = desktopview2;
// 	}


	public long getImageid() {
		return imageid;
	}


	public void setImageid(long imageid) {
		this.imageid = imageid;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
		return "CollectionsaleImagesBean [imageid=" + imageid + ", id=" + id + ", icon=" + Arrays.toString(icon)
				+ ", mobileview1=" + Arrays.toString(mobileview1) + ", mobileview2=" + Arrays.toString(mobileview2)
				+ ", desktopview1=" + Arrays.toString(desktopview1) + ", desktopview2=" + Arrays.toString(desktopview2)
				+ "]";
	}
	
	
	
	

}
