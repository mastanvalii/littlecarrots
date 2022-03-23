package com.lc.sk.auth.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "useremails")
public class UserEmails implements Serializable {
	
	@Id
	@Column(name="id")
	private long id;
	
	
	@Column(name="username")
	private String username ;
	
	@Column(name="rolename")
	private String rolename;
	
	@Column(name="email")
	private String email;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="message")
	private String message;
	
	@Column(name="sentdate")
	private Timestamp sentdate;

	public UserEmails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEmails(String username, String rolename, String email, String subject, String message,
			Timestamp sentdate) {
		super();
	//	this.id = id;
		this.username = username;
		this.rolename = rolename;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.sentdate = sentdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getSentdate() {
		return sentdate;
	}

	public void setSentdate(Timestamp sentdate) {
		this.sentdate = sentdate;
	}

	@Override
	public String toString() {
		return "UserEmails [id=" + id + ", username=" + username + ", rolename=" + rolename + ", email=" + email
				+ ", subject=" + subject + ", message=" + message + ", sentdate=" + sentdate + "]";
	}
	
	

}
