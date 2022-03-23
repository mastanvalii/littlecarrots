package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="noofpieces")
public class NoOfPieces implements Serializable {

	
	
	@Id
	@Column(name = "pieceid")
	private long pieceid;	
	
	
	
	@Column(name = "productid")
	private long productid;	
	
	
	
	@Column(name = "noofset")
	private long noofset;



	public NoOfPieces() {
		super();
	}



	public NoOfPieces(long productid, long noofset) {
		super();
		this.productid = productid;
		this.noofset = noofset;
	}



	public long getPieceid() {
		return pieceid;
	}



	public void setPieceid(long pieceid) {
		this.pieceid = pieceid;
	}



	public long getProductid() {
		return productid;
	}



	public void setProductid(long productid) {
		this.productid = productid;
	}



	public long getNoofset() {
		return noofset;
	}



	public void setNoofset(long noofset) {
		this.noofset = noofset;
	}



	@Override
	public String toString() {
		return "NoOfPieces [pieceid=" + pieceid + ", productid=" + productid + ", noofset=" + noofset + "]";
	}	
	
	
	
	
	
	
	
	
}
