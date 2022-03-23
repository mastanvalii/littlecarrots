package com.lc.sk.inventory.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


	
	@Entity
	@Table(name = "categories")
	public class Categories implements Serializable {

		/*
		 * 
		 * create table Categories(CatID int auto_increment, 
		 * category varchar(200), 
		 * gender varchar(10), 
		 * constraint categories_pk primary key (catid),
		 * constraint categories_fk1 foreign key (gender) references genders (genderid));
		 */
		
		@Id
		@Column(name="catid")
		private long catid;
		
		@Column(name = "category")
		private String category;

		@Column(name = "gender")
		private String gender;

		public Categories() {
			super();

		}

		public Categories(String category, String gender) {
			super();
			this.category = category;
			this.gender = gender;
		}

		public long getCatid() {
			return catid;
		}

		public void setCatid(long catid) {
			this.catid = catid;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Categories [catid=" + catid + ", category=" + category + ", gender=" + gender + "]";
		}

		

		
		
		
		
		
		
		
		
		
		
		
		
	}
	
  		
