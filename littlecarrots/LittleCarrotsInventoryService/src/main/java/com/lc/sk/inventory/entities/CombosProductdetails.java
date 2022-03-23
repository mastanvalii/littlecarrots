package com.lc.sk.inventory.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="combos_productdetails")
public class CombosProductdetails implements Serializable{
	
//	create table combos_productdetails(
//			combo_subid bigint auto_increment,
//			comboid bigint,
//			productid bigint,
//			finalprice double,
//			constraint cmbsub_pk primary key (combo_subid) ,
//			constraint  cmbsub_fk1 foreign key (comboid) references combos(comboid),
//			constraint  cmbsub_fk2 foreign key (productid) references products (productid)
//			);

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="combo_subid")
	private long combo_subid;
	
	@Column(name="comboid")
	private long comboid;
	
	@Column(name="productid")
	private long productid;
	
	@Column(name="finalprice")
	private float finalPrice;

	public CombosProductdetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CombosProductdetails(long comboid, long productid, float finalPrice) {
		super();
		this.comboid = comboid;
		this.productid = productid;
		this.finalPrice = finalPrice;
	}

	public long getCombo_subid() {
		return combo_subid;
	}

	public void setCombo_subid(long combo_subid) {
		this.combo_subid = combo_subid;
	}

	public long getComboid() {
		return comboid;
	}

	public void setComboid(long comboid) {
		this.comboid = comboid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "CombosProductdetails [combo_subid=" + combo_subid + ", comboid=" + comboid + ", productid=" + productid
				+ ", finalPrice=" + finalPrice + "]";
	}
	
	
	
	
	
}
