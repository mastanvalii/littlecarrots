/**
 * 
 */
package com.lc.sk.inventory.security.util;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public class JsonToBeanConverter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Object convert(String data, Class<?> classname)
	{
		Object obj = null;
		ObjectMapper jsondata = new ObjectMapper();
		try {
			obj = jsondata.readValue(data, classname);
		} catch (JsonMappingException ee) {
			ee.printStackTrace();
		} catch (JsonProcessingException eee) {
			eee.printStackTrace();
		}
		return obj;
	}
}
