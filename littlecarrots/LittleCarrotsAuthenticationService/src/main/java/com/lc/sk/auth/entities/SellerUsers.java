/**
 * 
 */
package com.lc.sk.auth.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sellerusers")
public class SellerUsers implements Serializable {

	/*
	 * 
	 * create table sellerusers ( username varchar(100), password varchar(2000),
	 * email varchar(200) unique, sellerid bigint, fullname varchar(100), status
	 * int(1), rolename varchar(100), lastlogin date, constraint selleruser_pk
	 * primary key (username), constraint selleruser_fk foreign key (sellerid)
	 * references seller(sellerid), constraint selleruser_fk1 foreign key (rolename)
	 * references authorization(rolename) );
	 */

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "sellerid")
	private long sellerid;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "status")
	private boolean status;

	@Column(name = "rolename")
	private String rolename;

	@Column(name = "lastlogin")
	private Timestamp lastlogin;

	/**
	 * 
	 */
	public SellerUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param email
	 * @param sellerid
	 * @param fullname
	 * @param status
	 * @param rolename
	 * @param lastlogin
	 */
	public SellerUsers(String username, String password, String email, long sellerid, String fullname, boolean status,
			String rolename, Timestamp lastlogin) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.sellerid = sellerid;
		this.fullname = fullname;
		this.status = status;
		this.rolename = rolename;
		this.lastlogin = lastlogin;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the sellerid
	 */
	public long getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(long sellerid) {
		this.sellerid = sellerid;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	/**
	 * @return the lastlogin
	 */
	public Timestamp getLastlogin() {
		return lastlogin;
	}

	/**
	 * @param lastlogin the lastlogin to set
	 */
	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Override
	public String toString() {
		return "SalesUser [username=" + username + ", password=" + password + ", email=" + email + ", sellerid="
				+ sellerid + ", fullname=" + fullname + ", status=" + status + ", rolename=" + rolename + ", lastlogin="
				+ lastlogin + "]";
	}

}
