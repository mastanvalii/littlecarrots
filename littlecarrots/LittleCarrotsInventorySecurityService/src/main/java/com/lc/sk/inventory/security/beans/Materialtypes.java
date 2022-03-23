package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Materialtypes // implements Serializable 
{

	private long materialid;
	private String materialname;
	private String description;
	private int ocid;
	private long seasonid;
	private long catid;

	public long getMaterialid() {
		return materialid;
	}

	public void setMaterialid(long materialid) {
		this.materialid = materialid;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOcid() {
		return ocid;
	}

	public void setOcid(int ocid) {
		this.ocid = ocid;
	}

	public long getSeasonid() {
		return seasonid;
	}

	public void setSeasonid(long seasonid) {
		this.seasonid = seasonid;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public Materialtypes() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Materialtypes(long materialid, String materialname, String description, int ocid, long seasonid,
// 			long catid) {
// 		super();
// 		this.materialid = materialid;
// 		this.materialname = materialname;
// 		this.description = description;
// 		this.ocid = ocid;
// 		this.seasonid = seasonid;
// 		this.catid = catid;
// 	}

	@Override
	public String toString() {
		return "Materialtypes [materialid=" + materialid + ", materialname=" + materialname + ", description="
				+ description + ", ocid=" + ocid + ", seasonid=" + seasonid + ", catid=" + catid + "]";
	}

}
