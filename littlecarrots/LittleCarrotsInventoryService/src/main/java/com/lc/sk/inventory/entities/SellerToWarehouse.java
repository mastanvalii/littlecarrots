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
@Table(name = "sellertowarehouse")
public class SellerToWarehouse implements Serializable {

	@Id
	@Column(name = "serialid")
	private long serialid;

	@Column(name = "warehouseid")
	private long warehouseid;

	@Column(name = "sellerid")
	private long sellerid;

	public SellerToWarehouse() {
	}

	/**
	 * @param warehouseid
	 * @param sellerid
	 */
	public SellerToWarehouse(long warehouseid, long sellerid) {
		super();
		this.warehouseid = warehouseid;
		this.sellerid = sellerid;
	}

	/**
	 * @return the serialid
	 */
	public long getSerialid() {
		return serialid;
	}

	/**
	 * @param serialid the serialid to set
	 */
	public void setSerialid(long serialid) {
		this.serialid = serialid;
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
		return "SellerToWarehouse [serialid=" + serialid + ", warehouseid=" + warehouseid + ", sellerid=" + sellerid
				+ "]";
	}

}
