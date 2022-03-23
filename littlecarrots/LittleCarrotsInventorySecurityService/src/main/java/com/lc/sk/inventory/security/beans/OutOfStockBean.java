package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class OutOfStockBean implements Serializable {

	private String ofs;

	public OutOfStockBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutOfStockBean(String ofs) {
		super();
		this.ofs = ofs;
	}

	public String getOfs() {
		return ofs;
	}

	public void setOfs(String ofs) {
		this.ofs = ofs;
	}
	
	
	
}
