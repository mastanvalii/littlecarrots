package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patterns")

public class Patterns implements Serializable {

	
	
	/**
	 * create table patterns(
	 * patid bigint auto_increment, 
	 * description text, 
	 * productid bigint, 
	 * constraint patterns_pk primary key (patid),
	 * constraint patterns_fk1 foreign key (productid) references products (productid));
	 */
	
	@Id
	@Column(name="patid")
	private long patid;
	
	@Column(name = "description")
	private String description;

	@Column(name = "productid")
	private long productid;

	public Patterns() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Patterns(String description, long productid) {
		super();
		this.description = description;
		this.productid = productid;
	}



	@Override
	public String toString() {
		return "Patterns [patid=" + patid + ", description=" + description + ", productid=" + productid + "]";
	}



	public long getPatid() {
		return patid;
	}



	public void setPatid(long patid) {
		this.patid = patid;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public long getProductid() {
		return productid;
	}



	public void setProductid(long productid) {
		this.productid = productid;
	}

	
	
	
	
	
}
