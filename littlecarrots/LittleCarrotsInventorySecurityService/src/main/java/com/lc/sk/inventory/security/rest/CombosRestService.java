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

import com.lc.sk.inventory.security.beans.ComboFullDetailsBean;
import com.lc.sk.inventory.security.beans.CombosBean;
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
public class CombosRestService {
	
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
	
	public ResponseBean insertCombos(String total_amount,String discount, String total_amt_after_discount,String startdate,String enddate,String status, 
			String title, String description, String ageid, String genderid ) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMBOS_POST);
		ResponseBean responseBean = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());	
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.TOTALAMOUNT, total_amount);
				map.add(ConstantValues.DISCOUNT, discount);
				map.add(ConstantValues.TOTALAMOUNT_AFTER_DISCOUNT, total_amt_after_discount);
				map.add(ConstantValues.STARTDATE, startdate);
				map.add(ConstantValues.ENDDATE, enddate);
				map.add(ConstantValues.STATUS, status);
				map.add(ConstantValues.TITLE, title);
				map.add(ConstantValues.DESCRIPTION, description);
				map.add(ConstantValues.AGEID, ageid);
				map.add(ConstantValues.GENDERID, genderid);
				
			
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
	
	public ResponseBean insertComboProductDetails(String comboid,String productid[],String finalprice[]) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMBOS__PROD_POST);
		ResponseBean responseBean = null;
		
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());	
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add(ConstantValues.COMBOID, comboid);
				System.out.println("----------------------------------");
				System.out.println(comboid+"----"+productid+"----"+finalprice);
				for (String ids : productid) {
					map.add(ConstantValues.PRODUCT_ID1, ids);
				}
				for (String x : finalprice) {
					map.add(ConstantValues.FINALPRICE, x);
				}
			
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
	
	//get method
		public List<ComboFullDetailsBean> getAllCombosfulldetails() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMBOS_FULL_DETAILS);
			List<ComboFullDetailsBean> combo = null;
				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());

					HttpEntity<Object> request = new HttpEntity<>(headers);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
					if (response.getStatusCode() == HttpStatus.ACCEPTED) {
						ComboFullDetailsBean[] combo_temp = (ComboFullDetailsBean[]) JsonToBeanConverter.convert(response.getBody(), ComboFullDetailsBean[].class);
						combo = ArrayToListConverter.convertArrayToList(combo_temp);
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

			return combo;
		}

		//get method
				public List<CombosBean> getAllComboidwithoutproductdetails() {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.INVENTORY_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.COMBOID_WITHOUT_DETAILS);
					List<CombosBean> combo = null;
						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());

							HttpEntity<Object> request = new HttpEntity<>(headers);
							response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								CombosBean[] combo_temp = (CombosBean[]) JsonToBeanConverter.convert(response.getBody(), CombosBean[].class);
								combo = ArrayToListConverter.convertArrayToList(combo_temp);
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

					return combo;
				}

}
