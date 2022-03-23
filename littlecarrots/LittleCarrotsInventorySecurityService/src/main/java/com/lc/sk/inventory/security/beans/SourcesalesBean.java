package com.lc.sk.inventory.security.beans;

import java.io.Serializable;


public class SourcesalesBean implements Serializable {
	
	private long sid;
	
	private String source;

	public SourcesalesBean() {
		super();
		// TODO Auto-generated constructor stub
	}

// TODO Remove unused code found by UCDetector
// 	public SourcesalesBean(long sid, String source) {
// 		super();
// 		this.sid = sid;
// 		this.source = source;
// 	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SourcesalesBean [sid=" + sid + ", source=" + source + "]";
	}
	
	

}
