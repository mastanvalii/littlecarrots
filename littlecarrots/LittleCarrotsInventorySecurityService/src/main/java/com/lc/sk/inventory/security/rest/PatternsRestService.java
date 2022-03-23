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
import com.lc.sk.inventory.security.beans.Patterns;
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
public class PatternsRestService {

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
	
	
	
	
	public List<Patterns> getAllPatternsDetails() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PATTERNS_GET);
		List<Patterns> patterns = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Patterns[] patid = (Patterns[]) JsonToBeanConverter.convert(response.getBody(),
							Patterns[].class);
					patterns = ArrayToListConverter.convertArrayToList(patid);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new DataNotFoundException(resBean.getMessage());
				}
				
			}
			else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		/*} catch (Exception e) {
			System.out.println("MES:::"+e.getMessage());
			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
					ResponseBean.class);
			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
				throw new DataNotFoundException(resBean.getMessage());
			} else {
				throw new RestResponseException(resBean.getMessage());
			}
		}*/

		return patterns;
	}

	public Patterns getPatternsById(String patid)
	{
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PATTERNS_GET);
		Patterns patterns = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl()+ConstantValues.SLASH_TAG+patid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					patterns = (Patterns) JsonToBeanConverter.convert(response.getBody(),
							Patterns.class);
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
		/*} catch (Exception e) {
			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
					ResponseBean.class);
			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
				throw new DataNotFoundException(resBean.getMessage());
			} else {
				throw new RestResponseException(resBean.getMessage());
			}
		}*/

		return patterns;
	}
	
		
	//POST Insertion
			public ResponseBean InsertPatternsDetails(String description,String productid)
			{
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PATTERNS_POST);
				ResponseBean responseBean = null;
				//try {
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
						map.add(ConstantValues.DESCRIPTION, description);
						map.add(ConstantValues.PRODUCTID, productid);
						
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
				/*} catch (Exception e) {
					throw new RestResponseException(e.getMessage());
				}*/
				return responseBean;
			}
			
	
			//Put Insertion
			public ResponseBean UpdatePatternsDetails(String patid,String description,String productid)
			{
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PATTERNS_PUT);
				ResponseBean responseBean = null;
				//try {
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
						map.add(ConstantValues.PATID, patid);
						map.add(ConstantValues.DESCRIPTION, description);
						map.add(ConstantValues.PRODUCTID, productid);
						
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
				/*} catch (Exception e) {
					throw new RestResponseException(e.getMessage());
				}*/
				return responseBean;
			}
	
	
	
}
