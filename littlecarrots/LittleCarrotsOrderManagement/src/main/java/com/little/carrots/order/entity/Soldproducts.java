package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="soldproducts")
public class Soldproducts implements Serializable{

	
//	create table soldproducts(
//			saleprid bigint auto_increment,
//			inid bigint,
//	        prid bigint,
//	        qty bigint,
//			netamount float,
//	        gst float,
//			discount float,
//	        totalproductprice float,        
//	        status varchar(100),
//			constraint soldproducts_pk primary key (saleprid),
//	        constraint soldproducts_fk1 foreign key (inid) references invoices(inid),
//	        constraint soldproducts_fk2 foreign key (status) references soldproductstatus(status)
//			);
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleprid")
	private long saleprid;
	
	@Column(name = "inid")
	private long inid;
	
	@Column(name = "prid")
	private long prid;
	
	@Column(name = "qty")
	private long qty;
	
	@Column(name = "netamount")
	private float netamount;
	
	@Column(name = "gst")
	private float gst;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "totalproductprice")
	private float totalproductprice;
	
	
	
	@Column(name = "status")
	private String status;

	public Soldproducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Soldproducts( long inid, long prid, long qty, float netamount, float gst, float discount,
			float totalproductprice, String status) {
		super();
		
		this.inid = inid;
		this.prid = prid;
		this.qty = qty;
		this.netamount = netamount;
		this.gst = gst;
		this.discount = discount;
		this.totalproductprice = totalproductprice;
		this.status = status;
	}

	public long getSaleprid() {
		return saleprid;
	}

	public void setSaleprid(long saleprid) {
		this.saleprid = saleprid;
	}

	public long getInid() {
		return inid;
	}

	public void setInid(long inid) {
		this.inid = inid;
	}

	public long getPrid() {
		return prid;
	}

	public void setPrid(long prid) {
		this.prid = prid;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public float getNetamount() {
		return netamount;
	}

	public void setNetamount(float netamount) {
		this.netamount = netamount;
	}

	public float getGst() {
		return gst;
	}

	public void setGst(float gst) {
		this.gst = gst;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getTotalproductprice() {
		return totalproductprice;
	}

	public void setTotalproductprice(float totalproductprice) {
		this.totalproductprice = totalproductprice;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Soldproducts [saleprid=" + saleprid + ", inid=" + inid + ", prid=" + prid + ", qty=" + qty
				+ ", netamount=" + netamount + ", gst=" + gst + ", discount=" + discount + ", totalproductprice="
				+ totalproductprice +  ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
