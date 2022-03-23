/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestParam;

import com.lc.sk.inventory.security.util.ConstantValues;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class WarehouseBean implements Serializable {

	private String warehousename;
	private String contactpersonname;
	private String contactphone;
	private  String email;
	private  String address;
	private String pincode;
	private String est;
	private boolean status;
	private String username;
	private String isocode;
	/**
	 * 
	 */
	public WarehouseBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param warehousename
// 	 * @param contactpersonname
// 	 * @param contactphone
// 	 * @param email
// 	 * @param address
// 	 * @param pincode
// 	 * @param est
// 	 * @param status
// 	 * @param username
// 	 * @param isocode
// 	 */
// 	public WarehouseBean(String warehousename, String contactpersonname, String contactphone, String email,
// 			String address, String pincode, String est, boolean status, String username, String isocode) {
// 		super();
// 		this.warehousename = warehousename;
// 		this.contactpersonname = contactpersonname;
// 		this.contactphone = contactphone;
// 		this.email = email;
// 		this.address = address;
// 		this.pincode = pincode;
// 		this.est = est;
// 		this.status = status;
// 		this.username = username;
// 		this.isocode = isocode;
// 	}
	/**
	 * @return the warehousename
	 */
	public String getWarehousename() {
		return warehousename;
	}
	/**
	 * @param warehousename the warehousename to set
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	/**
	 * @return the contactpersonname
	 */
	public String getContactpersonname() {
		return contactpersonname;
	}
	/**
	 * @param contactpersonname the contactpersonname to set
	 */
	public void setContactpersonname(String contactpersonname) {
		this.contactpersonname = contactpersonname;
	}
	/**
	 * @return the contactphone
	 */
	public String getContactphone() {
		return contactphone;
	}
	/**
	 * @param contactphone the contactphone to set
	 */
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the est
	 */
	public String getEst() {
		return est;
	}
	/**
	 * @param est the est to set
	 */
	public void setEst(String est) {
		this.est = est;
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
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
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
	@Override
	public String toString() {
		return "WarehouseBean [warehousename=" + warehousename + ", contactpersonname=" + contactpersonname
				+ ", contactphone=" + contactphone + ", email=" + email + ", address=" + address + ", pincode="
				+ pincode + ", est=" + est + ", status=" + status + ", username=" + username + ", isocode=" + isocode
				+ "]";
	}
	
	
	
}
