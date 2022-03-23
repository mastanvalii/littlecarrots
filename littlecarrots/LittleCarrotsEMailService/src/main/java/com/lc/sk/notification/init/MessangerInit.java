
package com.lc.sk.notification.init;


import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.kafka.email.bean.CommonMail;
import com.kafka.email.bean.CommonMail1;
import com.kafka.email.bean.OrderEmailService;
import com.lc.sk.notification.bean.MessageBean;
import com.lc.sk.notification.util.ConstantValues;


/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */
@Service
public class MessangerInit {

	private @Autowired
	MailContentBuilder contentBuilder;
	
	private @Autowired
	OrderContentBuilder orderBuilder;
	
	private @Autowired
	ReceivedOrderBuilder receivedOrderBuilder;
	
	private @Autowired
	EmailMarketingContentBuilder emailMarketingBuilder;
	
	public  SimpleMailMessage getMessageData(MessageBean messageBean) 
	{
		SimpleMailMessage mail = new SimpleMailMessage();		
		mail.setFrom(ConstantValues.FROM_MAIL_ADDRESS);
		mail.setTo(messageBean.getToEmail());
		mail.setSubject(messageBean.getSubject());
		String infomation = messageBean.getInformation()+"\n\n\n\n\n\n\n\n"+ConstantValues.TAG_MESSAGE+"\n\n"+ConstantValues.TRADE_MARK;
		mail.setText(infomation);		
		return mail;
		
	}
	public  MimeMessagePreparator  getMimeMessageData(MessageBean messageBean)
	{
		 MimeMessagePreparator messagePreparator = mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		        messageHelper.setFrom(new InternetAddress(ConstantValues.FROM_MAIL_ADDRESS));
		        messageHelper.setTo(messageBean.getToEmail());
		        messageHelper.setSubject(messageBean.getSubject());

			 String content = contentBuilder.build(messageBean.getInformation());
		        messageHelper.setText(content, true);
		       
		    };
		    
		    return messagePreparator;
		    
	}
	
	public  MimeMessagePreparator  getMimeMessageData1(OrderEmailService messageBean)
	{
		 MimeMessagePreparator messagePreparator = mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		        messageHelper.setFrom(new InternetAddress(ConstantValues.FROM_MAIL_ADDRESS));
		        messageHelper.setTo(messageBean.getEmail());
		        messageHelper.setSubject(ConstantValues.ORDER_MAIL_SUBJECT+messageBean.getOrderid()+" has been received");

			 String content = orderBuilder.build(messageBean);
		        messageHelper.setText(content, true);
		       
		    };
		    
		    return messagePreparator;
		    
	}
	
	public  MimeMessagePreparator  sendToLittleCarrotsTeam(OrderEmailService messageBean, String toTeamId)
	{
		 MimeMessagePreparator messagePreparator = mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		        messageHelper.setFrom(new InternetAddress(ConstantValues.FROM_MAIL_ADDRESS));
		   //     messageHelper.setTo(messageBean.getEmail());
		        messageHelper.setTo(toTeamId);
		        messageHelper.setSubject(ConstantValues.ORDER_MAIL_SUBJECT1+messageBean.getOrderid()+" from Mr./Mrs. "+messageBean.getFullname());

			 String content = receivedOrderBuilder.build(messageBean);
		        messageHelper.setText(content, true);
		       
		    };
		    
		    return messagePreparator;
		    
	}
	
	
	public  MimeMessagePreparator  getMimeMessageData2(CommonMail messageBean)
	{
		 MimeMessagePreparator messagePreparator = mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		        messageHelper.setFrom(new InternetAddress(ConstantValues.FROM_MAIL_ADDRESS));
		        messageHelper.setTo(messageBean.getTomail());
		        messageHelper.setSubject(messageBean.getSubject());

		        String content = contentBuilder.build(messageBean.getMessage());
		        messageHelper.setText(content, true);
		       
		    };
		    
		    return messagePreparator;
		    
	}
	
	public  MimeMessagePreparator  getMimeMessageData3(CommonMail1 messageBean)
	{
		 MimeMessagePreparator messagePreparator = mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		        messageHelper.setFrom(new InternetAddress(ConstantValues.FROM_MAIL_ADDRESS));
		        messageHelper.setTo(messageBean.getTomail());
		        messageHelper.setSubject(messageBean.getSubject());

		        String content = emailMarketingBuilder.build();
		        messageHelper.setText(content, true);
		       
		    };
		    
		    return messagePreparator;
		    
	}
}

