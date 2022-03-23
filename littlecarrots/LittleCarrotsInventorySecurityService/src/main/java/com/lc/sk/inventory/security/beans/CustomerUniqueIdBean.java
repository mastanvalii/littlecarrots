package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class CustomerUniqueIdBean implements Serializable {

	private String uniqueid;

	public CustomerUniqueIdBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerUniqueIdBean(String uniqueid) {
		super();
		this.uniqueid = uniqueid;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	
	
}
