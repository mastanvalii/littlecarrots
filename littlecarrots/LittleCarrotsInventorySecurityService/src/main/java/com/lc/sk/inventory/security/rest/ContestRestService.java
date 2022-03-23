package com.lc.sk.inventory.security.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.ResponseBean;
import com.lc.sk.inventory.security.beans.UrlBean;
import com.lc.sk.inventory.security.dao.HeaderKeyManagement;
import com.lc.sk.inventory.security.dao.ServiceUrlsDao;
import com.lc.sk.inventory.security.exceptions.subexceptions.BackEndDataException;
import com.lc.sk.inventory.security.exceptions.subexceptions.DataNotFoundException;
import com.lc.sk.inventory.security.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestResponseException;
import com.lc.sk.inventory.security.exceptions.subexceptions.RestURLReaderException;
import com.lc.sk.inventory.security.factory.ServiceHttpRequestFactory;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.JsonToBeanConverter;
import com.lc.sk.inventory.security.util.MultipartInputStreamFileResource;
import com.lc.sk.inventory.security.util.NextServiceURLMapping;
import com.lc.sk.inventory.security.util.UrlDetails;

@Component
public class ContestRestService {

	@Autowired
	private HeaderKeyManagement headerKeyManagement;

	@Autowired
	private ServiceUrlsDao serviceUrlsDao;

	@Autowired
	private ServiceHttpRequestFactory requestFacotry;
//	File folder = new File("E:/LITTLECARROTS/DEV/Services/GIT/lil_invetory_services/LittleCarrotsAuthenticationService/CONTENT");
// TODO Remove unused code found by UCDetector
// 	File folder=new File("CONTEST");
	private UrlBean urls;
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private Key key;
	private ResponseEntity<String> response;
// TODO Remove unused code found by UCDetector
// 	ObjectMapper jsondata = new ObjectMapper();

	// POST 
		public ResponseBean inserContestInformation(String emailid,long phone, String instaid,String childname,String age, boolean tc, 
				MultipartFile[] images,String contestmonthyear) {
			RestTemplate restTemplate;
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST);
			ResponseBean reply = null;
//			ResponseBean responseBean = null;
			headers = new HttpHeaders();
			

				if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
					restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
					headers.set(key.getName(), key.getValue());
					headers.setContentType(MediaType.MULTIPART_FORM_DATA);

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add(ConstantValues.EMAILID, emailid);
					map.add(ConstantValues.PHONE, phone);
					map.add(ConstantValues.INSTAID, instaid);
					map.add(ConstantValues.CHILDNAME, childname);
					map.add(ConstantValues.AGE, age);
					map.add(ConstantValues.TC, tc);
					/*
					if(!this.folder.exists()) {
						this.folder.mkdir();
					}else {
						System.err.println("CONTENT FOLDER EXIST");
						System.err.println("Path:"+this.folder.getAbsolutePath());
					}
					File subfolder = new File(this.folder.getAbsoluteFile()+"/"+emailid+"");
					if(!subfolder.exists()) {
						subfolder.mkdir();
						System.err.println("Sub Folder["+subfolder.getName()+"] CREATED..");
						System.err.println("Path:"+subfolder.getAbsolutePath());
					}else {
						System.err.println("Sub Folder["+subfolder.getName()+"] FOLDER EXIST");
					}
					
					*/
						try {
							
							for(MultipartFile file: images) {
								map.add(ConstantValues.IMAGES, new MultipartInputStreamFileResource(
													file.getInputStream(), file.getOriginalFilename()));
							}
						//	map.add(ConstantValues.IMAGES, new MultipartInputStreamFileResource(
						//			images.getInputStream(), images.getOriginalFilename()));
						//	map.add(ConstantValues.IMAGES, images);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//					map.add(ConstantValues.IMAGES, images.getOriginalFilename());
				
					map.add(ConstantValues.CONTESTMONTHYEAR, contestmonthyear);
					
					
					HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
					System.out.println(request);
					response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
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
//			} catch (Exception e) {
//				throw new RestResponseException(e.getMessage());
//			}
				return reply;
			}
	
		//Get Page wise
		public String getAllDetailsByPage(int page, int count) {
			RestTemplate restTemplate;
			headers = new HttpHeaders();
			key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
			ResponseEntity<String> response;
			UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET);
			String reply = "";
			// List<GrannyBlog> pages = null;

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());
				urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page + ConstantValues.SLASH_TAG + count);
				HttpEntity<Object> request = new HttpEntity<>(headers);
				response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
				if (response.getStatusCode() == HttpStatus.ACCEPTED) {
					reply = response.getBody();
					
				}
				else if(response.getStatusCode() == HttpStatus.OK) {
					
					ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
							ResponseBean.class);
					throw new DataNotFoundException(resBean.getMessage());
				}

			} else {
				throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
			}
			return reply;

		}
		
		//Get Count 
				public String getPageCount(int page, int count) {
					RestTemplate restTemplate;
					headers = new HttpHeaders();
					key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET1);
					String reply = "";
					// List<GrannyBlog> pages = null;

					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + page + ConstantValues.SLASH_TAG + count);
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							reply = response.getBody();
							
						}
else if(response.getStatusCode() == HttpStatus.OK) {
							
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return reply;

				}
				
				//Get BY Emailid
				
				public String getbyEmailid(String emailid) {
					RestTemplate restTemplate;
					headers = new HttpHeaders();
					key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET2);
					String reply = "";
					// List<GrannyBlog> pages = null;

					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + emailid );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
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
				
//Get BY instaid
				
				public String getbyInstaid(String instaid) {
					RestTemplate restTemplate;
					headers = new HttpHeaders();
					key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET3);
					String reply = "";
					// List<GrannyBlog> pages = null;

					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + instaid );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							reply = response.getBody();
							
						}
else if(response.getStatusCode() == HttpStatus.OK) {
							
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return reply;

				}
				
//Get BY phone
				
				public String getbyphone(String phone) {
					RestTemplate restTemplate;
					headers = new HttpHeaders();
					key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET4);
					String reply = "";
					// List<GrannyBlog> pages = null;

					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + phone );
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							reply = response.getBody();
							
						}
else if(response.getStatusCode() == HttpStatus.OK) {
							
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return reply;

				}
				

				//Get Page wise for contest
				public String getAllDetailsforContest(String contestmonthyear,int page, int count) {
					RestTemplate restTemplate;
					headers = new HttpHeaders();
					key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
					ResponseEntity<String> response;
					UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET_MONTH);
					String reply = "";
					// List<GrannyBlog> pages = null;

					if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());

						restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
						headers.set(key.getName(), key.getValue());
						urls.setUrl(urls.getUrl()+ ConstantValues.SLASH_TAG + contestmonthyear  + ConstantValues.SLASH_TAG + page + ConstantValues.SLASH_TAG + count);
						HttpEntity<Object> request = new HttpEntity<>(headers);
						response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
						if (response.getStatusCode() == HttpStatus.ACCEPTED) {
							reply = response.getBody();
							
						}
						
else if(response.getStatusCode() == HttpStatus.OK) {
							
							ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
									ResponseBean.class);
							throw new DataNotFoundException(resBean.getMessage());
						}

					} else {
						throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
					}
					return reply;

				}
				
				//Get Count contestwise
						public String getPageCountcontestwise(String contestmonthyear,int page, int count) {
							RestTemplate restTemplate;
							headers = new HttpHeaders();
							key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
							ResponseEntity<String> response;
							UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.CONTEST_GET_MONTH1);
							String reply = "";
							// List<GrannyBlog> pages = null;

							if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
								restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
								headers.set(key.getName(), key.getValue());

								restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
								headers.set(key.getName(), key.getValue());
								urls.setUrl(urls.getUrl()+ ConstantValues.SLASH_TAG + contestmonthyear  + ConstantValues.SLASH_TAG + page + ConstantValues.SLASH_TAG + count);
								HttpEntity<Object> request = new HttpEntity<>(headers);
								response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
								if (response.getStatusCode() == HttpStatus.ACCEPTED) {
									reply = response.getBody();
									
								}
								
								else if(response.getStatusCode() == HttpStatus.OK) {
									
									ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
											ResponseBean.class);
									throw new DataNotFoundException(resBean.getMessage());
								}

							} else {
								throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
							}
							return reply;

						}
						
						//Contest Months
						public String getContestNames() {
							RestTemplate restTemplate;
							headers = new HttpHeaders();
							key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
							ResponseEntity<String> response;
							UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.MONTHS);
							String reply = "";
							// List<GrannyBlog> pages = null;

							if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
								restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
								headers.set(key.getName(), key.getValue());

								restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
								headers.set(key.getName(), key.getValue());
								urls.setUrl(urls.getUrl() );
								HttpEntity<Object> request = new HttpEntity<>(headers);
								response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
								if (response.getStatusCode() == HttpStatus.ACCEPTED) {
									reply = response.getBody();
									
								}
								else if(response.getStatusCode() == HttpStatus.OK) {
									
									ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
											ResponseBean.class);
									throw new DataNotFoundException(resBean.getMessage());
								}

							} else {
								throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
							}
							return reply;

						}
						
						
						//Get IMG Location
						
						public String getImgLoc(String emailid, String filename) {
							RestTemplate restTemplate;
							headers = new HttpHeaders();
							key = headerKeyManagement.getKey(ConstantValues.AUTH_HEADER_KEY_DB_ID);
							ResponseEntity<String> response;
							UrlBean urls = serviceUrlsDao.getAccessUrl(UrlDetails.IMGLOC);
							String reply = "";
							
							if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
								restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
								headers.set(key.getName(), key.getValue());

								restTemplate = new RestTemplate(requestFacotry.validateAuthenticationSsl(urls.getTimeout()));
								headers.set(key.getName(), key.getValue());
								urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + emailid + ConstantValues.SLASH_TAG +filename );
								HttpEntity<Object> request = new HttpEntity<>(headers);
								response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
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
//							System.err.println("--------------------------------------------------------");
//							for(String ss:reply) {
//								System.err.println(ss);
//							}
//							System.err.println("--------------------------------------------------------");
							return reply;

						}
						

						
						
}
