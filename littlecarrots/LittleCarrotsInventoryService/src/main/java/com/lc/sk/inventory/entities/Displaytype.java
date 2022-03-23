package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "displaytype")
public class Displaytype implements Serializable {

	/*
		CREATE TABLE displaytype(
		display varchar(20),
		description varchar(100),
		constraint displaytype_pk primary key (display)
		);
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680561487730361813L;

	@Id
	@Column(name="display")
	private String display;
	
	@Column(name="description")
	private String description;

	public Displaytype() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public Displaytype(String display, String description) {
// 		super();
// 		this.display = display;
// 		this.description = description;
// 	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Displaytype [display=" + display + ", description=" + description + "]";
	}
	
	
}
