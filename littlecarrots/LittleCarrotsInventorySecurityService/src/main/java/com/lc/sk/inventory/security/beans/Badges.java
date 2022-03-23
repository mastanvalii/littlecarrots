package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class Badges implements Serializable {

	private String badge;
	private String description;
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
// TODO Remove unused code found by UCDetector
// 	public Badges(String badge, String description) {
// 		super();
// 		this.badge = badge;
// 		this.description = description;
// 	}
	public Badges() {
		super();
	}
	@Override
	public String toString() {
		return "Badges [badge=" + badge + ", description=" + description + "]";
	}
	
}
