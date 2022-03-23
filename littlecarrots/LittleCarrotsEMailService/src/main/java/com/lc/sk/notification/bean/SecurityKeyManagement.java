/**
 * 
 */
package com.lc.sk.notification.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */

@Entity
@Table(name = "securitykeymanagement")
public class SecurityKeyManagement implements Serializable {

	@Id
	@Column(name = "keyid")
	private long keyId;

	@Column(name = "keyname")
	private String keyName;

	@Column(name = "keyvalue")
	private String keyValue;

	/**
	 * 
	 */
	public SecurityKeyManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param keyId
	 * @param keyName
	 * @param keyValue
	 */
	public SecurityKeyManagement(long keyId, String keyName, String keyValue) {
		super();
		this.keyId = keyId;
		this.keyName = keyName;
		this.keyValue = keyValue;
	}

	/**
	 * @return the keyId
	 */
	public long getKeyId() {
		return keyId;
	}

	/**
	 * @param keyId the keyId to set
	 */
	public void setKeyId(long keyId) {
		this.keyId = keyId;
	}

	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return the keyValue
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue the keyValue to set
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public String toString() {
		return "SecurityKeyManagement [keyId=" + keyId + ", keyName=" + keyName + ", keyValue=" + keyValue + "]";
	}

	
}
