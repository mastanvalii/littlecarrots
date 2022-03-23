/**
 * 
 */
package com.lc.sk.auth.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
@SuppressWarnings("serial")
@Entity
@Table(name="serviceurls")
public class ServiceURLs implements Serializable {
	
	@Id
	@Column(name="servicename")
	private String servicename;
	
	@Column(name="url")
	private String url;
	
	@Column(name="timeout")
	private long timeout;
	
	
	@Column(name="method")
	private String method;
	
	@Column(name="serviceip")
	private String serviceip;

	/**
	 * 
	 */
	public ServiceURLs() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @param servicename
// 	 * @param url
// 	 * @param timeout
// 	 * @param method
// 	 * @param serviceip
// 	 */
// 	public ServiceURLs(String servicename, String url, long timeout, String method, String serviceip) {
// 		super();
// 		this.servicename = servicename;
// 		this.url = url;
// 		this.timeout = timeout;
// 		this.method = method;
// 		this.serviceip = serviceip;
// 	}

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
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the serviceip
	 */
	public String getServiceip() {
		return serviceip;
	}

	/**
	 * @param serviceip the serviceip to set
	 */
	public void setServiceip(String serviceip) {
		this.serviceip = serviceip;
	}

	@Override
	public String toString() {
		return "ServiceURLs [servicename=" + servicename + ", url=" + url + ", timeout=" + timeout + ", method="
				+ method + ", serviceip=" + serviceip + "]";
	}
	
	

}
