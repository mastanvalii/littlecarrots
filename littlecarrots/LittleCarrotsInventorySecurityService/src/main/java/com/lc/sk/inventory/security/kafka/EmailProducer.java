package com.lc.sk.inventory.security.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.email.bean.CommonMail;
import com.kafka.email.bean.CommonMail1;
import com.kafka.email.bean.OrderEmailService;

@Component
public class EmailProducer {

	private @Autowired
	KafkaTemplate<String,OrderEmailService> template ;
	
	private @Autowired
	KafkaTemplate<String,CommonMail> template1;
	
	private @Autowired
	KafkaTemplate<String,CommonMail1> template2;
	
	private @Autowired
	KafkaTemplate<String, String> template5;
	
	private String topic = "mail";
	private String topic1 = "contest";
	
	private String topic2 = "marketing";
	private String pings = "ping";
	
	public String push(OrderEmailService bean) {
		template.send(topic, bean);
		return "Queued";
	}
	
	public String push1(CommonMail bean) {
		template1.send(topic1, bean);
		return "Contest Mail Queued";
	}
	
	public String push2(CommonMail1 bean) {
		template2.send(topic2, bean);
		return "Contest Mail Queued";
	}
	
	public String pingService(String ping) {
		template5.send(pings,ping);
		return "Sent for Service Active Test";
	}
}
