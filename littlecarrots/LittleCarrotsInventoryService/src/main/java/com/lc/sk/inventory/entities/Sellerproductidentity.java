package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sellerproductidentity")
public class Sellerproductidentity implements Serializable {

	/*
			 * create table sellerproductidentity(
			uid bigint auto_increment,
			flipkartid varchar(100),
			amazonid varchar(100),
			meeshoid varchar(100),
			paytmid varchar(100),
			s1 varchar(100),
			s2 varchar(100),
			s3 varchar(100),
			skuid bigint,
			constraint sellpksku_pk primary key (uid),
			constraint sellfksku_fk foreign key(skuid) references products(productid)
		);
	 */
	
	@Id
	@Column(name="uid")
	private long uid;
	
	@Column(name="flipkartid")
	private String flipkartid;
	
	@Column(name="amazonid")
	private String amazonid;
	
	@Column(name="meeshoid")
	private String meeshoid;
	
	@Column(name="paytmid")
	private String paytmid;
	
	@Column(name="s1")
	private String s1;
	
	@Column(name="s2")
	private String s2;
	
	@Column(name="s3")
	private String s3;
	
	@Column(name="skuid")
	private long skuid;

	public Sellerproductidentity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sellerproductidentity( String flipkartid, String amazonid, String meeshoid, String paytmid,
			String s1, String s2, String s3, long skuid) {
		super();
	//	this.uid = uid;
		this.flipkartid = flipkartid;
		this.amazonid = amazonid;
		this.meeshoid = meeshoid;
		this.paytmid = paytmid;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.skuid = skuid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getFlipkartid() {
		return flipkartid;
	}

	public void setFlipkartid(String flipkartid) {
		this.flipkartid = flipkartid;
	}

	public String getAmazonid() {
		return amazonid;
	}

	public void setAmazonid(String amazonid) {
		this.amazonid = amazonid;
	}

	public String getMeeshoid() {
		return meeshoid;
	}

	public void setMeeshoid(String meeshoid) {
		this.meeshoid = meeshoid;
	}

	public String getPaytmid() {
		return paytmid;
	}

	public void setPaytmid(String paytmid) {
		this.paytmid = paytmid;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getS3() {
		return s3;
	}

	public void setS3(String s3) {
		this.s3 = s3;
	}

	public long getSkuid() {
		return skuid;
	}

	public void setSkuid(long skuid) {
		this.skuid = skuid;
	}

	@Override
	public String toString() {
		return "SellerProductIdentity [uid=" + uid + ", flipkartid=" + flipkartid + ", amazonid=" + amazonid
				+ ", meeshoid=" + meeshoid + ", paytmid=" + paytmid + ", s1=" + s1 + ", s2=" + s2 + ", s3=" + s3
				+ ", skuid=" + skuid + "]";
	}
	
	
	
}
