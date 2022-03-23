package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "blogreport")

public class Blogreport implements Serializable {

	
	/*create table blogreport(
              bid bigint,
              authorname varchar(50),
              authorimage longblob,
              description text,
              constraint blogreport_pk primary key (bid));*/
	
	
	@Id
	@Column(name = "bid")
	private long bid;	
	
	@Column(name = "authorname")
	private String authorname;
	
	@Column(name = "authorimage", columnDefinition = "LONGBLOB")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] authorimage;
	
	@Column(name = "description")
	private String description;

	
	public Blogreport() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Blogreport(String authorname, byte[] authorimage, String description) {
		super();
		this.authorname = authorname;
		this.authorimage = authorimage;
		this.description = description;
	}



	public long getBid() {
		return bid;
	}


	public void setBid(long bid) {
		this.bid = bid;
	}


	public String getAuthorname() {
		return authorname;
	}


	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}


	

	public byte[] getAuthorimage() {
		return authorimage;
	}



	public void setAuthorimage(byte[] authorimage) {
		this.authorimage = authorimage;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Blogreport [bid=" + bid + ", authorname=" + authorname + ", authorimage=" + Arrays.toString(authorimage)
				+ ", description=" + description + "]";
	}

	

	
	
	
	
	
}
	
	