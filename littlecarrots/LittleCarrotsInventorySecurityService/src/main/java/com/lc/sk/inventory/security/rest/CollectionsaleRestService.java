package com.lc.sk.inventory.security.rest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.lc.sk.inventory.security.beans.CollectionSalePack1Bean;
import com.lc.sk.inventory.security.beans.CollectionSalePackBean;
import com.lc.sk.inventory.security.beans.CollectionSalewithProductIdsBean;
import com.lc.sk.inventory.security.beans.CollectionsaleBean;
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
public class CollectionsaleRestService {

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
	public List<CollectionsaleBean> getAllCollectionsale() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_GET);
		List<CollectionsaleBean> collection = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					CollectionsaleBean[] collection_temp = (CollectionsaleBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionsaleBean[].class);
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
	
	public CollectionsaleBean getAllCollectionById(String id) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_GET);
		CollectionsaleBean collection = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + id);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					collection = (CollectionsaleBean) JsonToBeanConverter.convert(response.getBody(), CollectionsaleBean.class);
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

	public ResponseBean insertCollectionsale(String title,String genderid,String badge,String display,String startdate, String enddate ) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_POST);
		ResponseBean responseBean = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());	
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.TITLE, title);
				map.add(ConstantValues.GENDERID, genderid);
				map.add(ConstantValues.BADGE, badge);
				map.add(ConstantValues.DISPLAY, display);
				map.add(ConstantValues.STARTDATE, startdate);
				map.add(ConstantValues.ENDDATE, enddate);
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
	
	
	public List<CollectionSalewithProductIdsBean> getAllCollectionByGenerId(String genderid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_GET_BY_GENDER);
		List<CollectionSalewithProductIdsBean> collection = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + genderid);
				HttpEntity<Object> request = new HttpEntity<>(headers);				
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					CollectionSalewithProductIdsBean[] collection_temp = (CollectionSalewithProductIdsBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionSalewithProductIdsBean[].class);
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

	public CollectionSalePack1Bean getAllCollectionFullInformation(String id,String siteview,String genderid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTION3);
		CollectionSalePack1Bean collection = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + id +ConstantValues.SLASH_TAG+ siteview+ ConstantValues.SLASH_TAG+genderid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					collection = (CollectionSalePack1Bean) JsonToBeanConverter.convert(response.getBody(), CollectionSalePack1Bean.class);
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

	public List<CollectionSalePackBean> getAllCollectionInformation(String genderid,String siteview) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTION2);
		List<CollectionSalePackBean> collection = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + genderid+ConstantValues.SLASH_TAG+siteview);
				HttpEntity<Object> request = new HttpEntity<>(headers);				
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					CollectionSalePackBean[] collection_temp = (CollectionSalePackBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionSalePackBean[].class);
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
	
	public List<CollectionSalePackBean> getAllGenderCollectionInformation() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.NEW_COLLECTION);
		List<CollectionSalePackBean> collection = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl());
				HttpEntity<Object> request = new HttpEntity<>(headers);				
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					CollectionSalePackBean[] collection_temp = (CollectionSalePackBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionSalePackBean[].class);
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
	
	//Customer Phase
	//get method
		public List<CollectionSalePackBean> getAllCollectionsBasedOnIcons() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTION_ICONMENU);
			List<CollectionSalePackBean> collection = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						CollectionSalePackBean[] collection_temp = (CollectionSalePackBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionSalePackBean[].class);
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
		
		public List<CollectionSalePackBean> getCollectionSaleBasedOnSiteview(String siteview, String gender) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTION_SITEVIEW);
			List<CollectionSalePackBean> collection = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() +ConstantValues.SLASH_TAG+siteview+"/"+gender);
					HttpEntity<Object> request = new HttpEntity<>(headers);				
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);				
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						CollectionSalePackBean[] collection_temp = (CollectionSalePackBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionSalePackBean[].class);
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
		
		
		
		public CollectionSalePack1Bean getCollectionLandingView1(String id,String siteview) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTION_LANDING_VIEW);
			CollectionSalePack1Bean collection = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + id +ConstantValues.SLASH_TAG+ siteview);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						collection = (CollectionSalePack1Bean) JsonToBeanConverter.convert(response.getBody(), CollectionSalePack1Bean.class);
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
		
		private List<CollectionSalePackBean> menupop = new CopyOnWriteArrayList<>();
		
		@PostConstruct
		public void cacheCollectionView() {
			System.err.println("-------------------------------------------------------");
			System.err.println("Collection List icons loading....");
			menupop = this.getAllCollectionsBasedOnIcons();
			System.err.println("--------------------------COMPLETE-----------------------------");
			
		}
		
		public List<CollectionSalePackBean> getAllCollectionsBasedOnIcons1(){
			return this.menupop;
		}
		
}
