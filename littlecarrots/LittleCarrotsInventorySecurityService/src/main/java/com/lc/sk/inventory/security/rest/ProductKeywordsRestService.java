package com.lc.sk.inventory.security.rest;

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
import com.lc.sk.inventory.security.beans.ProductKeywords;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;
@Component
public class ProductKeywordsRestService {
	
	
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
// 		ObjectMapper jsondata = new ObjectMapper();
		
		
		//Get by Productid
		public ProductKeywords getProductKeywordsByPId(String productid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTKEYWORDS_GET);
			ProductKeywords productkeyword = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + Long.parseLong(productid));
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					System.out.println(request);
					System.out.println(response);

					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						productkeyword = (ProductKeywords) JsonToBeanConverter.convert(response.getBody(),
								ProductKeywords.class);
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
			

			return productkeyword;
		}
		
		
		
		
		//Post Method
		public ResponseBean InsertProductKeywordsDetails(String productid,String keywords) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTKEYWORDS_POST);
			ResponseBean responseBean = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.PRODUCT_ID, productid);
					map.add(ConstantValues.KEYWORDS, keywords);
					

					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

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
			/*} catch (Exception e) {
				throw new RestResponseException(e.getMessage());
			}*/
			return responseBean;
		}
		
		
		//Put Insertion
				public ResponseBean UpdateProductKeywordsDetails(String productid,String keywords)
				{
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTKEYWORDS_PUT);
					ResponseBean responseBean = null;
					//try {
					//urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + new Long (productid).longValue());
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());

							MultiValueMap<String, String> map = new LinkedMultiValueMap<>();														
							map.add(ConstantValues.KEYWORDS, keywords);
							
							HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
							urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid);
							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
							} 
							else if(response.getStatusCode()==HttpStatus.OK)
							{
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new RestResponseException(resBean.getMessage());
							}else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY ||response.getStatusCode() == HttpStatus.BAD_REQUEST || response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
								throw new BackEndDataException(response.getBody());
							}
						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
//					} catch (Exception e) {
//						throw new RestResponseException(e.getMessage());
//					}
					return responseBean;
				}
		
		
}
