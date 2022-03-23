package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class CalenderBean implements Serializable {
	private long month;
	private long year;
	public CalenderBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public CalenderBean(long month, long year) {
// 		super();
// 		this.month = month;
// 		this.year = year;
// 	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "CalenderBean [month=" + month + ", year=" + year + "]";
	}
	
	
}
