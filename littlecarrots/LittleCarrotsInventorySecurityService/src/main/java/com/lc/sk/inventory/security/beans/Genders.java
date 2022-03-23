package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Genders implements Serializable {

	private String genderid;

	private String gender;

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

	public Genders() {
		super();
		// TODO Auto-generated constructor stub
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
