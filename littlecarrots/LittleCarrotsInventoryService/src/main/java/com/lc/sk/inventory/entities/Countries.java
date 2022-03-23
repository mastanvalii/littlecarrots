/**
 * 
 */
package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */

@Entity
@Table(name = "Countries")
public class Countries implements Serializable{
	/*
	 * CREATE TABLE COUNTRIES( ISOCODE varchar(10), Country varchar(50) Not Null,
	 * ISDCODE varchar(10) Not Null, status int(1), constraint countries_pk PRIMARY
	 * KEY (ISOCODE) );
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 5737193169754272380L;

	@Id
	@Column(name = "ISOCODE")
	private String isocode;

	@Column(name = "Country")
	private String country;

	@Column(name = "isdcode")
	private String isdcode;

	@Column(name = "status")
	private boolean status;

	/**
	 * 
	 */
	public Countries() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param isocode
// 	 * @param country
// 	 * @param isdcode
// 	 * @param status
// 	 */
// 	public Countries(String isocode, String country, String isdcode, boolean status) {
// 		super();
// 		this.isocode = isocode;
// 		this.country = country;
// 		this.isdcode = isdcode;
// 		this.status = status;
// 	}

	/**
	 * @return the isocode
	 */
	public String getIsocode() {
		return isocode;
	}

	/**
	 * @param isocode the isocode to set
	 */
	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the isdcode
	 */
	public String getIsdcode() {
		return isdcode;
	}

	/**
	 * @param isdcode the isdcode to set
	 */
	public void setIsdcode(String isdcode) {
		this.isdcode = isdcode;
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Countries [isocode=" + isocode + ", country=" + country + ", isdcode=" + isdcode + ", status=" + status
				+ "]";
	}

}
