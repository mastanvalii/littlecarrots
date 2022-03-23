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
public class NewBatch implements Serializable {
	private String warehouseid;
	private String dateofpurchase;
	private  String purchasedby;
	private  String invamount;
	private String whoinserted;
	private boolean status;
	private String isocode;
	private String qty;
	private String sellerid;
	/**
	 * 
	 */
	public NewBatch() {
		super();
		// TODO Auto-generated constructor stub
	}
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param warehouseid
// 	 * @param dateofpurchase
// 	 * @param purchasedby
// 	 * @param invamount
// 	 * @param whoinserted
// 	 * @param status
// 	 * @param isocode
// 	 * @param qty
// 	 * @param sellerid
// 	 */
// 	public NewBatch(String warehouseid, String dateofpurchase, String purchasedby, String invamount, String whoinserted,
// 			boolean status, String isocode, String qty, String sellerid) {
// 		super();
// 		this.warehouseid = warehouseid;
// 		this.dateofpurchase = dateofpurchase;
// 		this.purchasedby = purchasedby;
// 		this.invamount = invamount;
// 		this.whoinserted = whoinserted;
// 		this.status = status;
// 		this.isocode = isocode;
// 		this.qty = qty;
// 		this.sellerid = sellerid;
// 	}
	/**
	 * @return the warehouseid
	 */
	public String getWarehouseid() {
		return warehouseid;
	}
	/**
	 * @param warehouseid the warehouseid to set
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	/**
	 * @return the dateofpurchase
	 */
	public String getDateofpurchase() {
		return dateofpurchase;
	}
	/**
	 * @param dateofpurchase the dateofpurchase to set
	 */
	public void setDateofpurchase(String dateofpurchase) {
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
	public String getInvamount() {
		return invamount;
	}
	/**
	 * @param invamount the invamount to set
	 */
	public void setInvamount(String invamount) {
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
	public String getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}
	/**
	 * @return the sellerid
	 */
	public String getSellerid() {
		return sellerid;
	}
	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	@Override
	public String toString() {
		return "NewBatch [warehouseid, dateofpurchase, purchasedby, invamount, whoinserted, status, isocode, qty, sellerid ]";
	}
	
	
	
}
