package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Color implements Serializable {

	private long colorid;

	private String colorname;

	private String hashcode;

	public Color() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public Color(long colorid, String colorname, String hashcode) {
// 		super();
// 		this.colorid = colorid;
// 		this.colorname = colorname;
// 		this.hashcode = hashcode;
// 	}

	public long getColorid() {
		return colorid;
	}

	public void setColorid(long colorid) {
		this.colorid = colorid;
	}

	public String getColorname() {
		return colorname;
	}

	public void setColorname(String colorname) {
		this.colorname = colorname;
	}

	public String getHashcode() {
		return hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}

	@Override
	public String toString() {
		return "Color [colorid, colorname, hashcode]";
	}

}
