/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class User implements Serializable {

	private String username;
	private String password;
	private String rolename;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param username
// 	 * @param password
// 	 * @param rolename
// 	 */
// 	public User(String username, String password, String rolename) {
// 		super();
// 		this.username = username;
// 		this.password = password;
// 		this.rolename = rolename;
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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", rolename=" + rolename + "]";
	}

}
