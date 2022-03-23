package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="solditems")
public class SoldItems implements Serializable {

	
	/*
	 * 
	 * create table solditems(
				itemssoldid bigint auto_increment,
				skuid bigint,							# lc skuid
				qty bigint,								# qty sold
				netamount float,						# product amount 
			    constraint solditems_pk primary key (itemssoldid)
			);
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemssoldid")
	private long itemssoldid;
	
	@Column(name="skuid")
	private long skuid;
	
	@Column(name="qty")
	private long qty;
	
	@Column(name="netamount")
	private double netamount;
	
	@Column(name="invoiceid")
	private long invoiceid;
	
	public SoldItems() {
		// TODO Auto-generated constructor stub
	}



	public SoldItems(long skuid, long qty, double netamount, long invoiceid) {
		super();
		this.skuid = skuid;
		this.qty = qty;
		this.netamount = netamount;
		this.invoiceid = invoiceid;
	}



	public long getInvoiceid() {
		return invoiceid;
	}



	public void setInvoiceid(long invoiceid) {
		this.invoiceid = invoiceid;
	}



	public long getItemssoldid() {
		return itemssoldid;
	}

	public void setItemssoldid(long itemssoldid) {
		this.itemssoldid = itemssoldid;
	}

	public long getSkuid() {
		return skuid;
	}

	public void setSkuid(long skuid) {
		this.skuid = skuid;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public double getNetamount() {
		return netamount;
	}

	public void setNetamount(double netamount) {
		this.netamount = netamount;
	}

	@Override
	public String toString() {
		return "SoldItems [itemssoldid=" + itemssoldid + ", skuid=" + skuid + ", qty=" + qty + ", netamount="
				+ netamount + "]";
	}
	
	

}
