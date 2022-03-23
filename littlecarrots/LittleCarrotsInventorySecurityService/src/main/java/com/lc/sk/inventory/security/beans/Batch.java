package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Batch implements Serializable {

	private long batchid;
	private long warehouseid;
	private Timestamp dateofpurchase;
	private String purchasedby;
	private double invamount;
	private String whoinserted;
	private Timestamp dateinsertion;
	private boolean status;
	private String isocode;
	private long qty;
	private long sellerid;

	public Batch() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public Batch(long batchid, long warehouseid, Timestamp dateofpurchase, String purchasedby, double invamount,
// 			String whoinserted, Timestamp dateinsertion, boolean status, String isocode, long qty, long sellerid) {
// 		super();
// 		this.batchid = batchid;
// 		this.warehouseid = warehouseid;
// 		this.dateofpurchase = dateofpurchase;
// 		this.purchasedby = purchasedby;
// 		this.invamount = invamount;
// 		this.whoinserted = whoinserted;
// 		this.dateinsertion = dateinsertion;
// 		this.status = status;
// 		this.isocode = isocode;
// 		this.qty = qty;
// 		this.sellerid = sellerid;
// 	}

	@Override
	public String toString() {
		return "Batch [batchid=" + batchid + ", warehouseid=" + warehouseid + ", dateofpurchase=" + dateofpurchase
				+ ", purchasedby=" + purchasedby + ", invamount=" + invamount + ", whoinserted=" + whoinserted
				+ ", dateinsertion=" + dateinsertion + ", status=" + status + ", isocode=" + isocode + ", qty=" + qty
				+ ", sellerid=" + sellerid + "]";
	}

	public long getBatchid() {
		return batchid;
	}

	public void setBatchid(long batchid) {
		this.batchid = batchid;
	}

	public long getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(long warehouseid) {
		this.warehouseid = warehouseid;
	}

	public Timestamp getDateofpurchase() {
		return dateofpurchase;
	}

	public void setDateofpurchase(Timestamp dateofpurchase) {
		this.dateofpurchase = dateofpurchase;
	}

	public String getPurchasedby() {
		return purchasedby;
	}

	public void setPurchasedby(String purchasedby) {
		this.purchasedby = purchasedby;
	}

	public double getInvamount() {
		return invamount;
	}

	public void setInvamount(double invamount) {
		this.invamount = invamount;
	}

	public String getWhoinserted() {
		return whoinserted;
	}

	public void setWhoinserted(String whoinserted) {
		this.whoinserted = whoinserted;
	}

	public Timestamp getDateinsertion() {
		return dateinsertion;
	}

	public void setDateinsertion(Timestamp dateinsertion) {
		this.dateinsertion = dateinsertion;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getIsocode() {
		return isocode;
	}

	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getSellerid() {
		return sellerid;
	}

	public void setSellerid(long sellerid) {
		this.sellerid = sellerid;
	}

}
