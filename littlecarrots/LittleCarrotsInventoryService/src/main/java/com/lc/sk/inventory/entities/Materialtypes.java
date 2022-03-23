package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "materialtype")
public class Materialtypes implements Serializable {
	
	@Id
	@Column(name="materialid")
	private long materialid;
	
	@Column(name="materialname")
	private  String materialname;
	
	@Column(name="description")
	private String description;
	
	@Column(name="occasionid")
	private int ocid;
	
	@Column(name="seasonid")
	private long seasonid;
	
	@Column(name="catid")
	private long catid;

	
	
	
	public Materialtypes() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public String toString() {
		return "Materialtypes [materialid=" + materialid + ", materialname=" + materialname + ", description="
				+ description + ", ocid=" + ocid + ", seasonid=" + seasonid + ", catid=" + catid + "]";
	}

	public Materialtypes(String materialname, String description, int ocid, long seasonid, long catid) {
		super();
		this.materialname = materialname;
		this.description = description;
		this.ocid = ocid;
		this.seasonid = seasonid;
		this.catid = catid;
	}
	
	

}
