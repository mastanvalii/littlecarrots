/**
 * 
 */
package com.lc.sk.auth.util;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
public class EmailValidation implements Serializable {

	private static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

	private static Pattern pat = Pattern.compile(emailRegex);

	public static boolean isValid(String email) {
		boolean flag = false;
		if (email == null) {
			flag = false;
		}
		flag = pat.matcher(email).matches();
		return flag;
	}
}
