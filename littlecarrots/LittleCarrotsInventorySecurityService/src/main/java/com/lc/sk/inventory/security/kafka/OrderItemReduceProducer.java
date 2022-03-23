package com.lc.sk.inventory.security.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.email.bean.OrderedData;

@Component
public class OrderItemReduceProducer {

	private @Autowired
	KafkaTemplate<String,OrderedData> orderedinformation;
	
	private String topic = "order1";
	
	public String orderPush(OrderedData bean) {
		orderedinformation.send(topic, bean);
		return "Order Information Queued";
	}
}
