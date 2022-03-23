package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="materialcomposition")
public class Materialcomposition implements Serializable{

	@Id
	@Column(name="materialid")
	private long materialid;
	
	@Column(name="description")
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

	public Materialcomposition(long materialid, String description) {
		super();
		this.materialid = materialid;
		this.description = description;
	}

	public Materialcomposition() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Materialcomposition [materialid=" + materialid + ", description=" + description + "]";
	}
	
	
}
