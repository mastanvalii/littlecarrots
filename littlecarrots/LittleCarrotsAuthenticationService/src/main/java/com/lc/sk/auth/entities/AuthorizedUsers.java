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
@Table(name = "authorizedusers")
public class AuthorizedUsers implements Serializable {

	
	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "authuserstatus")
	private boolean authuserstatus;

	@Column(name = "rolename")
	private String rolename;

	@Column(name = "lastlogindate")
	private Timestamp lastlogindate;

	/**
	 * 
	 */
	public AuthorizedUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param authuserstatus
	 * @param rolename
	 * @param lastlogindate
	 */
	public AuthorizedUsers(String username, String email, String password, boolean authuserstatus, String rolename,
			Timestamp lastlogindate) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.authuserstatus = authuserstatus;
		this.rolename = rolename;
		this.lastlogindate = lastlogindate;
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
	 * @return the authuserstatus
	 */
	public boolean getAuthuserstatus() {
		return authuserstatus;
	}

	/**
	 * @param authuserstatus the authuserstatus to set
	 */
	public void setAuthuserstatus(boolean authuserstatus) {
		this.authuserstatus = authuserstatus;
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
	 * @return the lastlogindate
	 */
	public Timestamp getLastlogindate() {
		return lastlogindate;
	}

	/**
	 * @param date the lastlogindate to set
	 */
	public void setLastlogindate(Timestamp date) {
		this.lastlogindate = date;
	}

	@Override
	public String toString() {
		return "AuthorizedUsers [username=" + username + ", email=" + email + ", password=" + password
				+ ", authuserstatus=" + authuserstatus + ", rolename=" + rolename + ", lastlogindate=" + lastlogindate
				+ "]";
	}

}
