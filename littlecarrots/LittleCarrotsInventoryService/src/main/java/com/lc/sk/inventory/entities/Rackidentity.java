package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="rackidentity")
public class Rackidentity implements Serializable {

	
	/*
	 * 
	 * create table rackidentity(
			uid bigint auto_increment,
			rackid varchar(100),
			skuid bigint,
			constraint rackpksku_pk primary key (uid),
			constraint rackfksku_fk foreign key(skuid) references products(productid),
			constraint rackfksku1_fk foreign key(rackid) references racks(rackid),
		);
	 */
	
	@Id
	@Column(name="uid")
	private long uid;
	
	@Column(name="rackid")
	private String rackid;
	
	@Column(name="skuid")
	private String skuid;

	public Rackidentity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rackidentity( String rackid, String skuid) {
		super();

		this.rackid = rackid;
		this.skuid = skuid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getRackid() {
		return rackid;
	}

	public void setRackid(String rackid) {
		this.rackid = rackid;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	@Override
	public String toString() {
		return "Rackidentity [uid=" + uid + ", rackid=" + rackid + ", skuid=" + skuid + "]";
	}
	
	
	
}
