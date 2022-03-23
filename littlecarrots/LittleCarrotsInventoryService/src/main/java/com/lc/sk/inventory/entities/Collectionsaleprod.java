package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collectionsaleprod")
public class Collectionsaleprod implements Serializable{
	
	/*CREATE TABLE collectionsaleprod(
			csid bigint auto_increment,
			id bigint,
			productid bigint,
			constraint collectionsaleprod1_pk primary key (csid),
			constraint collectionsaleprod_fk_collectionsale_pk foreign key (id) references collectionsale(id),
			constraint collectionsaleprod_fk_product_pk foreign key (productid) references products(productid)
			);*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="csid")
	private long csid;
	
	@Column(name="id")
	private long id;
	
	@Column(name="productid")
	private long productid;

	public Collectionsaleprod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collectionsaleprod(long id, long productid) {
		super();
		this.id = id;
		this.productid = productid;
	}

	public long getCsid() {
		return csid;
	}

	public void setCsid(long csid) {
		this.csid = csid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "Collectionsaleprod [csid=" + csid + ", id=" + id + ", productid=" + productid + "]";
	}
	
	
}
