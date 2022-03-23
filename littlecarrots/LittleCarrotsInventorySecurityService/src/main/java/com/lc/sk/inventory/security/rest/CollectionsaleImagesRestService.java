package com.lc.sk.inventory.security.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.lc.sk.inventory.security.beans.CollectionsaleImagesBean;
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
import com.lc.sk.inventory.security.util.MultipartInputStreamFileResource;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class CollectionsaleImagesRestService {
	
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
			public List<CollectionsaleImagesBean> getAllCollectionsaleImages(int num) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_IMAGES_GET);
				List<CollectionsaleImagesBean> collection = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers = new HttpHeaders();
						headers.set(key.getName(), key.getValue());

						HttpEntity<Object> request = new HttpEntity<>(headers);
						System.out.println(request);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							CollectionsaleImagesBean[] collection_temp = (CollectionsaleImagesBean[]) JsonToBeanConverter.convert(response.getBody(), CollectionsaleImagesBean[].class);
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

			public CollectionsaleImagesBean getAllCollectionIMAGEBYIMGID(String imageid) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_IMAGES_GET);
				CollectionsaleImagesBean collection = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + imageid);
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							collection = (CollectionsaleImagesBean) JsonToBeanConverter.convert(response.getBody(), CollectionsaleImagesBean.class);
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
			
			public ResponseBean insertCOllectoionImage(long id, MultipartFile icon, MultipartFile mobileview1,MultipartFile mobileview2,MultipartFile desktopview1,MultipartFile desktopview2) {
				RestTemplate restTemplate;
				key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
				ResponseEntity<String> response;
				UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COLLECTIONSALE_IMAGES_POST);
				ResponseBean responseBean = null;
				//try {
				
				System.out.println("hi");
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						headers.setContentType(MediaType.MULTIPART_FORM_DATA);
						
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
						map.add(ConstantValues.ID, id);	
						try {
							map.add(ConstantValues.ICON, new MultipartInputStreamFileResource(icon.getInputStream(), icon.getOriginalFilename()));
							map.add(ConstantValues.MOBILEVIEW1, new MultipartInputStreamFileResource(mobileview1.getInputStream(), mobileview1.getOriginalFilename()));
							map.add(ConstantValues.MOBILEVIEW2, new MultipartInputStreamFileResource(mobileview2.getInputStream(), mobileview2.getOriginalFilename()));
							map.add(ConstantValues.DESKTOPVIEW1, new MultipartInputStreamFileResource(desktopview1.getInputStream(), desktopview1.getOriginalFilename()));
							map.add(ConstantValues.DESKTOPVIEW2, new MultipartInputStreamFileResource(desktopview2.getInputStream(), desktopview2.getOriginalFilename()));
						} catch (IOException e) {
							e.printStackTrace();
						}
						

						HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
							System.out.println(request);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						System.out.println(response);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
						}
						else if(response.getStatusCode()==HttpStatus.OK)
						{
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new RestResponseException(resBean.getMessage());
						}else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
								|| response.getStatusCode() == HttpStatus.BAD_REQUEST
								|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
							throw new BackEndDataException(response.getBody());
						}
					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
//				} catch (Exception e) {
//					throw new RestResponseException(e.getMessage());
//				}
				return responseBean;
				
			}
			
			
}
