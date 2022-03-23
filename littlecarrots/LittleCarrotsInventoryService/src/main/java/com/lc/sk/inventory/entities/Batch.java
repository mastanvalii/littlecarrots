/**
 * 
 */
package com.lc.sk.inventory.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */
@Entity
@Table(name = "Batch")
public class Batch implements Serializable {

	/*
	 * 
	 * create table Batch( BatchID bigint auto_increment, WarehouseID bigint,
	 * DateofPurchase dateTime, purchasedby varchar(50), InvAmount double,
	 * WhoInserted varchar(50), DateInsertion DATEtime, Status int(1), ISOCODE
	 * varchar(10), QTY bigint, Sellerid bigint, constraint batch_pk primary key
	 * (batchid), constraint batch_fk1 foreign key (warehouseid) references
	 * warehouses(warehouseid), constraint batch_fk2 foreign key (isocode)
	 * references countries(isocode));
	 */
	
	@Id
	@Column(name="batchid")
	private long batchid;
	
	@Column(name="warehouseid")
	private long warehouseid;
	
	@Column(name="dateofpurchase")
	private Timestamp dateofpurchase;
	
	@Column(name="purchasedby")
	private String purchasedby;
	
	@Column(name="invamount")
	private double invamount;
	
	@Column(name="whoinserted")
	private String whoinserted;
	
	@Column(name="dateinsertion")
	private Timestamp dateinsertion;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="isocode")
	private String isocode;
	
	@Column(name="qty")
	private long qty;
	
	@Column(name="sellerid")
	private long sellerid;

	/**
	 * 
	 */
	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param warehouseid
	 * @param dateofpurchase
	 * @param purchasedby
	 * @param invamount
	 * @param whoinserted
	 * @param dateinsertion
	 * @param status
	 * @param isocode
	 * @param qty
	 * @param sellerid
	 */
	public Batch(long warehouseid, Timestamp dateofpurchase, String purchasedby, double invamount, String whoinserted,
			Timestamp dateinsertion, boolean status, String isocode, long qty, long sellerid) {
		super();
		this.warehouseid = warehouseid;
		this.dateofpurchase = dateofpurchase;
		this.purchasedby = purchasedby;
		this.invamount = invamount;
		this.whoinserted = whoinserted;
		this.dateinsertion = dateinsertion;
		this.status = status;
		this.isocode = isocode;
		this.qty = qty;
		this.sellerid = sellerid;
	}

	/**
	 * @return the batchid
	 */
	public long getBatchid() {
		return batchid;
	}

	/**
	 * @param batchid the batchid to set
	 */
	public void setBatchid(long batchid) {
		this.batchid = batchid;
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
	 * @return the dateofpurchase
	 */
	public Timestamp getDateofpurchase() {
		return dateofpurchase;
	}

	/**
	 * @param dateofpurchase the dateofpurchase to set
	 */
	public void setDateofpurchase(Timestamp dateofpurchase) {
		this.dateofpurchase = dateofpurchase;
	}

	/**
	 * @return the purchasedby
	 */
	public String getPurchasedby() {
		return purchasedby;
	}

	/**
	 * @param purchasedby the purchasedby to set
	 */
	public void setPurchasedby(String purchasedby) {
		this.purchasedby = purchasedby;
	}

	/**
	 * @return the invamount
	 */
	public double getInvamount() {
		return invamount;
	}

	/**
	 * @param invamount the invamount to set
	 */
	public void setInvamount(double invamount) {
		this.invamount = invamount;
	}

	/**
	 * @return the whoinserted
	 */
	public String getWhoinserted() {
		return whoinserted;
	}

	/**
	 * @param whoinserted the whoinserted to set
	 */
	public void setWhoinserted(String whoinserted) {
		this.whoinserted = whoinserted;
	}

	/**
	 * @return the dateinsertion
	 */
	public Timestamp getDateinsertion() {
		return dateinsertion;
	}

	/**
	 * @param dateinsertion the dateinsertion to set
	 */
	public void setDateinsertion(Timestamp dateinsertion) {
		this.dateinsertion = dateinsertion;
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
	 * @return the qty
	 */
	public long getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(long qty) {
		this.qty = qty;
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

	@Override
	public String toString() {
		return "Batch [batchid=" + batchid + ", warehouseid=" + warehouseid + ", dateofpurchase=" + dateofpurchase
				+ ", purchasedby=" + purchasedby + ", invamount=" + invamount + ", whoinserted=" + whoinserted
				+ ", dateinsertion=" + dateinsertion + ", status=" + status + ", isocode=" + isocode + ", qty=" + qty
				+ ", sellerid=" + sellerid + "]";
	}
	
	
	
	
}
