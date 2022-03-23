/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class NewSellerBean implements Serializable {
	
	private String sellername;
	private String sellercompanyname;
	private long phone;
	private String address;
	private String email;
	private String isocode;
	private boolean status;
	/**
	 * 
	 */
	public NewSellerBean() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param sellername
// 	 * @param sellercompanyname
// 	 * @param phone
// 	 * @param address
// 	 * @param email
// 	 * @param isocode
// 	 * @param status
// 	 */
// 	public NewSellerBean(String sellername, String sellercompanyname, long phone, String address, String email,
// 			String isocode, boolean status) {
// 		super();
// 		this.sellername = sellername;
// 		this.sellercompanyname = sellercompanyname;
// 		this.phone = phone;
// 		this.address = address;
// 		this.email = email;
// 		this.isocode = isocode;
// 		this.status = status;
// 	}
	/**
	 * @return the sellername
	 */
	public String getSellername() {
		return sellername;
	}
	/**
	 * @param sellername the sellername to set
	 */
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	/**
	 * @return the sellercompanyname
	 */
	public String getSellercompanyname() {
		return sellercompanyname;
	}
	/**
	 * @param sellercompanyname the sellercompanyname to set
	 */
	public void setSellercompanyname(String sellercompanyname) {
		this.sellercompanyname = sellercompanyname;
	}
	/**
	 * @return the phone
	 */
	public long getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(long phone) {
		this.phone = phone;
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
		return "NewSellerBean [sellername=" + sellername + ", sellercompanyname=" + sellercompanyname + ", phone="
				+ phone + ", address=" + address + ", email=" + email + ", isocode=" + isocode + ", status=" + status
				+ "]";
	}
	
	
}
