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
import com.lc.sk.inventory.security.beans.Batch;
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
public class BatchRestService {
	
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

	
	public List<Batch> getAllBatchList() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BATCHES_GET);
		List<Batch> batches = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Batch[] batch = (Batch[]) JsonToBeanConverter.convert(response.getBody(),
							Batch[].class);
					batches = ArrayToListConverter.convertArrayToList(batch);
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
		return batches;
	}
	
	
	//insertion//
		public ResponseBean insertBatch( String warehouseid, String dateofpurchase, String purchasedby, String invamount,
				String whoinserted,  boolean status, String isocode, String qty, String sellerid)
		{
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BATCHES_POST);
			ResponseBean responseBean = null;

				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.WAREHOUSE_ID, warehouseid);
					map.add(ConstantValues.DATE_OF_PURCHASE, dateofpurchase);
					map.add(ConstantValues.PURCHASE_BY, purchasedby);
					map.add(ConstantValues.INV_AMOUNT, invamount);
					map.add(ConstantValues.WHO_INSERTED, whoinserted);
					map.add(ConstantValues.WAREHOUSE_STATUS, status+"");
					map.add(ConstantValues.ISOCODE, isocode);
					map.add(ConstantValues.QUANTITY, qty);
					map.add(ConstantValues.SELLER_ID, sellerid);
					
					

					HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
					} else if (response.getStatusCode() == HttpStatus.OK) {
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
			/*}catch (Exception e) {
				throw new RestResponseException(e.getMessage());
			}*/
			return responseBean;
		}
		
		//get by id
		public Batch getBatchById(String batchid)
		{
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BATCHES_GET);
			Batch batches = null;
			//try {
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl()+ConstantValues.SLASH_TAG+batchid);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						batches = (Batch) JsonToBeanConverter.convert(response.getBody(),
								 Batch.class);
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
		/*	} catch (Exception e) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
						ResponseBean.class);
				if (resBean.getResponsecode() == SecurityHttpStatus.NOT_FOUND) {
					throw new DataNotFoundException(resBean.getMessage());
				} else {
					throw new RestResponseException(resBean.getMessage());
				}
			}*/

			return batches;
		}
		
	//UPDATE
		public ResponseBean updateBatch(String batchid, String status)
		{
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.BATCHES_PUT);
			ResponseBean responseBean = null;
			//try {
			
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					urls.setUrl(urls.getUrl()+ConstantValues.SLASH_TAG+batchid+ConstantValues.SLASH_TAG+status);
					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					 responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							 ResponseBean.class);
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
			/*}catch (Exception e) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
						ResponseBean.class);
				if (resBean.getResponsecode() == SecurityHttpStatus.NOT_FOUND) {
					throw new DataNotFoundException(resBean.getMessage());
				} else {
					throw new RestResponseException(resBean.getMessage());
				}
			}*/
			return responseBean;
		}


}
