package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class OccasionalWear implements Serializable {

	private int occasionid;

	private String occaname;

	private long subcatid;

	public OccasionalWear() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public OccasionalWear(int occasionid, String occaname, long subcatid) {
// 		super();
// 		this.occasionid = occasionid;
// 		this.occaname = occaname;
// 		this.subcatid = subcatid;
// 	}

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
