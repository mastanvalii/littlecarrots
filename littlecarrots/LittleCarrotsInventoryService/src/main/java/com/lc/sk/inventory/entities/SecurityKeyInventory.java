/**
 * 
 */
package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */

@Entity
@Table(name="securitykeyinventory")
public class SecurityKeyInventory implements Serializable{

	/*keyid bigint auto_increment,
keyname varchar(100),
keyvalue varchar(1000),
*/
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9162939526988219926L;

	@Id
	@Column(name="keyid")
	private long keyId;
	
	@Column(name="keyname")
	private String keyName;
	
	
	@Column(name="keyvalue")
	private String keyValue;


	/**
	 * 
	 */
	public SecurityKeyInventory() {
		super();
		// TODO Auto-generated constructor stub
	}


// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param keyId
// 	 * @param keyName
// 	 * @param keyValue
// 	 */
// 	public SecurityKeyInventory(String keyName, String keyValue) {
// 		super();
// 		this.keyName = keyName;
// 		this.keyValue = keyValue;
// 	}


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
		return "SecurityKeyInventory [keyId=" + keyId + ", keyName=" + keyName + ", keyValue=" + keyValue + "]";
	}
	
	
	
}
