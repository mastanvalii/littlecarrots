/**
 * 
 */
package com.lc.sk.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.notification.bean.Acknowledgement;
import com.lc.sk.notification.bean.MessageBean;
import com.lc.sk.notification.config.MailNotificationService;
import com.lc.sk.notification.util.ConstantValues;
import com.lc.sk.notification.util.URLMapping;



/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */
@RestController
@CrossOrigin
@RequestMapping(value = URLMapping.ROOT_URL_PATH+URLMapping.VERSION_NUMBER)
public class LittleCarrotsMailService {

	@Autowired
	private MailNotificationService service;
	
	
	@PostMapping(URLMapping.SEND_SIMPLE_MAIL)
	@ResponseBody
	public ResponseEntity<Acknowledgement> sendMail(@RequestBody MessageBean messageBean)
	{
		boolean flag = service.sendMimeMail(messageBean);		
		Acknowledgement ack = null;
		if(flag)
		{
			ack = new Acknowledgement(ConstantValues.SUCCESS_MAIL_MESSAGE,2000,System.currentTimeMillis());
			return new ResponseEntity<Acknowledgement>(ack, HttpStatus.ACCEPTED);
		}
		else
		{
			ack = new Acknowledgement(ConstantValues.FAIL_MAIL_MESSAGE,4000,System.currentTimeMillis());
			return new ResponseEntity<Acknowledgement>(ack, HttpStatus.OK);
		}		
	}
	
	@PostMapping(URLMapping.SEND_SIMPLE_MAIL1)
	@ResponseBody
	public ResponseEntity<Acknowledgement> sendMimeMail(@RequestBody MessageBean messageBean) 
	{
		boolean flag = service.sendMimeMail(messageBean);		
		Acknowledgement ack = null;
		if(flag)
		{
			ack = new Acknowledgement(ConstantValues.SUCCESS_MAIL_MESSAGE,2000,System.currentTimeMillis());
			return new ResponseEntity<Acknowledgement>(ack, HttpStatus.ACCEPTED);
		}
		else
		{
			ack = new Acknowledgement(ConstantValues.FAIL_MAIL_MESSAGE,4000,System.currentTimeMillis());
			return new ResponseEntity<Acknowledgement>(ack, HttpStatus.OK);
		}		
	}
}
