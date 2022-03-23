package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="color")
public class Color implements Serializable{
   
	
	
	@Id
	@Column(name = "colorid")
	private long colorid;
	
	
    @Column(name = "colorname")
	private String colorname;  
    
    
    @Column(name = "hashcode")
    private String hashcode;


	public Color() {
		super();
	}


	public Color( String colorname, String hashcode) {
		super();
		
		this.colorname = colorname;
		this.hashcode = hashcode;
	}


	public long getColorid() {
		return colorid;
	}


	public void setColorid(long colorid) {
		this.colorid = colorid;
	}


	public String getColorname() {
		return colorname;
	}


	public void setColorname(String colorname) {
		this.colorname = colorname;
	}


	public String getHashcode() {
		return hashcode;
	}


	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}


	@Override
	public String toString() {
		return "Color [colorid=" + colorid + ", colorname=" + colorname + ", hashcode=" + hashcode + "]";
	}


    
    
}
