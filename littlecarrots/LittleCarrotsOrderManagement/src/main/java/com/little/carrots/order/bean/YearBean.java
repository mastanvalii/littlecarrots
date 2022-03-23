package com.little.carrots.order.bean;

import java.io.Serializable;
import java.util.List;

public class YearBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3074144927948908254L;
	private String year;
	private List<String> month;
	public YearBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public YearBean(String year, List<String> month) {
		super();
		this.year = year;
		this.month = month;
	}
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
