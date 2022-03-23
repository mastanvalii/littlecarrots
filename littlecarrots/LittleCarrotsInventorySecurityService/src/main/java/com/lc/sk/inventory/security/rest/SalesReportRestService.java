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
import com.lc.sk.inventory.security.beans.CalenderBean;
import com.lc.sk.inventory.security.beans.Key;
import com.lc.sk.inventory.security.beans.Razorpay;
import com.lc.sk.inventory.security.beans.ReceiptBean;
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
public class SalesReportRestService {
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
	
	/* insertSalesDetails*/
	public ResponseBean insertSalesDetails(
			String soldfrom,
			String orderid,
			String sellerinvoice,
			String awb,
			String trackingid,
			String state,
			String hsn,
			long tax,
			String taxtype,
			double discount,
			double taxamount,
			double totalamount,
			String paidtype,
			String transactionid,
			String invoicedate,
			String orderdate,
			long skuid[],
			long qty[],
			double netamount[],
			double shippingcharges,
			double shippingtaxamount,
			double shippingtotalamount
					
			) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl("INVOICE_REPORT_POST");
		ResponseBean responseBean = null;

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add("soldfrom",soldfrom+"" );
				map.add("orderid", orderid);
				map.add("sellerinvoice", sellerinvoice);
				map.add("awb", awb);
				map.add("trackingid", trackingid);
				map.add("state", state);
				map.add("hsn", hsn);
				map.add("tax", tax+"");
				map.add("taxtype", taxtype);
				map.add("discount", discount+"");
				map.add("taxamount", taxamount+"");
				map.add("totalamount", totalamount+"");
				map.add("paidtype", paidtype);
				map.add("transactionid", transactionid);
				map.add("invoicedate", invoicedate+"");
				map.add("orderdate", orderdate);
				map.add("shippingcharges", shippingcharges+"");
				map.add("shippingtaxamount", shippingtaxamount+"");
				map.add("shippingtotalamount", shippingtotalamount+"");
				for (long sids : skuid) {
					map.add("skuid[]", sids+"");
				}
				for (long qtys : qty) {
					map.add("qty[]", qtys+"");
				}
				for (double netamt : netamount) {
					map.add("netamount[]", netamt+"");
				}
				
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
		return responseBean;
	}
	
	/* insertSalesDetails*/
	public ResponseBean updateSalesDetails(
			long invoiceid,
			String soldfrom,
			String orderid,
			String sellerinvoice,
			String awb,
			String trackingid,
			String state,
			String hsn,
			long tax,
			String taxtype,
			double discount,
			double taxamount,
			double totalamount,
			String paidtype,
			String transactionid,
	//		String invoicedate,
	//		String orderdate,
			long itemssoldid[],
			long skuid[],
			long qty[],
			double netamount[],
			long invoiceids[],
			double shippingcharges,
			double shippingtaxamount,
			double shippingtotalamount
			) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl("INVOICE_REPORT_PuT");
		System.out.println(urls.toString());
		ResponseBean responseBean = null;

			if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
				restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
				headers.set(key.getName(), key.getValue());

				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add("invoiceid", invoiceid+"");
				map.add("soldfrom",soldfrom+"" );
				map.add("orderid", orderid);
				map.add("sellerinvoice", sellerinvoice);
				map.add("awb", awb);
				map.add("trackingid", trackingid);
				map.add("state", state);
				map.add("hsn", hsn);
				map.add("tax", tax+"");
				map.add("taxtype", taxtype);
				map.add("discount", discount+"");
				map.add("taxamount", taxamount+"");
				map.add("totalamount", totalamount+"");
				map.add("paidtype", paidtype);
				map.add("transactionid", transactionid);
		//		map.add("invoicedate", invoicedate+"");
		//		map.add("orderdate", orderdate);
				map.add("shippingcharges", shippingcharges+"");
				map.add("shippingtaxamount", shippingtaxamount+"");
				map.add("shippingtotalamount", shippingtotalamount+"");
				for (long sids : skuid) {
					map.add("skuid[]", sids+"");
				}
				for (long qtys : qty) {
					map.add("qty[]", qtys+"");
				}
				for (double netamt : netamount) {
					map.add("netamount[]", netamt+"");
				}
				
				for (long itemsold : itemssoldid) {
					map.add("itemssoldid[]", itemsold+"");
				}
				/*
				for (long inviceid : invoiceids) {
					map.add("invoiceids[]", inviceid+"");
				}
				*/
				
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
		return responseBean;
	}
	
	
	public List<ReceiptBean> getAllSoldlist() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl("INVOICE_REPORT_GET");
		List<ReceiptBean> receiptBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			System.out.println("SalesReport");
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				ReceiptBean[] ob = (ReceiptBean[]) JsonToBeanConverter.convert(response.getBody(), ReceiptBean[].class);
				receiptBean = ArrayToListConverter.convertArrayToList(ob);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return receiptBean;
	}
	
	public ReceiptBean getAllSoldlistByInvoiceId(long invoiceid) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl("INVOICE_REPORT_GET");
		ReceiptBean receiptBean = null;
//try {
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + invoiceid);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				receiptBean = (ReceiptBean) JsonToBeanConverter.convert(response.getBody(), ReceiptBean.class);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}

		return receiptBean;
	}
	
	
	public List<CalenderBean> getAllCalenderlist() {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl("INVOICE_REPORT_GET_1");
		List<CalenderBean> receiptBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());

			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			System.out.println("Calender");
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				CalenderBean[] ob = (CalenderBean[]) JsonToBeanConverter.convert(response.getBody(), CalenderBean[].class);
				receiptBean = ArrayToListConverter.convertArrayToList(ob);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return receiptBean;
	}
	
	public List<ReceiptBean> getAllSoldlist1(long month, long year) {
		RestTemplate restTemplate;
		key = headerKeyManagement.getKey(ConstantValues.ORDER_API_ACCESS_KEY);
		ResponseEntity<String> response;
		UrlBean urls = serviceUrlsDao.getAccessUrl("INVOICE_REPORT_GET_1");
		List<ReceiptBean> receiptBean = null;
		if (!urls.getUrl().equals(NextServiceURLMapping.ERROR_URL)) {
			restTemplate = new RestTemplate(requestFacotry.validateInventorySsl(urls.getTimeout()));
			headers.set(key.getName(), key.getValue());
			urls.setUrl(urls.getUrl() + ConstantValues.SLASH_TAG + month + ConstantValues.SLASH_TAG+ year);
			HttpEntity<Object> request = new HttpEntity<>(headers);
			response = restTemplate.exchange(urls.getUrl(), urls.getMethod(), request, String.class);
			System.out.println("SalesReport");
			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				ReceiptBean[] ob = (ReceiptBean[]) JsonToBeanConverter.convert(response.getBody(), ReceiptBean[].class);
				receiptBean = ArrayToListConverter.convertArrayToList(ob);
			} else if (response.getStatusCode() == HttpStatus.OK) {
				ResponseBean resBean = (ResponseBean) JsonToBeanConverter.convert(response.getBody(),
						ResponseBean.class);
				throw new DataNotFoundException(resBean.getMessage());
			}

		} else {
			throw new RestURLReaderException(ConstantValues.ERROR_BEAN_RETURNED);
		}
		return receiptBean;
	}
	
}
