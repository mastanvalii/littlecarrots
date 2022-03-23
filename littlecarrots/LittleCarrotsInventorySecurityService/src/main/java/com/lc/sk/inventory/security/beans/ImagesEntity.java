/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class ImagesEntity implements Serializable {
	private long imgid;
	private long productid;
	private List<byte[]> file;
	/**
	 * 
	 */
	public ImagesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImagesEntity(long imgid, long productid, List<byte[]> file) {
		super();
		this.imgid = imgid;
		this.productid = productid;
		this.file = file;
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
	public List<byte[]> getFile() {
		return file;
	}
	public void setFile(List<byte[]> file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "ImagesEntity [imgid=" + imgid + ", productid=" + productid + ", file=" + file + "]";
	}

	


	
}
