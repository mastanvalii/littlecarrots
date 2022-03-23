package com.lilttle.carrots.imgs.util;

public interface URLMapping {

	final static String ROOT = "/img2";
	final static String VERSION = "/v1";
	
	final static String UPLOAD ="/upload/{productid}";
	
	final static String GET = "/get/{productid}/{name}";
	
	final static String GETLIST = "/getlist/{productid}";
	final static String GETLIST1 = "/getlist1/{productid}";
	
	final static String GETPRODUCTIDS = "/getallimg";
	
	final static String IMG10="/IMG10/{page}/{count}";
	final static String IMG11="/IMG11/{page}/{count}";
	
	final static String IMG12 = "/IMG12";
	
	final static String IMG13= "/IMG13";
}
