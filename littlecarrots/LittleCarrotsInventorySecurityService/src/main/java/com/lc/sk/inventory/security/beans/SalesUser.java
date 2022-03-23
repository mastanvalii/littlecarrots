/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class SalesUser implements Serializable {

	private String username;
	private String password;
	private String rolename;
	private String email;
	private String fullname;
	private String sellerid;
	private boolean status;
	/**
	 * 
	 */
	public SalesUser() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param username
// 	 * @param password
// 	 * @param rolename
// 	 * @param email
// 	 * @param fullname
// 	 * @param sellerid
// 	 * @param status
// 	 */
// 	public SalesUser(String username, String password, String rolename, String email, String fullname, String sellerid,
// 			boolean status) {
// 		super();
// 		this.username = username;
// 		this.password = password;
// 		this.rolename = rolename;
// 		this.email = email;
// 		this.fullname = fullname;
// 		this.sellerid = sellerid;
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
	 * @return the sellerid
	 */
	public String getSellerid() {
		return sellerid;
	}
	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
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
	
	
}
