package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SoldproductstatusBean implements Serializable {
	
	private String status;

	public SoldproductstatusBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SoldproductstatusBean(String status) {
// 		super();
// 		this.status = status;
// 	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SoldproductstatusBean [status=" + status + "]";
	}
	
	

}
