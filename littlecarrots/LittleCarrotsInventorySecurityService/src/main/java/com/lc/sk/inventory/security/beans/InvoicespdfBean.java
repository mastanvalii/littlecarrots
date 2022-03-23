package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class InvoicespdfBean implements Serializable {
	
	private long pdfid;
	private long inid;
	private String filename;
	public InvoicespdfBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvoicespdfBean(long pdfid, long inid, String filename) {
		super();
		this.pdfid = pdfid;
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
		return "InvoicespdfBean [pdfid=" + pdfid + ", inid=" + inid + ", filename=" + filename + "]";
	}
	
	

}
