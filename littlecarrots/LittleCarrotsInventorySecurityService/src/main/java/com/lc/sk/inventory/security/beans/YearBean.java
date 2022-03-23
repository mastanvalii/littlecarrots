package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.List;

public class YearBean implements Serializable{
	
	public String year;
	public List<String> month;
	public YearBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public YearBean(String year, List<String> month) {
// 		super();
// 		this.year = year;
// 		this.month = month;
// 	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<String> getMonth() {
		return month;
	}
	public void setMonth(List<String> month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "YearBean [year=" + year + ", month=" + month + "]";
	}
	
	

}
