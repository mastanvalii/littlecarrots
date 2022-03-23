package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="racks")
public class Racks implements Serializable {

	
	
	/*
	 * create table racks(
			rackid varchar(100),
			constraint racks_pk primary key(rackid)
		);
	 */
	
	@Id
	@Column(name="rackid")
	private String rackid;

	public Racks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Racks(String rackid) {
		super();
		this.rackid = rackid;
	}

	public String getRackid() {
		return rackid;
	}

	public void setRackid(String rackid) {
		this.rackid = rackid;
	}

	@Override
	public String toString() {
		return "Racks [rackid=" + rackid + "]";
	}
	
	
	
}
