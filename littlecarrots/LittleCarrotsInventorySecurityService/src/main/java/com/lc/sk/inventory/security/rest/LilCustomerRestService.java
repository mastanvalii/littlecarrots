package com.lc.sk.inventory.security.rest;

import java.util.ArrayList;
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
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.LilCustomerBean;
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
public class LilCustomerRestService {

	
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
	
	


	
	public ResponseBean insert(String uniqueid,String email,String firstname,String lastname,String imageurl)

	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.USER_POST);

	ResponseBean responseBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add(ConstantValues.UNIQUE_ID, uniqueid);
			map.add(ConstantValues.EMAIL, email);
			map.add(ConstantValues.FIRST_NAME, firstname);
			map.add(ConstantValues.LAST_NAME, lastname);
			map.add(ConstantValues.IMAGE_URL, imageurl);
			
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			System.out.println(request);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			System.out.println(response);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
			}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new BackEndDataException(resBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}

	return responseBean;
	}


	
	
	
	public ResponseBean update(String userid, String uniqueid,String email,String firstname,String lastname,String imageurl,String phone,String status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.USER_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.USER_ID, userid);
				map.add(ConstantValues.UNIQUE_ID, uniqueid);
				map.add(ConstantValues.FIRST_NAME, firstname);
				map.add(ConstantValues.LAST_NAME,lastname);
				map.add(ConstantValues.IMAGE_URL,imageurl);
				map.add(ConstantValues.PHONE, phone);
				map.add(ConstantValues.STATUS, status);
				
				
				
				HttpEntity<Object> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				//	ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),ResponseBean.class);
					throw new DataNotFoundException(responseBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
	
		return responseBean;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<LilCustomerBean> getAllUser(){
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.USER_GET);
		List<LilCustomerBean> lilcustomer = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					LilCustomerBean[] lilcustomer1 = (LilCustomerBean[]) JsonToBeanConverter.convert(response.getBody(),
							LilCustomerBean[].class);
					lilcustomer = ArrayToListConverter.convertArrayToList(lilcustomer1);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					lilcustomer = new ArrayList<>();
					//	throw new RestResponseException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return lilcustomer;
	}

	
	public LilCustomerBean getByEmail(String email) {
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.USER_GET_EMAIL);
		LilCustomerBean lilcustomer = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + email);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					lilcustomer = (LilCustomerBean) JsonToBeanConverter.convert(response.getBody(), LilCustomerBean.class);
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

		return lilcustomer;
	}
	
	
	public LilCustomerBean getByUniqueId(String uniqueid) {
		System.err.println("@Rest Uinique ID:"+uniqueid);
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.USER_GET_UNIQUEID);
		LilCustomerBean lilcustomer = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + uniqueid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					lilcustomer = (LilCustomerBean) JsonToBeanConverter.convert(response.getBody(), LilCustomerBean.class);
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

		return lilcustomer;
	}
	
	
	
	public List<LilCustomerBean> getAllUsersListByPage(int page, int count){
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION4);
		
		List<LilCustomerBean> pages = null;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				LilCustomerBean[] temp1 = (LilCustomerBean[]) JsonToBeanConverter.convert(response.getBody(),
						LilCustomerBean[].class);
				pages = ArrayToListConverter.convertArrayToList(temp1);
			}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	
	public int getAllUsersPageCount(int page, int count){
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION5);
		
		int pages = 0;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				int temp1 = (Integer) JsonToBeanConverter.convert(response.getBody(),
						Integer.class);
				pages = temp1;
		}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	public LilCustomerBean Validate(String email, String phone) {
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		urls = serviceUrlsDao.getAccessUrl(UrlDetails.VALIDATE_MAIL_PHONE);
		LilCustomerBean lilcustomer = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + email+  ConstantValues.SLASH_TAG+ phone);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					lilcustomer = (LilCustomerBean) JsonToBeanConverter.convert(response.getBody(), LilCustomerBean.class);
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

		return lilcustomer;
	}
	


	
	
}
