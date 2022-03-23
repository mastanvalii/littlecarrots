/**
 * 
 */
package com.lc.sk.inventory.security.beans;

import java.io.Serializable;

import org.springframework.http.HttpMethod;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
public class UrlBean implements Serializable {

	private String url;

	private HttpMethod method;

	private long timeout;

	private String servicename;

	/**
	 * @param url
	 * @param method
	 * @param timeout
	 * @param servicename
	 */
	public UrlBean(String ipaddress, String url, String method, long timeout, String servicename) {
		super();
		this.url = ipaddress + url;
		this.method = HttpMethod.valueOf(method);
		this.timeout = timeout;
		this.servicename = servicename;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the method
	 */
	public HttpMethod getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	/**
	 * @return the timeout
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the servicename
	 */
	public String getServicename() {
		return servicename;
	}

	/**
	 * @param servicename the servicename to set
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	@Override
	public String toString() {
		return "REST TEMPLATE URL: [url=" + url + ", method=" + method + ", timeout=" + timeout + ", servicename=" + servicename
				+ "]";
	}

}
