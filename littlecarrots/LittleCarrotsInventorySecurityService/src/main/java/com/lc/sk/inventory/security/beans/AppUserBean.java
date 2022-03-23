/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class AppUserBean implements Serializable {

	private String username;
	private String password;
	private String rolename;
	private String email;
	private boolean status;

	/**
	 * 
	 */
	public AppUserBean() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param username
// 	 * @param password
// 	 * @param rolename
// 	 * @param email
// 	 * @param status
// 	 */
// 	public AppUserBean(String username, String password, String rolename, String email, boolean status) {
// 		super();
// 		this.username = username;
// 		this.password = password;
// 		this.rolename = rolename;
// 		this.email = email;
// 		this.status = status;
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

	@Override
	public String toString() {
		return "AppUserBean [username=" + username + ", password=" + password + ", rolename=" + rolename + ", email="
				+ email + ", status=" + status + "]";
	}

}
