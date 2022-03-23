package com.lilttle.carrots.imgs.beans;

import java.io.Serializable;

public class CompressBean implements Serializable {

	private String productid;
	private String filename;
	private String beforesize;
	private String aftersize;
	public CompressBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompressBean(String productid, String filename, String beforesize, String aftersize) {
		super();
		this.productid = productid;
		this.filename = filename;
		this.beforesize = beforesize;
		this.aftersize = aftersize;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getBeforesize() {
		return beforesize;
	}
	public void setBeforesize(String beforesize) {
		this.beforesize = beforesize;
	}
	public String getAftersize() {
		return aftersize;
	}
	public void setAftersize(String aftersize) {
		this.aftersize = aftersize;
	}
	@Override
	public String toString() {
		return "CompressBean [productid=" + productid + ", filename=" + filename + ", beforesize=" + beforesize
				+ ", aftersize=" + aftersize + "]";
	}
	
	
}
