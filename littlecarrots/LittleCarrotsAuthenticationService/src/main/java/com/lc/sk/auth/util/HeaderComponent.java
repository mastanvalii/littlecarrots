/**
 * 
 */
package com.lc.sk.auth.util;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
@Component
public class HeaderComponent implements Serializable {

	private HttpHeaders headers = new HttpHeaders();

	public HttpHeaders getHeader() {
		headers.clear();
		headers.add("TOKENID", "123456789");
		return headers;
	}


		

}
