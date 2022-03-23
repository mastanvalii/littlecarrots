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
public class UserRoles implements Serializable {

	private String rolename;
	private String description;
	private boolean status;
	/**
	 * 
	 */
	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param rolename
// 	 * @param description
// 	 * @param status
// 	 */
// 	public UserRoles(String rolename, String description, boolean status) {
// 		super();
// 		this.rolename = rolename;
// 		this.description = description;
// 		this.status = status;
// 	}
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
