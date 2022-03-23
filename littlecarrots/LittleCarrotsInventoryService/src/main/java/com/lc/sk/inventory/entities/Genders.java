package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Genders")
public class Genders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3977097887697102270L;

	/*
	 *create table Genders(
*GenderID varchar(10) ,
*Gender varchar(10),
*constraint gender_pk primary key (genderid)
*);
*/
	@Id
	@Column(name="genderid")
	private String genderid;
	
	@Column(name="gender")
	private String gender;
	
	public Genders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGenderid() {
		return genderid;
	}
	
	public void setGenderid(String genderid) {
		this.genderid = genderid;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
// TODO Remove unused code found by UCDetector
// 	public Genders(String genderid, String gender) {
// 		super();
// 		this.genderid = genderid;
// 		this.gender = gender;
// 	}
	
	@Override
	public String toString() {
		return "Genders [genderid=" + genderid + ", gender=" + gender + "]";
	}
	
	
}
