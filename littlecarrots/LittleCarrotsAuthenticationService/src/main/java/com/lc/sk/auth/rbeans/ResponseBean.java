/**
 * 
 */
package com.lc.sk.auth.rbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
public class ResponseBean implements Serializable {

	private String message;
	private int responsecode;
	private long tiemstamp;

	/**
	 * 
	 */
	public ResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param responsecode
	 * @param tiemstamp
	 */
	public ResponseBean(String message, int responsecode, long tiemstamp) {
		super();
		this.message = message;
		this.responsecode = responsecode;
		this.tiemstamp = tiemstamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the responsecode
	 */
	public int getResponsecode() {
		return responsecode;
	}

	/**
	 * @param responsecode the responsecode to set
	 */
	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}

	/**
	 * @return the tiemstamp
	 */
	public long getTiemstamp() {
		return tiemstamp;
	}

	/**
	 * @param tiemstamp the tiemstamp to set
	 */
	public void setTiemstamp(long tiemstamp) {
		this.tiemstamp = tiemstamp;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(tiemstamp);
		return "ResponseBean [message=" + message + ", responsecode=" + responsecode + ", tiemstamp="
				+ sdf.format(resultdate) + "]";
	}

}
