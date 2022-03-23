/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class ResponseBean implements Serializable {
	private String message;
	private int responsecode;
	private long tiemstamp;

	public ResponseBean() {
		super();
	}

	public ResponseBean(String message, int responsecode, long tiemstamp) {
		super();
		this.message = message;
		this.responsecode = responsecode;
		this.tiemstamp = tiemstamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}

	public long getTiemstamp() {
		return tiemstamp;
	}

	public void setTiemstamp(long tiemstamp) {
		this.tiemstamp = tiemstamp;
	}

	@Override
	public String toString() {
		return "ResponseBean [message=" + message + ", responsecode=" + responsecode + ", tiemstamp=" + tiemstamp + "]";
	}

}
