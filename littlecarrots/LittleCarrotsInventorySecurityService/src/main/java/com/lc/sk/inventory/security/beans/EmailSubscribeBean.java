package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class EmailSubscribeBean implements Serializable {
	
	private String emailid;

	public EmailSubscribeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public EmailSubscribeBean(String emailid) {
// 		super();
// 		this.emailid = emailid;
// 	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "EmailSubscribeBean [emailid=" + emailid + "]";
	}
	
	

}
