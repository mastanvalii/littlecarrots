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
import com.lc.sk.inventory.security.beans.ProductAge;
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
public class ProductAgeRestService {

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

	public ResponseBean addProductAge(String ageid, String des)

	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTAGE_POST);

		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.DES, des);
				map.add(ConstantValues.AGE_ID, ageid);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
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

//		} catch (Exception e) {
//
//			System.out.println(e.getMessage());
//
//			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
//					ResponseBean.class);
//
//			System.out.println(resBean);
//
//			if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
//					|| response.getStatusCode() == HttpStatus.BAD_REQUEST
//					|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//				throw new BackEndDataException(resBean.getMessage());
//			} else {
//				throw new RestResponseException(resBean.getMessage());
//			}
//		}
		return responseBean;
	}

	public List<ProductAge> getAll() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTAGE_GET);
		List<ProductAge> productage1 = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					ProductAge[] productage = (ProductAge[]) JsonToBeanConverter.convert(response.getBody(),
							ProductAge[].class);
					productage1 = ArrayToListConverter.convertArrayToList(productage);
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
		//} catch (Exception e) {
//			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
//					ResponseBean.class);
//			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
//				throw new DataNotFoundException(resBean.getMessage());
//			} else {
//				throw new RestResponseException(resBean.getMessage());
//			}
//	}

		return productage1;
	}

	public ProductAge getProductAgeById(String ageid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTAGE_GET);
		ProductAge productage = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + ageid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					productage = (ProductAge) JsonToBeanConverter.convert(response.getBody(), ProductAge.class);
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
//		} catch (Exception e) {
//			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
//					ResponseBean.class);
//			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
//				throw new DataNotFoundException(resBean.getMessage());
//			} else {
//				throw new RestResponseException(resBean.getMessage());
//			}
//		}

		return productage;

	}

	public ResponseBean update(String ageid, String des) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PRODUCTAGE_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.AGE_ID, ageid);
				map.add(ConstantValues.DES, des);

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
//		} catch (Exception e) {
//			throw new RestResponseException(e.getMessage());
//		}
		return responseBean;
	}

}
