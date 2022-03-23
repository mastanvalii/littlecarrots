/**
 * 
 */
package com.lc.sk.inventory.security.beans;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class SeasonCats {

	private long seasonid;
	private String category;
	private String subcategory;
	private String season;
	private String gender;
	/**
	 * 
	 */
	public SeasonCats() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param seasonid
	 * @param category
	 * @param subcategory
	 * @param season
	 * @param gender
	 */
	public SeasonCats(long seasonid, String category, String subcategory, String season, String gender) {
		super();
		this.seasonid = seasonid;
		this.category = category;
		this.subcategory = subcategory;
		this.season = season;
		this.gender = gender;
	}
	/**
	 * @return the seasonid
	 */
	public long getSeasonid() {
		return seasonid;
	}
	/**
	 * @param seasonid the seasonid to set
	 */
	public void setSeasonid(long seasonid) {
		this.seasonid = seasonid;
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
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}
	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
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
		return "SeasonCats [seasonid=" + seasonid + ", category=" + category + ", subcategory=" + subcategory
				+ ", season=" + season + ", gender=" + gender + "]";
	}
	
	
	
	
}
