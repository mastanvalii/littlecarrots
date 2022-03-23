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
public class NewColor implements Serializable {
	private String colorname; 
	private String hashcode;
	/**
	 * 
	 */
	public NewColor() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param colorname
// 	 * @param hashcode
// 	 */
// 	public NewColor(String colorname, String hashcode) {
// 		super();
// 		this.colorname = colorname;
// 		this.hashcode = hashcode;
// 	}
	/**
	 * @return the colorname
	 */
	public String getColorname() {
		return colorname;
	}
	/**
	 * @param colorname the colorname to set
	 */
	public void setColorname(String colorname) {
		this.colorname = colorname;
	}
	/**
	 * @return the hashcode
	 */
	public String getHashcode() {
		return hashcode;
	}
	/**
	 * @param hashcode the hashcode to set
	 */
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	@Override
	public String toString() {
		return "NewColor [colorname, hashcode]";
	}
	
	
}
