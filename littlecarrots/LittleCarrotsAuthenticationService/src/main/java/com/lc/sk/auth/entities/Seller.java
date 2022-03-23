/**
 * 
 */
package com.lc.sk.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Seller")
public class Seller implements Serializable {

	/*
	 * 
	 * create table Seller ( sellerid bigint auto_increment, sellername
	 * varchar(100), sellercompanyname varchar(100) unique, phonenumber bigint,
	 * address varchar(2000) , email varchar(100) , isocode varchar(10) , status
	 * int(1), constraint seller_pk primary key (sellerid) );
	 */
	@Id
	@Column(name = "sellerid")
	private long sellerid;

	@Column(name = "sellername")
	private String sellername;

	@Column(name = "sellercompanyname")
	private String sellercompanyname;

	@Column(name = "phonenumber")
	private long phone;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "isocode")
	private String isocode;

	@Column(name = "status")
	private boolean status;

	/**
	 * 
	 */
	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sellername
	 * @param sellercompanyname
	 * @param phone
	 * @param address
	 * @param email
	 * @param isocode
	 * @param status
	 */
	public Seller(String sellername, String sellercompanyname, long phone, String address, String email, String isocode,
			boolean status) {
		super();
		this.sellername = sellername;
		this.sellercompanyname = sellercompanyname;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.isocode = isocode;
		this.status = status;
	}

	/**
	 * @return the sellerid
	 */
	public long getSellerid() {
		return sellerid;
	}

	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(long sellerid) {
		this.sellerid = sellerid;
	}

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
		return "Seller [sellerid=" + sellerid + ", sellername=" + sellername + ", sellercompanyname="
				+ sellercompanyname + ", phone=" + phone + ", address=" + address + ", email=" + email + ", isocode="
				+ isocode + ", status=" + status + "]";
	}

}
