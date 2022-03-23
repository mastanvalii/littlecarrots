package com.lc.sk.inventory.security.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="notifications")
public class Notifications implements Serializable {
	
//	create table notifications(
//			id bigint auto_increment,
//			title varchar(100),
//	        image longblob,
//	        description varchar(1000),
//	        date date,
//      	delaydate date,
//	        url varchar(200),
//			constraint notifications_pk primary key (id)
//			);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="title")
	private String title;

	@Column(name="image", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="delaydate")
	private Date delaydate;
	
	@Column(name="url")
	private String url;

	public Notifications() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notifications(String title, byte[] image, String description, Date date, Date delaydate, String url) {
		super();
		this.title = title;
		this.image = image;
		this.description = description;
		this.date = date;
		this.delaydate = delaydate;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDelaydate() {
		return delaydate;
	}

	public void setDelaydate(Date delaydate) {
		this.delaydate = delaydate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Notifications [id=" + id + ", title=" + title + ", image=" + Arrays.toString(image) + ", description="
				+ description + ", date=" + date + ", delaydate=" + delaydate + ", url=" + url + "]";
	}


	
	

	
}
