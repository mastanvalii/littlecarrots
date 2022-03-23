/**
 * 
 */
package com.lc.sk.inventory.util;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventoryService
 * 2020
 */
@Component
public class HeaderComponent {

	private HttpHeaders headers= new HttpHeaders();
	
	public HttpHeaders getHeader()
	{
		headers.clear();
		headers.add("TOKENID", "123456789");
		return headers;
	}
	
}