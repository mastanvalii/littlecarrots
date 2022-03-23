package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class ProductCountBean implements Serializable {

	private String pc;

	public ProductCountBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCountBean(String pc) {
		super();
		this.pc = pc;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}
	
	
	
	
}
