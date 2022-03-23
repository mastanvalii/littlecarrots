package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="productdescriptions")

public class ProductDescriptions implements Serializable {


/*	create table ProductDescriptions(
			descriptionid bigint auto_increment,
			description varchar(200),
			constraint productdescriptions_pk primary key(descriptionid)
			);*/
	
	@Id
	@Column (name="descriptionid")
	private long descriptionid;
	
	@Column (name="description")
	private String description;
	

	
	public ProductDescriptions(String description) {
		super();
		this.description = description;
	}

	public long getDescriptionid() {
		return descriptionid;
	}

	public void setDescriptionid(long descriptionid) {
		this.descriptionid = descriptionid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductDescriptions [descriptionid=" + descriptionid + ", description=" + description + "]";
	}

	public ProductDescriptions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
