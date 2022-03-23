package com.little.carrots.order.bean;

import java.io.Serializable;
import java.util.List;

public class SalesTimeBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6804175681597340198L;
	private String user;
	private List<YearBean> yearwise;
	public SalesTimeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public SalesTimeBean(String user, List<YearBean> yearwise) {
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
		return "SalesTimeBean [user=" + user + ", yearwise=" + yearwise + "]";
	}
	
	
	

}
