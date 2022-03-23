package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "productquantities")
public class ProductQuantities implements Serializable{

	/*
	 * 
	 * create table productquantities(custid bigint auto_increment, 
	 * quantity bigint, 
	 * sizeid bigint, 
	 * constraint productquantities_pk primary key (custid),
	 * constraint productquantities_fk1 foreign key (sizeid) references sizes (sizeid));
	 */
	
	@Id
	@Column(name = "custid")
	private long custid;	
	
	
	@Column(name = "quantity")
	private long quantity;	
	
	
	@Column(name = "sizeid")
	private long sizeid;


	public ProductQuantities() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProductQuantities(long quantity, long sizeid) {
		super();
		this.quantity = quantity;
		this.sizeid = sizeid;
	}


	@Override
	public String toString() {
		return "ProductQuantities [custid=" + custid + ", quantity=" + quantity + ", sizeid=" + sizeid + "]";
	}


	public long getCustid() {
		return custid;
	}


	public void setCustid(long custid) {
		this.custid = custid;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public long getSizeid() {
		return sizeid;
	}


	public void setSizeid(long sizeid) {
		this.sizeid = sizeid;
	}

	
	
	
	
}
