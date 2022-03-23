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
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.beans.WishlistBean;
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
public class WishlistRestService {

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

	
	
	
	public List<WishlistBean> getAllWishlists()
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_GET);
		List<WishlistBean> wishlist = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					WishlistBean[] wishlist_temp = (WishlistBean[]) JsonToBeanConverter.convert(response.getBody(),
							WishlistBean[].class);
					wishlist = ArrayToListConverter.convertArrayToList(wishlist_temp);
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
		return wishlist;
	}
	
	
	
	
	public ResponseBean insertWishlist(String customerid, String productid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_POST);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.CUSTOMER_ID1, customerid);
				map.add(ConstantValues.PRODUCT_ID, productid);
				System.out.println("====="+customerid+"+++++"+productid);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				System.out.println("====="+response);
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
	
	
	public List<WishlistBean> getByCustomerId(String customerid)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_GET);
		 List<WishlistBean> wishlist = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + customerid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					WishlistBean[] wishlist_temp = (WishlistBean[]) JsonToBeanConverter.convert(response.getBody(),
						WishlistBean[].class);
				wishlist = ArrayToListConverter.convertArrayToList(wishlist_temp);
				
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

		return wishlist;
	}
	
			
	public ResponseBean deleteByProductId(String productid,String Customerid)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_DELETE_PRODUCT_ID);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid+ ConstantValues.SLASH_TAG + Customerid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
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
	
//	public WishlistBean getByProductId(String productid,String customerid)
//	{
//		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
//		urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_GET_PRODUCT_ID);
//		WishlistBean wishlist = null;
//			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
//				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
//				headers.set(key.getName(), key.getValue());
//				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid+ ConstantValues.SLASH_TAG + customerid);
//				HttpEntity<Object> request = new HttpEntity<>(headers);
//				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
//				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
//					wishlist = (WishlistBean) JsonToBeanConverter.convert(response.getBody(), WishlistBean.class);
//				}
//				else if(response.getStatusCode()==HttpStatus.OK)
//				{
//					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
//							ResponseBean.class);
//					throw new DataNotFoundException(resBean.getMessage());
//				}
//
//			} else {
//				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
//			}
//
//		return wishlist;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public ResponseBean deleteByCustomerId(String customerid)
//	{
//		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
//		urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_DELETE);
//		ResponseBean responseBean = null;
//			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
//				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
//				headers.set(key.getName(), key.getValue());
//				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + customerid);
//				HttpEntity<Object> request = new HttpEntity<>(headers);
//				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
//				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
//					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
//				}
//				else if(response.getStatusCode()==HttpStatus.OK)
//				{
//					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
//							ResponseBean.class);
//					throw new DataNotFoundException(resBean.getMessage());
//				}
//
//			} else {
//				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
//			}
//
//		return responseBean;
//	}
	
	
//	public List<WishlistBean> getByProductId(String productid)
//	{
//		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
//		urls = serviceUrlsDao.getAccessUrl(UrlDetails.WISHLIST_GET);
//		 List<WishlistBean> wishlist = null;
//			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
//				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
//				headers.set(key.getName(), key.getValue());
//				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid);
//				HttpEntity<Object> request = new HttpEntity<>(headers);
//				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
//				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
//					WishlistBean[] wishlist_temp = (WishlistBean[]) JsonToBeanConverter.convert(response.getBody(),
//							WishlistBean[].class);
//					wishlist = ArrayToListConverter.convertArrayToList(wishlist_temp);
//					
//					}
//				else if(response.getStatusCode()==HttpStatus.OK)
//				{
//					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
//							ResponseBean.class);
//					throw new DataNotFoundException(resBean.getMessage());
//				}
//
//			} else {
//				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
//			}
//
//		return wishlist;
//	}
		
		
}
