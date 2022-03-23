/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class Countries implements Serializable {

	private String isocode;

	private String country;

	private String isdcode;

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
