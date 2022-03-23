package com.lc.sk.inventory.security.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.sk.inventory.security.beans.EmailSubscribeBean;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component

public class EmailSubscribeRestService  {
	
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
// TODO Remove unused code found by UCDetector
// 	ObjectMapper jsondata = new ObjectMapper();
	
	
		
		  
		 
		
		public ResponseBean InsertSUbscribers(String emailid) {
			
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SUBSCRIBE_POST);
			ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.EMAILID, emailid);
			
				
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
				System.out.println(request);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				// System.out.println(response);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);

				} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
						|| response.getStatusCode() == HttpStatus.BAD_REQUEST
						|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					throw new BackEndDataException(response.getBody());
				} else if (response.getStatusCode() == HttpStatus.OK) {
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
			return responseBean;
		}

		public List<EmailSubscribeBean> getAllSubscribedEmails() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SUBSCRIBE_GET);

			List<EmailSubscribeBean> mail = null;
			
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
		
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						EmailSubscribeBean[] authorizedUsersList = (EmailSubscribeBean[]) JsonToBeanConverter
								.convert(response.getBody(), EmailSubscribeBean[].class);
						mail = ArrayToListConverter.convertArrayToList(authorizedUsersList);					
					} 
					else if(response.getStatusCode() == HttpStatus.OK) {
							throw new DataNotFoundException(response.getBody());
					}
				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
			return mail;
		}

		
		public EmailSubscribeBean getAllSubscribedEmailsBYID(String emailid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SUBSCRIBE_GET);
			EmailSubscribeBean mail = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					HttpEntity<Object> request = new HttpEntity<>(headers);
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + emailid);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						mail = (EmailSubscribeBean) JsonToBeanConverter.convert(response.getBody(),
								EmailSubscribeBean.class);
					}
					else if(response.getStatusCode()==HttpStatus.OK)
					{
						ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								ResponseBean.class);
						throw new DataNotFoundException(resBean.getMessage());
					}
				} else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}
		
			return mail;
		}

}
