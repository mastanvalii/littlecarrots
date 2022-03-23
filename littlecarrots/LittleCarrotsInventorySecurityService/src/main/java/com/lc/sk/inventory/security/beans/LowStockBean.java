package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class LowStockBean implements Serializable {

	private String ls;

	public LowStockBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LowStockBean(String ls) {
		super();
		this.ls = ls;
	}

	public String getLs() {
		return ls;
	}

	public void setLs(String ls) {
		this.ls = ls;
	}
	
	
	
}
