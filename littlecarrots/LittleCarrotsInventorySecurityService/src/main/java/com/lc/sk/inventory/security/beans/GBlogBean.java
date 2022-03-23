package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class GBlogBean implements Serializable {
	
	private long blogid;
	private String title;
	private Date dateofinsertion;		
	private byte[] mobilebanner;
	public GBlogBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GBlogBean(long blogid, String title, Date dateofinsertion, byte[] mobilebanner) {
		super();
		this.blogid = blogid;
		this.title = title;
		this.dateofinsertion = dateofinsertion;
		this.mobilebanner = mobilebanner;
	}
	public long getBlogid() {
		return blogid;
	}
	public void setBlogid(long blogid) {
		this.blogid = blogid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateofinsertion() {
		return dateofinsertion;
	}
	public void setDateofinsertion(Date dateofinsertion) {
		this.dateofinsertion = dateofinsertion;
	}
	public byte[] getMobilebanner() {
		return mobilebanner;
	}
	public void setMobilebanner(byte[] mobilebanner) {
		this.mobilebanner = mobilebanner;
	}
	@Override
	public String toString() {
		return "GBlogBean [blogid=" + blogid + ", title=" + title + ", dateofinsertion=" + dateofinsertion
				+ ", mobilebanner=" + Arrays.toString(mobilebanner) + "]";
	}
	
	
	
	

}
