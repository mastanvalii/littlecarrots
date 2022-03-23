package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="similarproducts")
public class Similarproducts implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid")
	private long uid;
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	@Column(name="pvid")
	private long pvid;
	
	@Column(name="productid")
	private long productid;

	public Similarproducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Similarproducts(long pvid, long productid) {
		super();
		this.pvid = pvid;
		this.productid = productid;
	}

	public long getPvid() {
		return pvid;
	}

	public void setPvid(long pvid) {
		this.pvid = pvid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "Similarproducts [ pvid=" + pvid + "]";
	}

	
}
