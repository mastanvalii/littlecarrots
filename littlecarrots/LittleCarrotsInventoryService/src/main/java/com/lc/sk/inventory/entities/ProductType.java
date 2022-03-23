package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="producttype")
public class ProductType implements Serializable {

	@Id
	@Column(name = "protypeid")
	private long protypeid;	
	
	
	@Column(name = "subcatid")
	private long subcatid;	
	
	
	
	@Column(name = "producttypename")
	private String producttypename;



	public ProductType() {
		super();
	}



	public ProductType(long subcatid, String producttypename) {
		super();
		this.subcatid = subcatid;
		this.producttypename = producttypename;
	}



	public long getProtypeid() {
		return protypeid;
	}



	public void setProtypeid(long protypeid) {
		this.protypeid = protypeid;
	}



	public long getSubcatid() {
		return subcatid;
	}



	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}



	public String getProducttypename() {
		return producttypename;
	}



	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}



	@Override
	public String toString() {
		return "ProductType [protypeid=" + protypeid + ", subcatid=" + subcatid + ", producttypename=" + producttypename
				+ "]";
	}	
	
	
	
}
