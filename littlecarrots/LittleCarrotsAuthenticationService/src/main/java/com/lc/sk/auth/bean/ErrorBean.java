/**
 * 
 */
package com.lc.sk.auth.bean;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public class ErrorBean  extends UrlBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5054139578008107053L;

	public ErrorBean(String ipaddress, String url, String get, long timeout, String servicename) {
		super(ipaddress, url, get, timeout, servicename);
	}
}
