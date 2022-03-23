package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Materialcomposition implements Serializable {

	private long materialid;
	private String description;

	public long getMaterialid() {
		return materialid;
	}

	public void setMaterialid(long materialid) {
		this.materialid = materialid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

// TODO Remove unused code found by UCDetector
// 	public Materialcomposition(long materialid, String description) {
// 		super();
// 		this.materialid = materialid;
// 		this.description = description;
// 	}

	public Materialcomposition() {
		super();

	}

	@Override
	public String toString() {
		return "Materialcomposition [materialid=" + materialid + ", description=" + description + "]";
	}

}
