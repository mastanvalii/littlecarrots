/**
 * 
 */
package com.lc.sk.auth.security;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public interface SecretSecurityKeys {

	final static String SECRETKEY = "Li77L3C@rr0t5keY";
	final static String SALT = "yeK5t0rr@C3L77iL01";
	
	final static String PBKDF2WithHmacSHA256 = "PBKDF2WithHmacSHA256";
	final static String AES = "AES";
	final static String CHIPER_PADDING = "AES/CBC/PKCS5Padding";
	
	final static String TEXT_FORMAT = "UTF-8";
	
// TODO Remove unused code found by UCDetector
// 	final static String ENCRYPTION_EXCEPTION = "Error while encrypting: ";
// TODO Remove unused code found by UCDetector
// 	final static String DECRYPTION_EXCEPTION = "Error while decrypting: ";
}
