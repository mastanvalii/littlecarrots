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

import com.lc.sk.inventory.security.beans.CollectionsaleprodBean;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
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
public class CollectionsaleprodRest {
	
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
	
	//get method
		public List<CollectionsaleprodBean> getAllCollectionsale(int num) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALEPROD_GET);
			List<CollectionsaleprodBean> collection = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						CollectionsaleprodBean[] collection_temp = (CollectionsaleprodBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionsaleprodBean[].class);
						collection = ArrayToListConverter.convertArrayToList(collection_temp);
					}else if(response.getStatusCode()==HttpStatus.OK)
					{
						if(num==1) {
							collection = new ArrayList<>();
							return collection;
						}else if(num==0) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new BackEndDataException(resBean.getMessage());
						}
						
					}

				} 
				else {
					throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
				}

			return collection;
		}
		
		public ResponseBean insertCollectionsale(String id,String productid[]) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALEPROD_POST);
			ResponseBean responseBean = null;
			System.out.println(id+"------"+productid);
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());	
					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.ID, id);
					for(String ids:productid) {
					map.add(ConstantValues.PRODUCT_ID1, ids);
					}
					System.out.println("-----hi");
					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
					System.out.println(request);
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
		
		
		
		public CollectionsaleprodBean getAllCollectionSIds(String csid) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALEPROD_GET_SID);
			CollectionsaleprodBean collection = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + csid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						collection = (CollectionsaleprodBean) JsonToBeanConverter.convert(response.getBody(), CollectionsaleprodBean.class);
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
			return collection;

		}
		
		public List<CollectionsaleprodBean> getAllCollectionByProductId(String id) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALEPROD_GET);
			List<CollectionsaleprodBean> collection = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + id);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						CollectionsaleprodBean[] collection_temp = (CollectionsaleprodBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionsaleprodBean[].class);
						collection = ArrayToListConverter.convertArrayToList(collection_temp);
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
			return collection;

		}

}
