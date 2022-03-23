/**
 * 
 */
package com.lc.sk.inventory.security.beans;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class OccasionCats {
	private long occasionid;
	private String category;
	private String subcategory;
	private String occasion;
	private String gender;
	/**
	 * 
	 */
	public OccasionCats() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param occasionid
	 * @param category
	 * @param subcategory
	 * @param occasion
	 * @param gender
	 */
	public OccasionCats(long occasionid, String category, String subcategory, String occasion, String gender) {
		super();
		this.occasionid = occasionid;
		this.category = category;
		this.subcategory = subcategory;
		this.occasion = occasion;
		this.gender = gender;
	}
	/**
	 * @return the occasionid
	 */
	public long getOccasionid() {
		return occasionid;
	}
	/**
	 * @param occasionid the occasionid to set
	 */
	public void setOccasionid(long occasionid) {
		this.occasionid = occasionid;
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
	 * @return the occasion
	 */
	public String getOccasion() {
		return occasion;
	}
	/**
	 * @param occasion the occasion to set
	 */
	public void setOccasion(String occasion) {
		this.occasion = occasion;
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
