package com.lc.sk.notification.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kafka.email.bean.CommonMail;
import com.kafka.email.bean.CommonMail1;
import com.kafka.email.bean.OrderEmailService;
import com.lc.sk.notification.init.MessangerInit;

@Service
public class NotificationConsumer {

	private JavaMailSender mailSender;
	
	@Autowired
	private MessangerInit messanger;
	
	@Autowired
	public NotificationConsumer(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	
	
	@KafkaListener(topics="mail", groupId = "group_id",containerFactory = "kafkaJsonListenerFactory")
	public void consume(OrderEmailService bean) {
		System.err.println("Consumer Received Data:"+bean);
		mailSender.send(messanger.getMimeMessageData1(bean));
		mailSender.send(messanger.sendToLittleCarrotsTeam(bean, "management@littlecarrots.com"));
		mailSender.send(messanger.sendToLittleCarrotsTeam(bean, "shaikmastanvali41@littlecarrots.com"));
		
	}
	
	@KafkaListener(topics="contest", groupId = "group_id",containerFactory = "kafkaJsonListenerFactory1")
	public void consume(CommonMail bean) {
		System.err.println("Consumer Received Data:"+bean);
		mailSender.send(messanger.getMimeMessageData2(bean));
	}
	
	@KafkaListener(topics="marketing", groupId = "group_id",containerFactory = "kafkaJsonListenerFactory1")
	public void consume1(CommonMail1 bean) {
		System.err.println("Consumer Received Data:"+bean);
		mailSender.send(messanger.getMimeMessageData3(bean));
	}
	
	
	@KafkaListener(topics="ping", groupId="group_id",containerFactory="kafkaJsonListenerFactory2")
	public void consume2(String value) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		System.out.println(value+" @ "+dtf.format(now));
	}
}
