package com.lilttle.carrots.imgs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="imagelocation")
public class Imagelocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7661471741023564467L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="imgid")
	private long imgid;
	
	@Column(name="productid")
	private long productid;
	
	@Column(name="image")
	private String image;

	public Imagelocation() {
		super();
	}

	public Imagelocation(long productid, String image) {
		super();
		this.productid = productid;
		this.image = image;
	}

	public long getImgid() {
		return imgid;
	}

	public void setImgid(long imgid) {
		this.imgid = imgid;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Imagelocation [imgid=" + imgid + ", productid=" + productid + ", image=" + image + "]";
	}
	
	
	
}
