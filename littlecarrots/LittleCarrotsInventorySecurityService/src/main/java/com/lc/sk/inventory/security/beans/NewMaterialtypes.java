package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class NewMaterialtypes implements Serializable{

	private String materialname;
	private String description;
	private int ocid;
	private long seasonid;
	private long catid;
	public NewMaterialtypes() {
		super();
		// TODO Auto-generated constructor stub
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
// TODO Remove unused code found by UCDetector
// 	public NewMaterialtypes(String materialname, String description, int ocid, long seasonid, long catid) {
// 		super();
// 		this.materialname = materialname;
// 		this.description = description;
// 		this.ocid = ocid;
// 		this.seasonid = seasonid;
// 		this.catid = catid;
// 	}
	@Override
	public String toString() {
		return "NewMaterialtypes [materialname, description, ocid, seasonid, catid]";
	}
	
	
}
