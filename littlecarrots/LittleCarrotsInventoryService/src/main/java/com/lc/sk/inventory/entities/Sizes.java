package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sizes")
public class Sizes implements Serializable {

	@Id
	@Column(name="sizeid")
	private long sizeid;
	
	@Column(name="ageid")
	private String ageid;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="sizeno")
	private String sizeno;
	
	@Column(name="height")
	private String height;
	
	@Column(name="weight")
	private String weight;
	
	@Column(name="chest")
	private String chest;
	
	@Column(name="waist")
	private String waist;
	
	@Column(name="hip")
	private String hip;
	
	@Column(name="subcatid")
	private long subcatid;
	
	@Column(name="isocode")
	private String isocode;

	public Sizes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sizes( String ageid, String gender, String sizeno, String height, String weight, String chest,
			String waist, String hip, long subcatid, String isocode) {
		super();
		this.ageid = ageid;
		this.gender = gender;
		this.sizeno = sizeno;
		this.height = height;
		this.weight = weight;
		this.chest = chest;
		this.waist = waist;
		this.hip = hip;
		this.subcatid = subcatid;
		this.isocode = isocode;
	}

	public long getSizeid() {
		return sizeid;
	}

	public void setSizeid(long sizeid) {
		this.sizeid = sizeid;
	}

	public String getAgeid() {
		return ageid;
	}

	public void setAgeid(String ageid) {
		this.ageid = ageid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSizeno() {
		return sizeno;
	}

	public void setSizeno(String sizeno) {
		this.sizeno = sizeno;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getChest() {
		return chest;
	}

	public void setChest(String chest) {
		this.chest = chest;
	}

	public String getWaist() {
		return waist;
	}

	public void setWaist(String waist) {
		this.waist = waist;
	}

	public String getHip() {
		return hip;
	}

	public void setHip(String hip) {
		this.hip = hip;
	}

	public long getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}

	public String getIsocode() {
		return isocode;
	}

	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}

	@Override
	public String toString() {
		return "Sizes [sizeid=" + sizeid + ", ageid=" + ageid + ", gender=" + gender + ", sizeno=" + sizeno
				+ ", height=" + height + ", weight=" + weight + ", chest=" + chest + ", waist=" + waist + ", hip=" + hip
				+ ", subcatid=" + subcatid + ", isocode=" + isocode + "]";
	}

	
	
	
	
	
	

	
}
