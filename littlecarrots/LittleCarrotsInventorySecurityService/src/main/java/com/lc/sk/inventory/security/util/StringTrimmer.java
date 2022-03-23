/**
 * 
 */
package com.lc.sk.inventory.security.util;

import java.util.StringTokenizer;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class StringTrimmer {

	public static String trim(String msg)
	{
		StringTokenizer st = new StringTokenizer(msg, ConstantValues.REGEX);
		String temp ="";
		while(st.hasMoreTokens())
		{
			temp=st.nextToken();
		}
		return temp;
	}
}
