package com.lc.sk.inventory.security.beans;

import java.io.Serializable;


public class Rackidentity implements Serializable {
	private long uid;
	
	private String rackid;
	
	private String skuid;

	public Rackidentity() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Rackidentity( String rackid, String skuid) {
// 		super();
// 
// 		this.rackid = rackid;
// 		this.skuid = skuid;
// 	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getRackid() {
		return rackid;
	}

	public void setRackid(String rackid) {
		this.rackid = rackid;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	@Override
	public String toString() {
		return "Rackidentity [uid=" + uid + ", rackid=" + rackid + ", skuid=" + skuid + "]";
	}
	
}
