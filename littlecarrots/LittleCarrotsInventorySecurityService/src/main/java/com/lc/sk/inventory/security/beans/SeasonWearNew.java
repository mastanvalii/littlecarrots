package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SeasonWearNew implements Serializable{
	
	private long subcatId;

	private String season;

	public SeasonWearNew() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SeasonWearNew(long subcatId, String season) {
// 		super();
// 		this.subcatId = subcatId;
// 		this.season = season;
// 	}

	public long getSubcatId() {
		return subcatId;
	}

	public void setSubcatId(long subcatId) {
		this.subcatId = subcatId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "SeasonWearNew [subcatId, season]";
	}

	

}
