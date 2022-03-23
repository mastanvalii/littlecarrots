/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class Authorization implements Serializable {

	private String RoleName;
	private String Descriptions;
	private boolean authorization_status;
	private int responsecode;

	public Authorization() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public Authorization(String roleName, String descriptions, boolean authorization_status) {
// 		super();
// 		RoleName = roleName;
// 		Descriptions = descriptions;
// 		this.authorization_status = authorization_status;
// 	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getDescriptions() {
		return Descriptions;
	}

	public void setDescriptions(String descriptions) {
		Descriptions = descriptions;
	}

	public boolean getAuthorization_status() {
		return authorization_status;
	}

	public void setAuthorization_status(boolean authorization_status) {
		this.authorization_status = authorization_status;
	}

	@Override
	public String toString() {
		return "Authorization [RoleName=" + RoleName + ", Descriptions=" + Descriptions + ", authorization_status="
				+ authorization_status + "]";
	}

	/**
	 * @return the responsecode
	 */
	public int getResponsecode() {
		return responsecode;
	}

	/**
	 * @param responsecode the responsecode to set
	 */
	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}

	
}
