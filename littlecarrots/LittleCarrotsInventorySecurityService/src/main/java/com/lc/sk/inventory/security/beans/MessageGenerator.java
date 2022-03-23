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
public class MessageGenerator implements Serializable{
	
	public static MessageBean newUser(String username, String password, String email, String role) {
	
		String msg = "";
		String subject = "Little Carrots Your Authorization Credentials generated";
		msg = "Welcome to Little Carrots Your Authorization Credentials generated <br> "+
		"Below are the Credential Details "+
				"Username:" +username+
				"Password:" + password+
				"Email: "+email+
				"and your role is: "+role+
				"**Account will active soon";
		
		return new MessageBean(email, subject, msg);
	}
}
