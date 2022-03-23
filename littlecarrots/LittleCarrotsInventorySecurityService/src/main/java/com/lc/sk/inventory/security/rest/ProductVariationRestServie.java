package com.lc.sk.inventory.security.rest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
import com.lc.sk.inventory.security.beans.ProductVariationFullInfoBean;
import com.lc.sk.inventory.security.beans.ProductidsBean;
import com.lc.sk.inventory.security.beans.ProductvariationBean;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.SimilarproductsBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class ProductVariationRestServie {
	
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
	
	//get method for product variation
	public List<ProductvariationBean> getAllProductVariations() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTVARIATION_GET);
		List<ProductvariationBean> productvariation = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					ProductvariationBean[] productvariation_temp = (ProductvariationBean[]) JsonToBeanConverter.convert(response.getBody(), ProductvariationBean[].class);
					productvariation = ArrayToListConverter.convertArrayToList(productvariation_temp);
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

		return productvariation;
	}
	
	//post method for product variations
	public ResponseBean insertProductVariation(String pvtype) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTVARIATION_POST);
		ResponseBean responseBean = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());	
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.PVTYPE, pvtype);
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
			
		return responseBean;
	}
	
	
	//post method for similar products
	public ResponseBean insertSimilarProducts(String pvid,String productid[]) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIMILARPRODUCTS_POST);
		ResponseBean responseBean = null;
		//System.out.println(id+"------"+productid);
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());	
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.PVID, pvid);
				for(String ids:productid) {
				map.add(ConstantValues.PRODUCT_ID1, ids);
				}
				//System.out.println("-----hi");
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
				//System.out.println(request);
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

	
	//get method for Similar Products
		public List<SimilarproductsBean> getAllSimilarProducts() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SIMILARPRODUCTS_GET);
			List<SimilarproductsBean> productvariation = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						SimilarproductsBean[] productvariation_temp = (SimilarproductsBean[]) JsonToBeanConverter.convert(response.getBody(), SimilarproductsBean[].class);
						productvariation = ArrayToListConverter.convertArrayToList(productvariation_temp);
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

			return productvariation;
		}
	
		//Variation info
		public List<ProductVariationFullInfoBean> getAllInfoOfVariations(String productid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCT_VARITION_INFO);
			List<ProductVariationFullInfoBean> variation = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + productid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ProductVariationFullInfoBean[] collection_temp = (ProductVariationFullInfoBean[]) JsonToBeanConverter.convert(response.getBody(), ProductVariationFullInfoBean[].class);
						variation = ArrayToListConverter.convertArrayToList(collection_temp);
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
			return variation;

		}
		
		public List<ProductidsBean> getAllProductVariationsProductids() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTVARIATION_PRODUCTIDS);
			List<ProductidsBean> reply=new CopyOnWriteArrayList<>();
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ProductidsBean[] temp = (ProductidsBean[]) JsonToBeanConverter.convert(response.getBody(), ProductidsBean[].class);
						reply=ArrayToListConverter.convertArrayToList(temp);
						
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

			return reply;
		}
		
		
}
