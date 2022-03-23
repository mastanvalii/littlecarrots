/**
 * 
 */
package com.lc.sk.auth.bean;

import java.io.Serializable;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
public class MessageGenerator implements Serializable{
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4709904251705312573L;

	public static MessageBean newUser(String username, String password, String email, String role) {
	
		String msg = "";
		String subject = "Little Carrots Your Authorization Credentials generated";
		msg = "Welcome to Little Carrots Your Authorization Credentials generated  "+
		"Below are the Credential Details "+
				"Username:" +username+
				"Password:" + password+
				"Email: "+email+
				"and your role is: "+role+
				"**Account will actived soon";
		return new MessageBean(email, subject, msg);
	}
	
	public static MessageBean passwordUpdate(String username, String password, String email, String role) {
		
		String msg = "";
		String subject = "Little Carrots Your Authorization Credentials Updated";
		msg = "Welcome to Little Carrots Your Authorization Credentials updated  "+"\n"+
		"Below are the Credential Details "+"\n"+
				"Username:" +username+
				"New Password:" + password+
				"Email: "+email+
				"and your role is: "+role;
		return new MessageBean(email, subject, msg);
	}
	
	public static MessageBean accountStatusActive(String username,String role, String email, boolean status) {
		String msg = "";
		String txt = "";
		if(status) { txt = "Activated";} else { txt= "DeActivated";}
		String subject = "Little Carrots Your Authorization account "+ txt;
		msg = "Welcome to Little Carrots, Your Authorization account "+txt;	
		return new MessageBean(email, subject, msg);
	}
	
// TODO Remove unused code found by UCDetector
// 	public static MessageBean login(String username,String role,String email) {
// 		String msg="";
// 		String subject="Little Carrots you are succesfully logged into your acccount";
// 		msg="Welcome to Little Carrots you are succesfully logged into your acccount"+"\n"+
// 		"Username:" +username+
// 		"Role:" +role+
// 		"Email:" +email;
// 		return new MessageBean(email,subject,msg);
// 	}
	
	public static MessageBean newSeller(String sellercompanyname,String sellername,String phonenumber,String email,String address,String isocode) {
		String msg="";
		String subject="Little Carrots Your Seller Account Credentials";
		msg="Welcome to Little Carrots Your Seller Accout Credentials "+"\n"+
		"Below are your credentials "+"\n"+
				"Seller Companyname:" +sellercompanyname+
				"Seller Name:" +sellername+
				"Phone Number:" +phonenumber+
				"email:" +address+
				"isocode:" +isocode+
				"**Account will be activated soon";
	//	java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
//		emaildao.insertNewMail(new UserEmails(sellername, "Seller", email, subject, msg, date));
		return new MessageBean(email,subject,msg);
	}
	
	public static MessageBean updateSeller(String sellercompanyname,String sellername,String phonenumber,String email,String address,String isocode) {
		String msg="";
		String subject="Little Carrots Your Seller Account Updated Credentials";
		msg="Welcome to Little Carrots Your Seller Accout Updated Credentials "+"\n"+
		"Below are your credentials "+"\n"+
				"Seller Companyname:" +sellercompanyname+
				"Seller Name:" +sellername+
				"Phone Number:" +phonenumber+
				"email:" +address+
				"isocode:" +isocode+
				"**Account will be activated soon";
	//	java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	//	emaildao.insertNewMail(new UserEmails(sellername, "Seller", email, subject, msg, date));
		return new MessageBean(email,subject,msg);
	}
	
	public static MessageBean newSalesuser(String username,String password,String email,String fullname,String rolename,String sellerid) {
		String msg="";
		String subject="Little Carrots Your Sales User Account Credentials";
		msg="Welcome to Little Carrots Your Sales User Account Credentials \n"+
		"Below are your credentials \n"+
				"Username: "+username+
				" Password:  "+password+
				" Email: " +email+
				"FullName: "+ fullname+
				"Rolename: "+rolename+
				"Sellerid: "+sellerid+
				"**Your account will be activated soon";
	//	java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
//		emaildao.insertNewMail(new UserEmails(username, rolename, email, subject, msg, date));
		return new MessageBean(email,subject,msg);
		
	}
}
