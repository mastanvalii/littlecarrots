/**
 * 
 */
package com.lc.sk.auth.security;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.lc.sk.auth.exceptions.subexceptions.EncDecException;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
public class SecretSecurity {

	public static String enc(String password) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance(SecretSecurityKeys.PBKDF2WithHmacSHA256);
			KeySpec spec = new PBEKeySpec(SecretSecurityKeys.SECRETKEY.toCharArray(),
					SecretSecurityKeys.SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), SecretSecurityKeys.AES);

			Cipher cipher = Cipher.getInstance(SecretSecurityKeys.CHIPER_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder()
					.encodeToString(cipher.doFinal(password.getBytes(SecretSecurityKeys.TEXT_FORMAT)));
		} catch (Exception e) {
			throw new EncDecException("Error while encrypting: " + e.toString());
		}
	}

	public static String des(String encpassword) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance(SecretSecurityKeys.PBKDF2WithHmacSHA256);
			KeySpec spec = new PBEKeySpec(SecretSecurityKeys.SECRETKEY.toCharArray(),
					SecretSecurityKeys.SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), SecretSecurityKeys.AES);

			Cipher cipher = Cipher.getInstance(SecretSecurityKeys.CHIPER_PADDING);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encpassword)));
		} catch (Exception e) {
			throw new EncDecException("Error while decrypting: " + e.toString());
		}
	}
}
