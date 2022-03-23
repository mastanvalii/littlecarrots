package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seasonwear")
public class SeasonWear implements Serializable {

	@Id
	@Column(name="seasonid")
	private long seasonId;
	
	@Column(name="season")
	private String season;
	
	@Column(name="subcatid")
	private long subcatId;

	public SeasonWear() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeasonWear(String season, long subcatId) {
		super();
		this.season = season;
		this.subcatId = subcatId;
	}

	public long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(long seasonId) {
		this.seasonId = seasonId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public long getSubcatId() {
		return subcatId;
	}

	public void setSubcatId(long subcatId) {
		this.subcatId = subcatId;
	}

	@Override
	public String toString() {
		return "SeasonWear [seasonId=" + seasonId + ", season=" + season + ", subcatId=" + subcatId + "]";
	}
	
	
	
}
