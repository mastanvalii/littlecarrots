package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Lob;

public class Blogreport implements Serializable {

	private long bid;
	
	private String authorname;
	
	private byte[] authorimage;
	
	private String description;

	public Blogreport() {
		super();
	}
	
	
	
	

// TODO Remove unused code found by UCDetector
// 	public Blogreport(long bid, String authorname, byte[] authorimage, String description) {
// 		super();
// 		this.bid = bid;
// 		this.authorname = authorname;
// 		this.authorimage = authorimage;
// 		this.description = description;
// 	}
	
	
	
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
