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

import com.lc.sk.inventory.security.beans.Cartmanagement;
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
public class CartmanagementRestService {
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
	
	public List<Cartmanagement> getAllCart() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_GET);
		List<Cartmanagement> cart = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Cartmanagement[] car = (Cartmanagement[]) JsonToBeanConverter.convert(response.getBody(), Cartmanagement[].class);
					cart = ArrayToListConverter.convertArrayToList(car);
				}else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new BackEndDataException(resBean.getMessage());
				}

			} 
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}

		return cart;
	}
	
	public Cartmanagement getByCartid(String cartid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_GET);
		Cartmanagement cart = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + cartid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					cart = (Cartmanagement) JsonToBeanConverter.convert(response.getBody(), Cartmanagement.class);
				}else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new BackEndDataException(resBean.getMessage());
				}

			}
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return cart;

	}
	
	public List<Cartmanagement> getByCustomerid(String customerid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_GET_CUSTOMERID);
		List<Cartmanagement> cart = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + customerid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Cartmanagement[] cart1 = (Cartmanagement[]) JsonToBeanConverter.convert(response.getBody(), Cartmanagement[].class);
					cart = ArrayToListConverter.convertArrayToList(cart1);
				}else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new BackEndDataException(resBean.getMessage());
				}

			}
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return cart;

	}
	public ResponseBean insertCart(String customerid, String productid,String productprice,String quantity,String totalprice) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_POST);
		ResponseBean responseBean = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.CUSTOMER_ID1, customerid);
				map.add(ConstantValues.PRODUCT_ID, productid);
				map.add(ConstantValues.PRODUCT_PRICE, productprice);
				map.add(ConstantValues.QUANTITY1, quantity);
				//map.add(ConstantValues.CART_DATE, cartdate);
				map.add(ConstantValues.TOTAL_PRICE, totalprice);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
               // System.out.println(request);
                //System.out.println(response);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
						|| response.getStatusCode() == HttpStatus.BAD_REQUEST
						|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					throw new BackEndDataException(response.getBody());
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		
		return responseBean;
	}
	public ResponseBean deleteByCartid(String cartid)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_DELETE_CARTID);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + cartid);
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
	
	public ResponseBean deleteByProductId(String cartid,String productid,String customerid)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_DELETE_PRODUCTID);
		ResponseBean responseBean = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + cartid+ ConstantValues.SLASH_TAG + productid + ConstantValues.SLASH_TAG + customerid);
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

	
	public ResponseBean updateCart(String cartid, String customerid, String productid,String productprice,String quantity,String totalprice) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CART_PUT);
		ResponseBean responseBean = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateOrdersSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add("cartid", cartid);
				map.add(ConstantValues.CUSTOMER_ID1, customerid);
				map.add(ConstantValues.PRODUCT_ID, productid);
				map.add(ConstantValues.PRODUCT_PRICE, productprice);
				map.add(ConstantValues.QUANTITY1, quantity);
				//map.add(ConstantValues.CART_DATE, cartdate);
				map.add(ConstantValues.TOTAL_PRICE, totalprice);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
               // System.out.println(request);
                //System.out.println(response);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
						|| response.getStatusCode() == HttpStatus.BAD_REQUEST
						|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					throw new BackEndDataException(response.getBody());
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new RestResponseException(resBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		
		return responseBean;
	}
	
	
}