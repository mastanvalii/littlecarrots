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
import com.lc.sk.inventory.security.beans.NoOfPieces;
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
public class NoOfPiecesRestService {

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

	// POST Insertion
	public ResponseBean insertNoOfPiecesList(String productid, String noofset) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.NOOFPIECES_POST);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.PRODUCT_ID, productid);
				map.add(ConstantValues.NOOFSETS, noofset);

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

	public List<NoOfPieces> getAllNoOfPieces() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.NOOFPIECES_GET);
		List<NoOfPieces> noofpieces = null;

		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					NoOfPieces[] noofpieces_temp = (NoOfPieces[]) JsonToBeanConverter.convert(response.getBody(),
							NoOfPieces[].class);
					noofpieces = ArrayToListConverter.convertArrayToList(noofpieces_temp);
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

			System.out.println(e.getMessage());
			String value = StringTrimmer.trim(e.getMessage());
			System.out.println(value);

			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(value, ResponseBean.class);

			System.out.println(resBean);

			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
				throw new DataNotFoundException(resBean.getMessage());
			} else {
				throw new RestResponseException(resBean.getMessage());
			}
		}*/
		return noofpieces;

	}

	public NoOfPieces getNoOfPiecesId(String pieceid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.NOOFPIECES_GET);
		NoOfPieces noofpieces = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + pieceid);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					noofpieces = (NoOfPieces) JsonToBeanConverter.convert(response.getBody(), NoOfPieces.class);
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

		return noofpieces;

	}

	public ResponseBean update(String pieceid, String productid, String noofset) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.NOOFPIECES_PUT);
		ResponseBean responseBean = null;
		//try {
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.PIECE_ID, pieceid);
				map.add(ConstantValues.PRODUCT_ID, productid);
				map.add(ConstantValues.NOOFSETS, noofset);

				HttpEntity<Object> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				}
				else if(response.getStatusCode()==HttpStatus.OK)
				{
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				//	ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),ResponseBean.class);
					throw new DataNotFoundException(responseBean.getMessage());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
	/*	} catch (Exception e) {
			ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(StringTrimmer.trim(e.getMessage()),
					ResponseBean.class);
			if (resBean.getResponsecode() == HttpStatus.NOT_FOUND.value()) {
				throw new DataNotFoundException(resBean.getMessage());
			} else {
				throw new RestResponseException(resBean.getMessage());
			}
		}*/
		return responseBean;

	}

}
