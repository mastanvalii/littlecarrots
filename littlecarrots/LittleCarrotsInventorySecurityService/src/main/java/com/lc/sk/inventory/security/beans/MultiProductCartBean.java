package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class MultiProductCartBean implements Serializable {
	private CartmanagementPostBean[] mycarts;

	public MultiProductCartBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public MultiProductCartBean(CartmanagementPostBean[] mycarts) {
// 		super();
// 		this.mycarts = mycarts;
// 	}

	public CartmanagementPostBean[] getMycarts() {
		return mycarts;
	}

	public void setMycarts(CartmanagementPostBean[] mycarts) {
		this.mycarts = mycarts;
	}


	
}
