package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productkeywords")

public class ProductKeywords implements Serializable{
	
	/*create table productkeywords(
      keywordid bigint auto_increment,
      productid bigint,
      keywords varchar(10000),
      constraint productkeywords_pk primary key (keywordid),
      constraint productkeywords_fk foreign key (productid) references products(productid));*/
	
	
	@Id
	@Column(name="keywordid")
	private long keywordid;
	
	@Column(name="productid")
	private long productid;
	
	@Column(name="keywords")
	private String keywords;

	public ProductKeywords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductKeywords(long productid, String keywords) {
		super();
		this.productid = productid;
		this.keywords = keywords;
	}

	public long getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(long keywordid) {
		this.keywordid = keywordid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "ProductKeywords [keywordid=" + keywordid + ", productid=" + productid + ", keywords=" + keywords + "]";
	}
	
	
	
	
	
	
}
