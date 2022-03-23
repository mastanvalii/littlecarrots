package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "grannyblog")
public class GrannyBlog implements Serializable {
	
	       /*create table grannyblog( 
			blogid bigint auto_increment,
			bid bigint,
			title varchar(150),
			subtitle varchar(150),
			information text,
			dateofinsertion date,
			authorname varchar(50),
			authorimage longblob,
			posterimage longblob,
			status boolean,
			constraint grannyblog_pk primary key (blogid),
			constraint blogreport_fk1 foreign key (bid) references blogreport(bid));*/
	
	
	@Id
	@Column(name = "blogid")
	private long blogid;	
	
	@Column(name = "bid")
	private long bid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "subtitle")
	private String subtitle;
	
	@Column(name = "information")
	private String information;
	
	@Column(name = "dateofinsertion")
	private Date dateofinsertion;
		
	@Column(name = "thumbimage", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] thumbimage;
	
	@Column(name = "mobilebanner", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] mobilebanner;
	
	@Column(name = "desktopbanner", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] desktopbanner;	
	
	@Column(name = "status")
	private boolean status;

	public GrannyBlog() {
		super();
		// TODO Auto-generated constructor stub
	}



	public GrannyBlog( long bid, String title, String subtitle, String information, Date dateofinsertion,
			byte[] thumbimage, byte[] mobilebanner, byte[] desktopbanner, boolean status) {
		super();
	//	this.blogid = blogid;
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
	
	
	