package com.lc.sk.inventory.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.sk.inventory.security.beans.ApprovalPaging;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.PageCountBean;
import com.lc.sk.inventory.security.beans.ProductFullDetails;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class ProductPaginationRestService {

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
	
	//GET ALL PRODUCTS BY PAGE
	public List<ProductFullDetails> getAllProductByPage(int page, int count){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION1);
		
		List<ProductFullDetails> pages = null;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				ProductFullDetails[] temp1 = (ProductFullDetails[]) JsonToBeanConverter.convert(response.getBody(),
						ProductFullDetails[].class);
			//	pages = temp1;
				pages = ArrayToListConverter.convertArrayToList(temp1);
			//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
			}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	
	public List<ApprovalPaging> getAllApprovals(int page, int count){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION2);
		
		List<ApprovalPaging> pages = null;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				ApprovalPaging[] temp1 = (ApprovalPaging[]) JsonToBeanConverter.convert(response.getBody(),
						ApprovalPaging[].class);
			//	pages = temp1;
				pages = ArrayToListConverter.convertArrayToList(temp1);
			//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
			}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	public int getAllApprovalsPageCount(int page, int count){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION3);
		
		int pages = 0;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				int temp1 = (Integer) JsonToBeanConverter.convert(response.getBody(),
						Integer.class);
				pages = temp1;
			//	pages = ArrayToListConverter.convertArrayToList(temp1);
			//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
			}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	
	//GET ALL PRODUCTS BY PAGE COUNT
	public int getAllProductPageCount(int page, int count){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PAGENATION);
		
		int pages = 0;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				int temp1 = (Integer) JsonToBeanConverter.convert(response.getBody(),
						Integer.class);
				pages = temp1;
			//	pages = ArrayToListConverter.convertArrayToList(temp1);
			//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
			}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	
	//GET ALL PRODUCTS BY PAGE COUNT FOR SEARCH
	public int getAllProductSearchPageCount(int page, int count, String search, String dummy){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SEARCH_GET);
		
		int pages = 0;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page+  ConstantValues.SLASH_TAG +count+  ConstantValues.SLASH_TAG +search+  ConstantValues.SLASH_TAG +dummy);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				int temp1 = (Integer) JsonToBeanConverter.convert(response.getBody(),
						Integer.class);
				pages = temp1;
			//	pages = ArrayToListConverter.convertArrayToList(temp1);
			//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
			}
				
		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return pages;
				
	}
	
	//GET ALL PRODUCTS BY PAGE for Search
	public List<ProductFullDetails> getAllProductSearchByPage(String search,int des){
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SEARCH_GET);
		
		List<ProductFullDetails> pages = null;
		

		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG +search + ConstantValues.SLASH_TAG + des );
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				ProductFullDetails[] temp1 = (ProductFullDetails[]) JsonToBeanConverter.convert(response.getBody(),
						ProductFullDetails[].class);
			//	pages = temp1;
				pages = ArrayToListConverter.convertArrayToList(temp1);
			//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
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
		return pages;
				
	}
	
	//Load on scroll
		public PageCountBean getSearchQueryCount(String query, int page, int count){
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SCROL_SEARCH_GET);
			PageCountBean pages = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + query+  ConstantValues.SLASH_TAG +page+  ConstantValues.SLASH_TAG +count);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					PageCountBean temp1 = (PageCountBean) JsonToBeanConverter.convert(response.getBody(),
							PageCountBean.class);
					pages = temp1;	
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
					
		}
			return pages;
		}
		
		//GET ALL PRODUCTS BY PAGE for Search
		public List<ProductFullDetails> getSearchQueryByPage(String query,int page, int count){
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SCROL_SEARCH_GET_INFO);
			
			List<ProductFullDetails> pages = null;
			

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG +query + ConstantValues.SLASH_TAG + page +ConstantValues.SLASH_TAG + count );
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					ProductFullDetails[] temp1 = (ProductFullDetails[]) JsonToBeanConverter.convert(response.getBody(),
							ProductFullDetails[].class);
				//	pages = temp1;
					pages = ArrayToListConverter.convertArrayToList(temp1);
				//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
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
			return pages;
					
		}
		
		
		//GET ALL PRODUCTS BY PAGE for Search
				public List<ProductFullDetails> getAllCacheInformation(){
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CACHE_DATA1);
					
					List<ProductFullDetails> pages = null;
					

					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							ProductFullDetails[] temp1 = (ProductFullDetails[]) JsonToBeanConverter.convert(response.getBody(),
									ProductFullDetails[].class);
						//	pages = temp1;
							pages = ArrayToListConverter.convertArrayToList(temp1);
						//	pages = (List<ProductFullDetails>) JsonToBeanConverter.convert(response.getBody(), ProductFullDetails.class);
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
					return pages;
							
				}
}
