package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subcategories")
public class SubCategories implements Serializable {

	/*
	 * 
	 * create table subcategories(subcatid bigint auto_increment, 
	 * catid bigint, 
	 * categoryname varchar(50), 
	 * constraint subcategories_pk primary key (subcatid),
	 * constraint subcategories_fk1 foreign key (catid) references categories (catid));
	 */
	
	@Id
	@Column(name="subcatid")
	private long subcatid;
	
	@Column(name = "catid")
	private long catid;

	@Column(name = "categoryname")
	private String categoryname;

	public SubCategories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubCategories(long catid, String categoryname) {
		super();
		this.catid = catid;
		this.categoryname = categoryname;
	}

	@Override
	public String toString() {
		return "SubCategories [subcatid=" + subcatid + ", catid=" + catid + ", categoryname=" + categoryname + "]";
	}

	public long getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	
	
	
	
}
