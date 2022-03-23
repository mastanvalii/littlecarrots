/**
 * 
 */
package com.lc.sk.inventory.security.beans;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class SubCats {

	private long subcatid;
	private String category;
	private String subcategory;
	private String gender;
	/**
	 * 
	 */
	public SubCats() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param subcatid
	 * @param category
	 * @param subcategory
	 * @param gender
	 */
	public SubCats(long subcatid, String category, String subcategory, String gender) {
		super();
		this.subcatid = subcatid;
		this.category = category;
		this.subcategory = subcategory;
		this.gender = gender;
	}
	/**
	 * @return the subcatid
	 */
	public long getSubcatid() {
		return subcatid;
	}
	/**
	 * @param subcatid the subcatid to set
	 */
	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}
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
	 * @return the subcategory
	 */
	public String getSubcategory() {
		return subcategory;
	}
	/**
	 * @param subcategory the subcategory to set
	 */
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
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
	
	
	
}
