package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

public class SeasonWear implements Serializable {

	private long seasonId;

	private long subcatId;

	private String season;

	public SeasonWear() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SeasonWear(long seasonId, long subcatId, String season) {
// 		super();
// 		this.seasonId = seasonId;
// 		this.subcatId = subcatId;
// 		this.season = season;
// 	}

	@Override
	public String toString() {
		return "SeasonWear [seasonId=" + seasonId + ", subcatId=" + subcatId + ", season=" + season + "]";
	}

	public long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(long seasonId) {
		this.seasonId = seasonId;
	}

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

}
