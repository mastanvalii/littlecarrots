package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Warehouses implements Serializable {

	private long warehouseid;

	private String warehousename;

	private String contactpersonname;

	private long contactphone;

	private String email;

	private String address;

	private long pincode;

	private long est;

	private boolean warehouse_status;

	private String whocreated;

	private Timestamp dateofcreation;

	private String whoupdated;

	private Timestamp modifydate;

	private String isocode;

	public Warehouses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(long warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getContactpersonname() {
		return contactpersonname;
	}

	public void setContactpersonname(String contactpersonname) {
		this.contactpersonname = contactpersonname;
	}

	public long getContactphone() {
		return contactphone;
	}

	public void setContactphone(long contactphone) {
		this.contactphone = contactphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public long getEst() {
		return est;
	}

	public void setEst(long est) {
		this.est = est;
	}

	public boolean getWarehouse_status() {
		return warehouse_status;
	}

	public void setWarehouse_status(boolean warehouse_status) {
		this.warehouse_status = warehouse_status;
	}

	public String getWhocreated() {
		return whocreated;
	}

	public void setWhocreated(String whocreated) {
		this.whocreated = whocreated;
	}

	public Timestamp getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(Timestamp dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public String getWhoupdated() {
		return whoupdated;
	}

	public void setWhoupdated(String whoupdated) {
		this.whoupdated = whoupdated;
	}

	public Timestamp getModifydate() {
		return modifydate;
	}

	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}

	public String getIsocode() {
		return isocode;
	}

	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}

// TODO Remove unused code found by UCDetector
// 	public Warehouses(long warehouseid, String warehousename, String contactpersonname, long contactphone, String email,
// 			String address, long pincode, long est, boolean warehouse_status, String whocreated, Timestamp dateofcreation,
// 			String whoupdated, Timestamp modifydate, String isocode) {
// 		super();
// 		this.warehouseid = warehouseid;
// 		this.warehousename = warehousename;
// 		this.contactpersonname = contactpersonname;
// 		this.contactphone = contactphone;
// 		this.email = email;
// 		this.address = address;
// 		this.pincode = pincode;
// 		this.est = est;
// 		this.warehouse_status = warehouse_status;
// 		this.whocreated = whocreated;
// 		this.dateofcreation = dateofcreation;
// 		this.whoupdated = whoupdated;
// 		this.modifydate = modifydate;
// 		this.isocode = isocode;
// 	}

	@Override
	public String toString() {
		return "Warehouses [warehouseid=" + warehouseid + ", warehousename=" + warehousename + ", contactpersonname="
				+ contactpersonname + ", contactphone=" + contactphone + ", email=" + email + ", address=" + address
				+ ", pincode=" + pincode + ", est=" + est + ", warehouse_status=" + warehouse_status + ", whocreated="
				+ whocreated + ", dateofcreation=" + dateofcreation + ", whoupdated=" + whoupdated + ", modifydate="
				+ modifydate + ", isocode=" + isocode + "]";
	}

}
