package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;



public class GrannyBlog implements Serializable {

	
	
	private long blogid;	

	private long bid;

	private String title;
	
	private String subtitle;
	
	private String information;
	
    private Date dateofinsertion;	
	
	private byte[] thumbimage;

	private byte[] mobilebanner;
	

	private byte[]  desktopbanner;	

	private boolean status;

	public GrannyBlog() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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



	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public GrannyBlog(long blogid,long bid, String title, String subtitle, String information, Date dateofinsertion,
			byte[] thumbimage, byte[] mobilebanner, byte[] desktopbanner, boolean status) {
		super();
		this.blogid = blogid;
		this.bid = bid;
		this.title = title;
		this.subtitle = subtitle;
		this.information = information;
		this.dateofinsertion = dateofinsertion;
		this.thumbimage = thumbimage;
		this.mobilebanner = mobilebanner;
		this.desktopbanner = desktopbanner;
		this.status = status;
	}

	public byte[] getThumbimage() {
		return thumbimage;
	}

	public void setThumbimage(byte[] thumbimage) {
		this.thumbimage = thumbimage;
	}

	public byte[] getMobilebanner() {
		return mobilebanner;
	}

	public void setMobilebanner(byte[] mobilebanner) {
		this.mobilebanner = mobilebanner;
	}

	public byte[] getDesktopbanner() {
		return desktopbanner;
	}

	public void setDesktopbanner(byte[] desktopbanner) {
		this.desktopbanner = desktopbanner;
	}
	
	
	
	




	
	
	
	
	
	
	
	
	
	
	
	
}
