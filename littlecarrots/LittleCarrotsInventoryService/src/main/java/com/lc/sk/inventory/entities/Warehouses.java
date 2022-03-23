/**
 * 
 */
package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */

@Entity
@Table(name = "Warehouses")
public class Warehouses implements Serializable {

	/*
	 * 
	 * #--------warehouses TABLE-------- create table warehouses (WarehouseID
	 * bigint(20) auto_increment, WarehouseName varchar(50), ContactPersonName
	 * varchar(50) , ContactPhone bigint(15) unique not null, Email varchar(500)
	 * unique not null , Address varchar(500) , PinCode bigint(7) not null , EST
	 * int(8) , warehouse_Status int(1) , WhoCreated varchar(50),
	 * DateofCreation varchar(30), WhoUpdated varchar(50), ModifyDate date, ISOCODE
	 * varchar(10), CONSTRAINT warehouse_pk PRIMARY KEY (warehouseid), CONSTRAINT
	 * warehouse_fk FOREIGN KEY (isocode) references countries(isocode), CONSTRAINT
	 * warehouse_fk_whocreated FOREIGN KEY (whoupdated) references
	 * little_carrots_authentications.authorizedusers(username) );
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="warehouseid")
	private long warehouseid;
	
	@Column(name="warehousename")
	private String warehousename;
	
	@Column(name="contactpersonname")
	private String contactpersonname;
	
	@Column(name="contactphone")
	private long contactphone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="pincode")
	private long pincode;
	
	@Column(name="est")
	private long est;
	
	@Column(name="warehouse_status")
	private boolean warehouse_status;
	
	@Column(name="whocreated")
	private String whocreated;
	
	@Column(name="dateofcreation")
	private Timestamp  dateofcreation;
	
	@Column(name="whoupdated")
	private String whoupdated;
	
	@Column(name="modifydate")
	private Timestamp modifydate;
	
	@Column(name="isocode")
	private String isocode;

	public Warehouses() {
		super();
	}

	/**
	 * @param warehousename
	 * @param contactpersonname
	 * @param contactphone
	 * @param email
	 * @param address
	 * @param pincode
	 * @param est
	 * @param warehouse_status
	 * @param whocreated
	 * @param dateofcreation
	 * @param whoupdated
	 * @param modifydate
	 * @param isocode
	 */
	public Warehouses(String warehousename, String contactpersonname, long contactphone, String email, String address,
			long pincode, long est, boolean warehouse_status, String whocreated, Timestamp dateofcreation,
			String whoupdated, Timestamp modifydate, String isocode) {
		super();
		this.warehousename = warehousename;
		this.contactpersonname = contactpersonname;
		this.contactphone = contactphone;
		this.email = email;
		this.address = address;
		this.pincode = pincode;
		this.est = est;
		this.warehouse_status = warehouse_status;
		this.whocreated = whocreated;
		this.dateofcreation = dateofcreation;
		this.whoupdated = whoupdated;
		this.modifydate = modifydate;
		this.isocode = isocode;
	}

	/**
	 * @return the warehouseid
	 */
	public long getWarehouseid() {
		return warehouseid;
	}

	/**
	 * @param warehouseid the warehouseid to set
	 */
	public void setWarehouseid(long warehouseid) {
		this.warehouseid = warehouseid;
	}

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
	public long getContactphone() {
		return contactphone;
	}

	/**
	 * @param contactphone the contactphone to set
	 */
	public void setContactphone(long contactphone) {
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
	public long getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the est
	 */
	public long getEst() {
		return est;
	}

	/**
	 * @param est the est to set
	 */
	public void setEst(long est) {
		this.est = est;
	}

	/**
	 * @return the warehouse_status
	 */
	public boolean getWarehouse_status() {
		return warehouse_status;
	}

	/**
	 * @param warehouse_status the warehouse_status to set
	 */
	public void setWarehouse_status(boolean warehouse_status) {
		this.warehouse_status = warehouse_status;
	}

	/**
	 * @return the whocreated
	 */
	public String getWhocreated() {
		return whocreated;
	}

	/**
	 * @param whocreated the whocreated to set
	 */
	public void setWhocreated(String whocreated) {
		this.whocreated = whocreated;
	}

	/**
	 * @return the dateofcreation
	 */
	public Timestamp getDateofcreation() {
		return dateofcreation;
	}

	/**
	 * @param dateofcreation the dateofcreation to set
	 */
	public void setDateofcreation(Timestamp dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	/**
	 * @return the whoupdated
	 */
	public String getWhoupdated() {
		return whoupdated;
	}

	/**
	 * @param whoupdated the whoupdated to set
	 */
	public void setWhoupdated(String whoupdated) {
		this.whoupdated = whoupdated;
	}

	/**
	 * @return the modifydate
	 */
	public Timestamp getModifydate() {
		return modifydate;
	}

	/**
	 * @param modifydate the modifydate to set
	 */
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
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
		return "Warehouses [warehouseid=" + warehouseid + ", warehousename=" + warehousename + ", contactpersonname="
				+ contactpersonname + ", contactphone=" + contactphone + ", email=" + email + ", address=" + address
				+ ", pincode=" + pincode + ", est=" + est + ", warehouse_status=" + warehouse_status + ", whocreated="
				+ whocreated + ", dateofcreation=" + dateofcreation + ", whoupdated=" + whoupdated + ", modifydate="
				+ modifydate + ", isocode=" + isocode + "]";
	}

	
	
	
	
	
}
