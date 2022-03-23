package com.lc.sk.inventory.security.factory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 
 */

@SuppressWarnings("deprecation")
/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
@Component
public class ServiceHttpRequestFactory {

	public ClientHttpRequestFactory getClientHttpRequestFactory(long timeout) {
		return disablessl(timeout);
		/*
		 * HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
		 * HttpComponentsClientHttpRequestFactory();
		 * clientHttpRequestFactory.setConnectTimeout((int)timeout); return
		 * clientHttpRequestFactory;
		 */
	}

	public ClientHttpRequestFactory disablessl(long timeout) {
		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		};
		SSLContext sslContext = null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(httpClient);
		clientHttpRequestFactory.setConnectTimeout((int) timeout);
		return clientHttpRequestFactory;
	}

	public ClientHttpRequestFactory validateInventorySsl(long timeout) {

		return getClientHttpRequestFactory(timeout);
	}

	public ClientHttpRequestFactory validateAuthenticationSsl(long timeout) {

		return getClientHttpRequestFactory(timeout);
	}

	public ClientHttpRequestFactory validateEmailSsl(long timeout) {

		return getClientHttpRequestFactory(timeout);
	}

	public ClientHttpRequestFactory validateImageSsl(long timeout) {

		return getClientHttpRequestFactory(timeout);
	}

	public ClientHttpRequestFactory validateOrdersSsl(long timeout) {

		return getClientHttpRequestFactory(timeout);
	}

}
