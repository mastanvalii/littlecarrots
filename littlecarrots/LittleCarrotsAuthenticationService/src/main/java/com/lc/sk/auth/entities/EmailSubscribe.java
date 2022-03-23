package com.lc.sk.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "emailsubscribe")
public class EmailSubscribe implements Serializable {
	
	/*

create table emailsubscribe(
emailid varchar(200),
constraint useremail_pk primary key (emailid)
);
	*/
	
	@Id
	@Column(name= "emailid")
	private String emailid;

	public EmailSubscribe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailSubscribe(String emailid) {
		super();
		this.emailid = emailid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "EmailSubscribe [emailid=" + emailid + "]";
	}

	
	
	
}
