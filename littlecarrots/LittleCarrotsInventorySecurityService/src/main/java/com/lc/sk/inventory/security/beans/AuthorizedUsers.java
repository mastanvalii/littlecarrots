/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class AuthorizedUsers implements Serializable {

	private String username;
	private String email;
	private String password;
	private boolean authuserstatus;
	private String rolename;
	private Timestamp lastlogindate;


	public AuthorizedUsers() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param username
// 	 * @param email
// 	 * @param password
// 	 * @param authuserstatus
// 	 * @param rolename
// 	 * @param lastlogindate
// 	 */
// 	public AuthorizedUsers(String username, String email, String password, boolean authuserstatus, String rolename,
// 			Timestamp lastlogindate) {
// 		super();
// 		this.username = username;
// 		this.email = email;
// 		this.password = password;
// 		this.authuserstatus = authuserstatus;
// 		this.rolename = rolename;
// 		this.lastlogindate = lastlogindate;
// 	}

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
	 * @param lastlogindate the lastlogindate to set
	 */
	public void setLastlogindate(Timestamp lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	@Override
	public String toString() {
		return "AuthorizedUsers [username=" + username + ", email=" + email + ", password=" + password
				+ ", authuserstatus=" + authuserstatus + ", rolename=" + rolename + ", lastlogindate=" + lastlogindate
				+ "]";
	}

}
