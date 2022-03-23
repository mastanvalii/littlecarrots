/**
 * 
 */
package com.lc.sk.inventory.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.MessageBean;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.MailServiceException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsInventorySecurityService
 * 2020
 */
@Component
public class EmailRestService {
	
	@Autowired
	private HeaderKeyManagement headerKeyManagement;

	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;

	private UrlBean urls;
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private Key key;
	private ResponseEntity<String> response;
	public ResponseBean sendMail(MessageBean mBean) {
		RestTemplate restTemplate;
		ResponseBean responseBean = null;
		key = headerKeyManagement.getKey(ConstantValues.EMAIL_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.EMAIL_POST);
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateEmailSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<Object> request = new HttpEntity<>(mBean,headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if(response.getStatusCode() == HttpStatus.ACCEPTED) {
				responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
			}else {
				throw new MailServiceException(ConstantValues.MAIL_NOT_SENT);
			}
		}else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		
		
		return responseBean;
	}
}
