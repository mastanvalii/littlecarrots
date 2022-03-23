package com.lc.sk.inventory.security.beans;

import java.io.Serializable;


public class Racks implements Serializable {

	private String rackid;

	public Racks() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Racks(String rackid) {
// 		super();
// 		this.rackid = rackid;
// 	}

	public String getRackid() {
		return rackid;
	}

	public void setRackid(String rackid) {
		this.rackid = rackid;
	}

	@Override
	public String toString() {
		return "Racks [rackid=" + rackid + "]";
	}
	
}
