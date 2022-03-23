package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "badges")
public class Badges implements Serializable {

	/*
	 * CREATE TABLE badges(
badge varchar(10),
description varchar(100),
constraint collectioncode_pk primary key (badge)
);
	 */
	
	@Id
	@Column(name="badge")
	private String badge;
	
	@Column(name="description")
	private String description;

	public Badges() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Badges(String badge, String description) {
		super();
		this.badge = badge;
		this.description = description;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Badges [badge=" + badge + ", description=" + description + "]";
	}
	
	
}
