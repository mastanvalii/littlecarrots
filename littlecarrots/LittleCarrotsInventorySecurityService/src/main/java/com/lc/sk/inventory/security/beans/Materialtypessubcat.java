package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Materialtypessubcat implements Serializable{
	
	private long Material_ID;
	private String Material_Name;
	private String Description;
	private String Occasion;
	private String Occasion_Subcategory;
	private String Season;
	private String Season_Subcategory;
	private String Category;
	
	
	public Materialtypessubcat() {
		super();
		// TODO Auto-generated constructor stub
	}


// TODO Remove unused code found by UCDetector
// 	public Materialtypessubcat(long material_ID, String material_Name, String description, String occasion,
// 			String occasion_Subcategory, String season, String season_Subcategory, String category) {
// 		super();
// 		Material_ID = material_ID;
// 		Material_Name = material_Name;
// 		Description = description;
// 		Occasion = occasion;
// 		Occasion_Subcategory = occasion_Subcategory;
// 		Season = season;
// 		Season_Subcategory = season_Subcategory;
// 		Category = category;
// 	}


	


	public long getMaterial_ID() {
		return Material_ID;
	}


	public void setMaterial_ID(long material_ID) {
		Material_ID = material_ID;
	}


	public String getMaterial_Name() {
		return Material_Name;
	}


	public void setMaterial_Name(String material_Name) {
		Material_Name = material_Name;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getOccasion() {
		return Occasion;
	}


	public void setOccasion(String occasion) {
		Occasion = occasion;
	}


	public String getOccasion_Subcategory() {
		return Occasion_Subcategory;
	}


	public void setOccasion_Subcategory(String occasion_Subcategory) {
		Occasion_Subcategory = occasion_Subcategory;
	}


	public String getSeason() {
		return Season;
	}


	public void setSeason(String season) {
		Season = season;
	}


	public String getSeason_Subcategory() {
		return Season_Subcategory;
	}


	public void setSeason_Subcategory(String season_Subcategory) {
		Season_Subcategory = season_Subcategory;
	}


	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	@Override
	public String toString() {
		return "Materialtypessubcat [Material_ID=" + Material_ID + ", Material_Name=" + Material_Name + ", Description="
				+ Description + ", Occasion=" + Occasion + ", Occasion_Subcategory=" + Occasion_Subcategory
				+ ", Season=" + Season + ", Season_Subcategory=" + Season_Subcategory + ", Category=" + Category + "]";
	}


	



	
	
	
	
	

}
