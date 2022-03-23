/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class SellerUsers implements Serializable {

	private String username;

	private String password;

	private String email;

	private long sellerid;

	private String fullname;

	private boolean status;

	private String rolename;

	private Timestamp lastlogin;

	/**
	 * 
	 */
	public SellerUsers() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param username
// 	 * @param password
// 	 * @param email
// 	 * @param sellerid
// 	 * @param fullname
// 	 * @param status
// 	 * @param rolename
// 	 * @param lastlogin
// 	 */
// 	public SellerUsers(String username, String password, String email, long sellerid, String fullname, boolean status,
// 			String rolename, Timestamp lastlogin) {
// 		super();
// 		this.username = username;
// 		this.password = password;
// 		this.email = email;
// 		this.sellerid = sellerid;
// 		this.fullname = fullname;
// 		this.status = status;
// 		this.rolename = rolename;
// 		this.lastlogin = lastlogin;
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
		return "SellerUsers [username=" + username + ", password=" + password + ", email=" + email + ", sellerid="
				+ sellerid + ", fullname=" + fullname + ", status=" + status + ", rolename=" + rolename + ", lastlogin="
				+ lastlogin + "]";
	}

}
