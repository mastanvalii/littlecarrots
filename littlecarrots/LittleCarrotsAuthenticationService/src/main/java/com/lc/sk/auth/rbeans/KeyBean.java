/**
 * 
 */
package com.lc.sk.auth.rbeans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
public class KeyBean implements Serializable {

	private String name;
	private String value;

	/**
	 * 
	 */
	public KeyBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param value
	 */
	public KeyBean(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
