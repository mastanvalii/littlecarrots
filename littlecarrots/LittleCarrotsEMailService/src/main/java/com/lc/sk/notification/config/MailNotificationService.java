/**
 * 
 */
package com.lc.sk.notification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lc.sk.notification.bean.MessageBean;
import com.lc.sk.notification.init.MessangerInit;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */

@Service
public class MailNotificationService {
	private JavaMailSender mailSender;
	
	@Autowired
	private MessangerInit messanger;
	
	@Autowired
	public MailNotificationService(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	
	
	public boolean sendSampleMail(MessageBean messageBean) 
	{
		boolean flag = false;
		try {
				mailSender.send(messanger.getMessageData(messageBean));
				flag = true;
		}catch(MailException e)
		{
			flag =false;
			//e.printStackTrace();
		}
		return flag;
	}
	
	public boolean sendMimeMail(MessageBean messageBean)  
	{
		boolean flag = false;
		try {
				mailSender.send(messanger.getMimeMessageData(messageBean));
				flag = true;
		}catch(MailException e)
		{
			flag =false;
			//e.printStackTrace();
		}
		return flag;
	}
	/*
	 * public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmailAddress());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}
	 */
}
