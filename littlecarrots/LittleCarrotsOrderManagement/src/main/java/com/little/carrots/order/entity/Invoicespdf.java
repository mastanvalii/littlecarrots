package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoicespdf")
public class Invoicespdf implements Serializable{

//	create table invoicespdf(
//			pdfid bigint auto_increment,
//			inid bigint,
//	        filename varchar(200),
//			constraint invoicespdf_pk primary key (pdfid),
//	        constraint invoicespdf_fk1 foreign key (inid) references invoices(inid)
//			);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pdfid")
	private long pdfid;
	
	@Column(name = "inid")
	private long inid;
	
	@Column(name = "filename")
	private String filename;

	public Invoicespdf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoicespdf( long inid, String filename) {
		super();
		this.inid = inid;
		this.filename = filename;
	}

	public long getPdfid() {
		return pdfid;
	}

	public void setPdfid(long pdfid) {
		this.pdfid = pdfid;
	}

	public long getInid() {
		return inid;
	}

	public void setInid(long inid) {
		this.inid = inid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Invoicespdf [pdfid=" + pdfid + ", inid=" + inid + ", filename=" + filename + "]";
	}
	
	
}
