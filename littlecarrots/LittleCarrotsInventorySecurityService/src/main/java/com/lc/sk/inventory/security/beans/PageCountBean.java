package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class PageCountBean implements Serializable {

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public PageCountBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageCountBean(int count) {
		super();
		this.count = count;
	}
	
}
