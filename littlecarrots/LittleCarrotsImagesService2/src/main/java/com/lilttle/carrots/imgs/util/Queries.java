package com.lilttle.carrots.imgs.util;

public interface Queries {

	final static String GET_QUERY1="select imgid,productid, image from imagelocation where productid=:productid";
	final static String GET_QUERY2="select imgid,productid, image from imagelocation where productid=:productid LIMIT 1";
	final static String GET_QUERY3="select distinct productid from imagelocation";
	final static String GET_QUERY4="select imgid,productid, image from imagelocation";
}
