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

import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.Lcprops;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;
@Component
public class LcpropsRestService {
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
	
	public List<Lcprops> getAll(){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LC_PROPS_GET);
	List<Lcprops> lcprops = null;
	if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
		restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
		headers.set(key.getName(), key.getValue());
		HttpEntity<Object> request = new HttpEntity<>(headers);
		response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
		if (response.getStatusCode() == HttpStatus.ACCEPTED) {
			Lcprops[] lp = (Lcprops[])JsonToBeanConverter.convert(response.getBody(), Lcprops[].class);
			lcprops = ArrayToListConverter.convertArrayToList(lp);
			
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
	return lcprops;
}

public Lcprops getlcprops(String id) {	
	RestTemplate restTemplate;
	key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
	ResponseEntity<String> response;
	UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LC_PROPS_GET1);
	Lcprops lcprops = null;
	if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
		restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
		headers.set(key.getName(), key.getValue());

		HttpEntity<Object> request = new HttpEntity<>(headers);
		response = restTemplate.exchange(urls.getUrl()+"/"+id, urls.getMethod(), request, String.class);
		if (response.getStatusCode() == HttpStatus.ACCEPTED) {
			lcprops = (Lcprops)JsonToBeanConverter.convert(response.getBody(), Lcprops.class);
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
	return lcprops;
}
	
	public ResponseBean updateStatus(String id, boolean status)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LC_PROPS_PUT);
		ResponseBean responseBean = null;
		
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl()+ConstantValues.SLASH_TAG+id+ConstantValues.SLASH_TAG+status);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				 responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						 ResponseBean.class);
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
			return responseBean;
}
	public ResponseBean insert(String prop, String status)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LC_PROPS_POST);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.PROP, prop);
				map.add(ConstantValues.PROP_STATUS, status);
				
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					throw new BackEndDataException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		
		return responseBean;
	}
// TODO Remove unused code found by UCDetector
// 	public boolean getByProp(String prop) {	
// 		RestTemplate restTemplate;
// 		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
// 		ResponseEntity<String> response;
// 		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LC_PROPS_GET2);
// 		boolean lcprops = false;
// 		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
// 			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
// 			headers.set(key.getName(), key.getValue());
// 
// 			HttpEntity<Object> request = new HttpEntity<>(headers);
// 			response = restTemplate.exchange(urls.getUrl()+"/"+prop, urls.getMethod(), request, String.class);
// 			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
// 				lcprops = (boolean)JsonToBeanConverter.convert(response.getBody(), Lcprops.class);
// 			}
// 			
// 			else if(response.getStatusCode()==HttpStatus.OK)
// 			{
// 				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
// 						ResponseBean.class);
// 				throw new DataNotFoundException(resBean.getMessage());
// 			}				
// 			
// 		} else {
// 			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
// 		}
// 		return lcprops;
// 	}
	
	public Lcprops getPropStatus(String prop) {	
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.LC_PROPS_GET);
		Lcprops lcprops = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl()+"/"+prop, urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				lcprops = (Lcprops)JsonToBeanConverter.convert(response.getBody(), Lcprops.class);
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
		return lcprops;
	}
}