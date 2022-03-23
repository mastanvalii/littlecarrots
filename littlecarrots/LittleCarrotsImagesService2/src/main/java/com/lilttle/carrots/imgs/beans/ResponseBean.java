package com.lilttle.carrots.imgs.beans;

import java.io.Serializable;

public class ResponseBean implements Serializable {
	private String message;
	private int responsecode;
	private long timestamp;
	public ResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseBean(String message, int responsecode, long timestamp) {
		super();
		this.message = message;
		this.responsecode = responsecode;
		this.timestamp = timestamp;
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
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ResponseBean [message=" + message + ", responsecode=" + responsecode + ", timestamp=" + timestamp + "]";
	}
	
	
}
