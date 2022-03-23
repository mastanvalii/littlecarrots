package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

 

@Entity
@Table(name="lcprops")
public class Lcprops implements Serializable {

	@Id
	@Column(name="propid")
	private long propid;
	
	@Column(name="prop")
	private String prop;
	
	@Column(name="status")
	private boolean status;

	/**
	 * 
	 */
	public Lcprops() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param propid
// 	 * @param prop
// 	 * @param status
// 	 */
// 	public Lcprops(long propid, String prop, boolean status) {
// 		super();
// 		this.propid = propid;
// 		this.prop = prop;
// 		this.status = status;
// 	}

	/**
	 * @return the propid
	 */
	public long getPropid() {
		return propid;
	}

	/**
	 * @param propid the propid to set
	 */
	public void setPropid(long propid) {
		this.propid = propid;
	}

	/**
	 * @return the prop
	 */
	public String getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(String prop) {
		this.prop = prop;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
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
		return "Lcprops [propid=" + propid + ", prop=" + prop + ", status=" + status + "]";
	}

	
	}
	
	


