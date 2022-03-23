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
public class NewCategory implements Serializable {
	private String category;
	private String gender;
	/**
	 * 
	 */
	public NewCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param category
// 	 * @param gender
// 	 */
// 	public NewCategory(String category, String gender) {
// 		super();
// 		this.category = category;
// 		this.gender = gender;
// 	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "NewCategory [category, gender]";
	}
	
	
}
