package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class GBlogBean1 implements Serializable {
	
	private long blogid;
	private long bid;
	private String title;
	private String information;
	private Date dateofinsertion;		
	private byte[] thumbimage;
	public GBlogBean1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GBlogBean1(long blogid, long bid, String title, String information, Date dateofinsertion,
			byte[] thumbimage) {
		super();
		this.blogid = blogid;
		this.bid = bid;
		this.title = title;
		this.information = information;
		this.dateofinsertion = dateofinsertion;
		this.thumbimage = thumbimage;
	}
	public long getBlogid() {
		return blogid;
	}
	public void setBlogid(long blogid) {
		this.blogid = blogid;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Date getDateofinsertion() {
		return dateofinsertion;
	}
	public void setDateofinsertion(Date dateofinsertion) {
		this.dateofinsertion = dateofinsertion;
	}
	public byte[] getThumbimage() {
		return thumbimage;
	}
	public void setThumbimage(byte[] thumbimage) {
		this.thumbimage = thumbimage;
	}
	@Override
	public String toString() {
		return "GBlogBean1 [blogid=" + blogid + ", bid=" + bid + ", title=" + title + ", information=" + information
				+ ", dateofinsertion=" + dateofinsertion + ", thumbimage=" + Arrays.toString(thumbimage) + "]";
	}

	
}
