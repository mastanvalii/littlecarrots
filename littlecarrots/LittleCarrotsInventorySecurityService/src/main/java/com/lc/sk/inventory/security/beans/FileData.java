package com.lc.sk.inventory.security.beans;

import java.io.Serializable;
import java.util.Arrays;

public class FileData implements Serializable {
	private String productid;
	private String[] images;
	private String message;
	public FileData() {
		super();
	}
// TODO Remove unused code found by UCDetector
// 	public FileData(String productid, String[] images, String message) {
// 		super();
// 		this.productid = productid;
// 		this.images = images;
// 		this.message = message;
// 	}
	
// TODO Remove unused code found by UCDetector
// 	public FileData(String productid,  String message) {
// 		super();
// 		this.productid = productid;
// 	//	this.images = images;
// 		this.message = message;
// 	}
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "FileData [productid=" + productid + ", images=" + Arrays.toString(images) + ", message=" + message
				+ "]";
	}

	
}
