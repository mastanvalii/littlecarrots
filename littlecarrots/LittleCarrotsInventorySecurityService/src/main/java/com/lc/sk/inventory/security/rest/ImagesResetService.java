/**
 * 
 */
package com.lc.sk.inventory.security.rest;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.sk.inventory.security.beans.FileData;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
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
public class ImagesResetService {
	@Autowired
	private ServiceUrlsDao serviceUrlsDao;
	@Autowired
	private ServiceHttpRequestFactory requestFacotry;

	private UrlBean urls;
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private ResponseEntity<String> response;
// TODO Remove unused code found by UCDetector
// 	ObjectMapper jsondata = new ObjectMapper();
	
	public List<Long> getAllProductIdsFromImgService() {
		RestTemplate restTemplate;
		List<Long> ids = new CopyOnWriteArrayList<Long>();
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.IMAGE_GET_S2);
		if(!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateImageSsl(urls.getTimeout()));
			List<Long> entity = new CopyOnWriteArrayList<>();
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				Long[] imgs = (Long[])JsonToBeanConverter.convert(response.getBody(), Long[].class);
						entity = ArrayToListConverter.convertArrayToList(imgs);
						Set<Long> values = new LinkedHashSet<Long>();
						for(Long ie: imgs) {
							values.add(ie);
						}
						for(Long l: values) {
							ids.add(l);
						}
			}	else if(response.getStatusCode()==HttpStatus.OK)
			{
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}
			
		}else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return ids;
	}
	
	public byte[] getProductImage(Long productid) {
		RestTemplate restTemplate;
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.IMAGE_GET4);
		byte[] data = null;
		if(!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			this.restTemplate = new RestTemplate(requestFacotry.validateImageSsl(urls.getTimeout()));
			HttpEntity<Object> request = new HttpEntity<>(headers);
			urls.setUrl(urls.getUrl()+"/"+productid);
			response = this.restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				data = (byte[])response.getBody().getBytes();
			}else  if(response.getStatusCode()==HttpStatus.OK)
			{
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}
			
		}else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return data;
	}
	
	public List<FileData> getProductList2() {
		RestTemplate restTemplate;
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.IMAGE_GET5);
		ArrayList<FileData> data = null;
		if(!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			this.restTemplate = new RestTemplate(requestFacotry.validateImageSsl(urls.getTimeout()));
			HttpEntity<Object> request = new HttpEntity<>(headers);
			urls.setUrl(urls.getUrl());
			response = this.restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				FileData[] data1 = (FileData[])JsonToBeanConverter.convert(response.getBody(),FileData[].class);
				data = (ArrayList<FileData>) ArrayToListConverter.convertArrayToList(data1);
			}else  if(response.getStatusCode()==HttpStatus.OK)
			{
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}
			
		}else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return data;
	}
	
}
