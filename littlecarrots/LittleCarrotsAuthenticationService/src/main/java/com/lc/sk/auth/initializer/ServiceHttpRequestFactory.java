/**
 * 
 */
package com.lc.sk.auth.initializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.FileUtils;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
@Component
public class ServiceHttpRequestFactory {
	
	public ClientHttpRequestFactory getClientHttpRequestFactory(long timeout) {
		
		return disablessl(timeout);
		/*
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout((int)timeout);
	    return clientHttpRequestFactory;
	    */
	}
	

	/*
	 * public ClientHttpRequestFactory getClientHttpRequestFactory(long timeout) {
	 * return validateSsl(timeout); }
	 */
	
	private ClientHttpRequestFactory disablessl(long timeout) {
		   TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		};
				   //(X509Certificate[] chain, String authType) -> true;

		    SSLContext sslContext = null;
			try {
				sslContext = org.apache.http.ssl.SSLContexts.custom()
				                .loadTrustMaterial(null, acceptingTrustStrategy)
				                .build();
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    @SuppressWarnings("deprecation")
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());

		    CloseableHttpClient httpClient = HttpClients.custom()
		                    .setSSLSocketFactory(csf)
		                    .build();
		
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setHttpClient(httpClient);
	    clientHttpRequestFactory.setConnectTimeout((int)timeout);
	    return clientHttpRequestFactory;
	}
	
	/*
	
	public ClientHttpRequestFactory validateSsl(long timeout) {
		
		final String password = "Sairam1!";
		   TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		};
				   //(X509Certificate[] chain, String authType) -> true;

		    SSLContext sslContext = null;
			try {
				ClassPathResource classPathResource = new ClassPathResource("littlecarrots_email.p12");

				InputStream inputStream = classPathResource.getInputStream();
				File somethingFile = File.createTempFile("test1", ".txt");
				try {
				    FileUtils.copyInputStreamToFile(inputStream, somethingFile);
				} finally {
				    IOUtils.closeQuietly(inputStream);
				}
							
					sslContext = org.apache.http.ssl.SSLContexts.custom()
					                .loadTrustMaterial(somethingFile, password.toCharArray())
					                .build();
				
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (CertificateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());

		    CloseableHttpClient httpClient = HttpClients.custom()
		                    .setSSLSocketFactory(csf)
		                    .build();
		
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setHttpClient(httpClient);
	    clientHttpRequestFactory.setConnectTimeout((int)timeout);
	    return clientHttpRequestFactory;
	}
	
	*/
}
