/**
 * 
 */
package com.lc.sk.inventory.security.util;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
@Component
public class HeaderComponent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8458472002600603918L;
	private HttpHeaders headers = new HttpHeaders();

	public HttpHeaders getHeader() {
		headers.clear();
		headers.add("TOKENID", "123456789");
		return headers;
	}
}
