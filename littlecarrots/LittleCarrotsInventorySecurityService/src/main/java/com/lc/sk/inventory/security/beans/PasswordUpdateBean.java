package com.lc.sk.inventory.security.beans;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class PasswordUpdateBean implements Serializable {

	private String username;
	private String password;
	private String newPassword;
	/**
	 * 
	 */
	public PasswordUpdateBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param username
// 	 * @param password
// 	 * @param newPassword
// 	 */
// 	public PasswordUpdateBean(String username, String password, String newPassword) {
// 		super();
// 		this.username = username;
// 		this.password = password;
// 		this.newPassword = newPassword;
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
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
