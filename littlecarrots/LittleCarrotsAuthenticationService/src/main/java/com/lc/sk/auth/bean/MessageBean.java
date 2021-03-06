/**
 * 
 */
package com.lc.sk.auth.bean;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public class MessageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7011981885279429239L;
	private String toEmail;
	private String subject;
	private String information;
	
	public MessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param toEmail
	 * @param subject
	 * @param information
	 */
	MessageBean(String toEmail, String subject, String information) {
		super();
		this.toEmail = toEmail;
		this.subject = subject;
		this.information = information;
	}
	/**
	 * @return the toEmail
	 */
	public String getToEmail() {
		return toEmail;
	}
	/**
	 * @param toEmail the toEmail to set
	 */
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}
	/**
	 * @param information the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}
	
	
	@Override
	public String toString() {
		return "MessageBean [toEmail=" + toEmail + ", subject=" + subject + ", information=" + information + "]";
	}
	
}
