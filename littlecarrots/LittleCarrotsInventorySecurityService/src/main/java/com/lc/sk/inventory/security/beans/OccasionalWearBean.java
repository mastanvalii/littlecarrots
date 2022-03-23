package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class OccasionalWearBean implements Serializable{
	
	private String occaname;
	private long subcatid;
	
	public OccasionalWearBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public OccasionalWearBean(String occaname, long subcatid) {
// 		super();
// 		this.occaname = occaname;
// 		this.subcatid = subcatid;
// 	}

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

	@Override
	public String toString() {
		return "OccasionalWearBean [occaname, subcatid]";
	}

	
}
