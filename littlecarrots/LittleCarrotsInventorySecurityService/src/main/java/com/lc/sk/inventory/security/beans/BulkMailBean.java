package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class BulkMailBean implements Serializable {

	private String subject;
	private ArrayList<String> toEmail;
	public BulkMailBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public BulkMailBean(String subject, ArrayList<String> toEmail) {
// 		super();
// 		this.subject = subject;
// 		this.toEmail = toEmail;
// 	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public ArrayList<String> getToEmail() {
		return toEmail;
	}
	public void setToEmail(ArrayList<String> toEmail) {
		this.toEmail = toEmail;
	}
	@Override
	public String toString() {
		return "BulkMailBean [subject=" + subject + ", toEmail=" + toEmail + "]";
	}
	
	
}
