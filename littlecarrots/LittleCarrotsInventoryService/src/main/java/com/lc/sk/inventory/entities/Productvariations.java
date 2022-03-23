package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productvariations")
public class Productvariations implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pvid")
	private long pvid;
	
	@Column(name="pvtype")
	private String pvtype;

	public Productvariations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Productvariations(String pvtype) {
		super();
		
		this.pvtype = pvtype;
	}

	public long getPvid() {
		return pvid;
	}

	public void setPvid(long pvid) {
		this.pvid = pvid;
	}

	public String getPvtype() {
		return pvtype;
	}

	public void setPvtype(String pvtype) {
		this.pvtype = pvtype;
	}
	
	
}
