package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sourcesales")
public class Sourcesales implements Serializable{

//	create table sourcesales(
//	 sid bigint auto_increment,
//	 source varchar(100),
//	 constraint sourcesales_pk primary key (sid)
//	);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sid")
	private long sid;
	
	@Column(name = "source")
	private String source;

	public Sourcesales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sourcesales( String source) {
		super();
		
		this.source = source;
	}

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
		return "Sourcesales [sid=" + sid + ", source=" + source + "]";
	}
	
	
	
}
