package com.lc.sk.inventory.security.beans;

import java.io.Serializable;


public class Sellerproductidentity implements Serializable {
	private long uid;
	
	private String flipkartid;
	
	private String amazonid;
	
	private String meeshoid;
	
	private String paytmid;
	
	private String s1;
	
	private String s2;
	
	private String s3;
	
	private long skuid;

	public Sellerproductidentity() {
		super();
		// TODO Auto-generated constructor stub
	}



// TODO Remove unused code found by UCDetector
// 	public Sellerproductidentity(long uid, String flipkartid, String amazonid, String meeshoid, String paytmid,
// 			String s1, String s2, String s3, long skuid) {
// 		super();
// 		this.uid = uid;
// 		this.flipkartid = flipkartid;
// 		this.amazonid = amazonid;
// 		this.meeshoid = meeshoid;
// 		this.paytmid = paytmid;
// 		this.s1 = s1;
// 		this.s2 = s2;
// 		this.s3 = s3;
// 		this.skuid = skuid;
// 	}



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
