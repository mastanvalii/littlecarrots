/**
 * 
 */
package com.lc.sk.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */

@Entity
@Table(name = "authorization")
public class Authorization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -259335148133481580L;

	/*
	 * 
	 * create table authorization( RoleName varchar(20), Descriptions varchar(200),
	 * authorization_status int(1), constraint authorization_pk1 primary key
	 * (ROLENAME));
	 * 
	 * 
	 */
	// ENTITY FIELDS
	@Id
	@Column(name = "rolename")
	private String RoleName;

	@Column(name = "descriptions")
	private String Descriptions;

	@Column(name = "authorization_status")
	private boolean authorization_status;

	/**
	 * 
	 */
	public Authorization() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param roleName
	 * @param descriptions
	 * @param authorization_status
	 */
	public Authorization(String roleName, String descriptions, boolean authorization_status) {
		super();
		RoleName = roleName;
		Descriptions = descriptions;
		this.authorization_status = authorization_status;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return RoleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	/**
	 * @return the descriptions
	 */
	public String getDescriptions() {
		return Descriptions;
	}

	/**
	 * @param descriptions the descriptions to set
	 */
	public void setDescriptions(String descriptions) {
		Descriptions = descriptions;
	}

	/**
	 * @return the authorization_status
	 */
	public boolean getAuthorization_status() {
		return authorization_status;
	}

	/**
	 * @param authorization_status the authorization_status to set
	 */
	public void setAuthorization_status(boolean authorization_status) {
		this.authorization_status = authorization_status;
	}

	@Override
	public String toString() {
		return "Authorization [RoleName=" + RoleName + ", Descriptions=" + Descriptions + ", authorization_status="
				+ authorization_status + "]";
	}
}
