package com.lc.sk.inventory.security.rest;

import java.io.File;
import java.util.Date;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.sk.inventory.security.beans.InvoicesBean;
import com.lc.sk.inventory.security.beans.Invoices_Info_by_userBean;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.SalesTime;
import com.lc.sk.inventory.security.beans.SoldproductsBean;
import com.lc.sk.inventory.security.beans.SoldproductstatusBean;
import com.lc.sk.inventory.security.beans.SourcesalesBean;
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
import com.lc.sk.inventory.security.util.MultipartInputStreamFileResource;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class InvoiceManagementRestService {
	
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

	//Get All Source Sales
		public List<SourcesalesBean> getAllSources() {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SOURCE_GET);
			List<SourcesalesBean> sources = null;
			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() );
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					SourcesalesBean[] ob = (SourcesalesBean[]) JsonToBeanConverter.convert(response.getBody(), SourcesalesBean[].class);
					sources = ArrayToListConverter.convertArrayToList(ob);
				} else if (response.getStatusCode() == HttpStatus.OK) {
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new DataNotFoundException(resBean.getMessage());
				}

			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
			return sources;
		}
		
		//Get All Invoices
				public List<InvoicesBean> getAllInvoices() {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.INVOICES_GET);
					List<InvoicesBean> invoices = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							InvoicesBean[] ob = (InvoicesBean[]) JsonToBeanConverter.convert(response.getBody(), InvoicesBean[].class);
							invoices = ArrayToListConverter.convertArrayToList(ob);
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return invoices;
				}
				
				//Status 
				public List<SoldproductstatusBean> getStatus() {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.STATUS_GET);
					List<SoldproductstatusBean> status = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							SoldproductstatusBean[] ob = (SoldproductstatusBean[]) JsonToBeanConverter.convert(response.getBody(), SoldproductstatusBean[].class);
							status = ArrayToListConverter.convertArrayToList(ob);
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return status;
				}
				
				//Sold Products
				public List<SoldproductsBean> getSoldProductsInfo() {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SOLD_PRODUCTS_GET);
					List<SoldproductsBean> status = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							SoldproductsBean[] ob = (SoldproductsBean[]) JsonToBeanConverter.convert(response.getBody(), SoldproductsBean[].class);
							status = ArrayToListConverter.convertArrayToList(ob);
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return status;
				}
				
				
				// POST for orders
				public ResponseBean InsertOrders(String sid, String orid, String dateoforder,String dateofinvoice,
						String total, String shipping,String paymentmode, String transactionid,
						String statusoftheinvoice, String user, String invoiceid, String prid[],String qty[],
						String netamount[], String gst[], String discount[], String totalproductprice[],String status) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.INVOICE_POST);
					ResponseBean responseBean = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
						
						map.add(ConstantValues.SID, sid);
						map.add(ConstantValues.ORDERID, orid);
						map.add(ConstantValues.DATEOFORDER, dateoforder);
						map.add(ConstantValues.DATEOFINVOICE, dateofinvoice);
						map.add(ConstantValues.TOTAL, total);
						map.add(ConstantValues.SHIPPING, shipping);
						map.add(ConstantValues.PAYMENTMODE, paymentmode);
						map.add(ConstantValues.TRANSACTIONID, transactionid);
						map.add(ConstantValues.STATUSOFTHEINVOICE, statusoftheinvoice);
						map.add(ConstantValues.USER, user);
						map.add(ConstantValues.INVOICEID, invoiceid);

						for (String ids : prid) {
							map.add(ConstantValues.PRODUCTIDS, ids);
						}
						for (String x : qty) {
							map.add(ConstantValues.QUANTITYS, x);
						}
						for (String y : netamount) {
							map.add(ConstantValues.NETAMOUNT, y);
						}
						for (String z : gst) {
							map.add(ConstantValues.GST, z);
						}
						
						for (String a : discount) {
							map.add(ConstantValues.DISCOUNT_ARRAY, a);
						}
						for (String b : totalproductprice) {
							map.add(ConstantValues.TOTALPRODUCTPRICE, b);
						}
						map.add(ConstantValues.STATUS, status);
						HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
						System.out.println(request);
						System.out.println(urls);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						// System.out.println(response);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							responseBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(), ResponseBean.class);

						} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
								|| response.getStatusCode() == HttpStatus.BAD_REQUEST
								|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
							throw new BackEndDataException(response.getBody());
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new RestResponseException(resBean.getMessage());
						}
					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return responseBean;
				}
				
				//Get All Source Sales
				public List<SalesTime> getAllDates(String sid) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.SALE_TIME_GET);
					List<SalesTime> dates = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl()+ConstantValues.SLASH_TAG+sid, urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							SalesTime[] ob = (SalesTime[]) JsonToBeanConverter.convert(response.getBody(), SalesTime[].class);
							dates = ArrayToListConverter.convertArrayToList(ob);
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return dates;
				}
				
				//Get All users with invoices info
				public List<Invoices_Info_by_userBean> getAllInfo() {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.FULL_INFO);
					List<Invoices_Info_by_userBean> x = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							Invoices_Info_by_userBean[] ob = (Invoices_Info_by_userBean[]) JsonToBeanConverter.convert(response.getBody(), Invoices_Info_by_userBean[].class);
							x = ArrayToListConverter.convertArrayToList(ob);
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return x;
				}
				
				//Get  users  invoices info
				public Invoices_Info_by_userBean getAllInfoByUser(String user, String year, String month) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.FULL_INFO);
					Invoices_Info_by_userBean x = null;
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						System.out.println("Request------"+request);
						response = restTemplate.exchange(urls.getUrl()+ConstantValues.SLASH_TAG+user+ConstantValues.SLASH_TAG+year+ConstantValues.SLASH_TAG+month, urls.getMethod(), request, String.class);
						System.out.println("Response------"+response);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							x = (Invoices_Info_by_userBean) JsonToBeanConverter.convert(response.getBody(), Invoices_Info_by_userBean.class);
							
						} else if (response.getStatusCode() == HttpStatus.OK) {
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return x;
				}
				
				
				// PDF UPLOAD
				public ResponseBean uploadPDF(long inid,	MultipartFile img) {
					RestTemplate restTemplate;
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PDFUPLOAD);
					ResponseBean reply = null;
//					ResponseBean responseBean = null;
					headers = new HttpHeaders();
					

						if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
							restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
							headers.set(key.getName(), key.getValue());
							headers.setContentType(MediaType.MULTIPART_FORM_DATA);

							MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
							try {
									map.add(ConstantValues.IMG, new MultipartInputStreamFileResource(
												img.getInputStream(), img.getOriginalFilename()));
							
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
//							
							
							
							HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
							System.out.println("Printing ---------------------"+request);
							response = restTemplate.exchange(urls.getUrl()+ConstantValues.SLASH_TAG+inid, urls.getMethod(), request, String.class);
							System.out.println("Response ---------------------"+response);
							if (response.getStatusCode() == HttpStatus.ACCEPTED) {
								//reply = response.getBody();
								 reply = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
								 ResponseBean.class);
							} else if (response.getStatusCode() == HttpStatus.OK) {
								ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
										ResponseBean.class);
								throw new RestResponseException(resBean.getMessage());
							} else if (response.getStatusCode() == HttpStatus.FAILED_DEPENDENCY
									|| response.getStatusCode() == HttpStatus.BAD_REQUEST
									|| response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
								throw new BackEndDataException(response.getBody());
							}
						} else {
							throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
						}
//					} catch (Exception e) {
//						throw new RestResponseException(e.getMessage());
//					}
						return reply;
					}
				
				//Get PDF Location
				
				public String getImgLoc(long inid) {
					RestTemplate restTemplate;
					headers = new HttpHeaders();
					key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.PDFLOC);
					String reply = "";
					
					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + inid  );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						System.out.println("Printing ---------------------"+request);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						System.out.println("Response ---------------------"+response);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							reply = response.getBody();
							
						}else if(response.getStatusCode() == HttpStatus.OK) {
							
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}

					return reply;

				}
}
