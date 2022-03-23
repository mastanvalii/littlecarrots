/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class UrlsList implements Serializable {

	private String URL;
	private String HttpMethodName;
	private String parameterList;
	/**
	 * 
	 */
	public UrlsList() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param uRL
	 * @param httpMethodName
	 * @param parameterList
	 */
	public UrlsList(String uRL, String httpMethodName, String parameterList) {
		super();
		URL = uRL;
		HttpMethodName = httpMethodName;
		this.parameterList = parameterList;
	}
	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}
	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}
	/**
	 * @return the httpMethodName
	 */
	public String getHttpMethodName() {
		return HttpMethodName;
	}
	/**
	 * @param httpMethodName the httpMethodName to set
	 */
	public void setHttpMethodName(String httpMethodName) {
		HttpMethodName = httpMethodName;
	}
	/**
	 * @return the parameterList
	 */
	public String getParameterList() {
		return parameterList;
	}
	/**
	 * @param parameterList the parameterList to set
	 */
	public void setParameterList(String parameterList) {
		this.parameterList = parameterList;
	}
	@Override
	public String toString() {
		return "UrlsList [URL=" + URL + ", HttpMethodName=" + HttpMethodName + ", parameterList=" + parameterList + "]";
	}
	
	
}
