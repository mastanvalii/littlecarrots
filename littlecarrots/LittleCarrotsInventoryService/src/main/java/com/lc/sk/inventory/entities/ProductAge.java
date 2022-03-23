package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="productage")

public class ProductAge implements Serializable{
	
	
	/*
	 * 
	 * 
	 * create table ProductAge(
ageid varchar(50),
des varchar(200),
constraint productage_pk primary key(ageid) 
);
	 */
	@Id
	@Column (name="ageid")
	private String ageid;
	
	@Column (name="des")
	private String des;

	public ProductAge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductAge(String ageid, String des) {
		super();
		this.ageid = ageid;
		this.des = des;
	}

	public String getAgeid() {
		return ageid;
	}

	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Override
	public String toString() {
		return "ProductAge [ageid=" + ageid + ", des=" + des + "]";
	}
	
	
	
	


}
