/**
 * 
 */
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

import com.lc.sk.inventory.security.beans.Authorization;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.AuthorizationNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ArrayToListConverter;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.SecurityHttpStatus;
import com.lc.sk.inventory.security.util.UrlDetails;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
@Component
public class AuthorizationRestService {

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
	private ResponseBean responseBean = new ResponseBean();

	/**
	 * 
	 */
	public AuthorizationRestService() {
		super();

	}

	// Url id 100
	public List<Authorization> getAllRoleList() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZATION_SERVICE_URL_GET_ID);
		List<Authorization> authorizations = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					Authorization[] authorization = (Authorization[]) JsonToBeanConverter.convert(response.getBody(),
							Authorization[].class);
					authorizations = ArrayToListConverter.convertArrayToList(authorization);
				} else if (response.getStatusCode() == HttpStatus.OK) {
						throw new RestResponseException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return authorizations;
	}

	// Url id 100
	public Authorization getRoleByName(String rolename) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZATION_SERVICE_URL_GET_ID);
		Authorization authorizations = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				HttpEntity<Object> request = new HttpEntity<>(headers);
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + rolename);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					authorizations = (Authorization) JsonToBeanConverter.convert(response.getBody(),
							Authorization.class); // jsondata.readValue(response.getBody(), );
					if (authorizations != null && authorizations.getRoleName() != null
							&& !authorizations.getRoleName().equals("")) {
						authorizations.setResponsecode(SecurityHttpStatus.OK);
					}
				} else {
					throw new RestResponseException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
		return authorizations;
	}

	// Url id 101
	public ResponseBean insertAuthorization(String rolename, String description, boolean status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZATION_SERVICE_URL_POST_ID);

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.AUTHORIZATION_ROLE_NAME_VARIABLE_NAME, rolename);
				map.add(ConstantValues.AUTHORIZATION_ROLE_DESCRIPTIONS_VARIABLE_NAME, description);
				map.add(ConstantValues.AUTHORIZATION_ROLE_STATUS_VARIABLE_NAME, status+"" );

				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					return (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					throw new RestResponseException(response.getBody());
				}
			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
	

		return responseBean;
	}

	// Url id 102
	public ResponseBean updateAuthorizationStatus(String rolename, boolean status) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.AUTHORIZATION_SERVICE_URL_PUT_ID);
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				HttpEntity<Object> request = new HttpEntity<>(headers);
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + rolename + ConstantValues.SLASH_TAG + status);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					throw new RestResponseException(response.getBody());
				}
			} else {
				throw new AuthorizationNotFoundException(ConstantValues.VALUE_NOT_FOUND);
			}
		return responseBean;
	}

}
