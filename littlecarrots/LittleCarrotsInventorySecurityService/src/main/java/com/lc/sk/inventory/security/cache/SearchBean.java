package com.lc.sk.inventory.security.cache;

import java.io.Serializable;
import java.util.Arrays;

public class SearchBean implements Serializable {

	String gender[];
	String category[];
	double price[];
	String age[];
	String fashion[];
	String special[];
	public SearchBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	public SearchBean(String[] gender, String[] category, double[] price, String[] age, String[] fashion,
// 			String[] special) {
// 		super();
// 		this.gender = gender;
// 		this.category = category;
// 		this.price = price;
// 		this.age = age;
// 		this.fashion = fashion;
// 		this.special = special;
// 	}
	public String[] getGender() {
		return gender;
	}
	public void setGender(String[] gender) {
		this.gender = gender;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	public double[] getPrice() {
		return price;
	}
	public void setPrice(double[] price) {
		this.price = price;
	}
	public String[] getAge() {
		return age;
	}
	public void setAge(String[] age) {
		this.age = age;
	}
	public String[] getFashion() {
		return fashion;
	}
	public void setFashion(String[] fashion) {
		this.fashion = fashion;
	}
	public String[] getSpecial() {
		return special;
	}
	public void setSpecial(String[] special) {
		this.special = special;
	}
	@Override
	public String toString() {
		return "SearchBean [gender=" + Arrays.toString(gender) + ", category=" + Arrays.toString(category) + ", price="
				+ Arrays.toString(price) + ", age=" + Arrays.toString(age) + ", fashion=" + Arrays.toString(fashion)
				+ ", special=" + Arrays.toString(special) + "]";
	}
	
	
	
}
