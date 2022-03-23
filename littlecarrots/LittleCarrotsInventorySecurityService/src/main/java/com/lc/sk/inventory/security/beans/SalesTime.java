package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.List;



public class SalesTime implements Serializable{
	
	public String user;
	public List<YearBean> yearwise;
	
	public SalesTime() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SalesTime(String user, List<YearBean> yearwise) {
// 		super();
// 		this.user = user;
// 		this.yearwise = yearwise;
// 	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<YearBean> getYearwise() {
		return yearwise;
	}

	public void setYearwise(List<YearBean> yearwise) {
		this.yearwise = yearwise;
	}

	@Override
	public String toString() {
		return "SalesTime [user=" + user + "]";
	}
	
	

}
