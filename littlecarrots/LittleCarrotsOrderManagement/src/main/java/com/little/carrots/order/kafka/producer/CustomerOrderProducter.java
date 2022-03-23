package com.little.carrots.order.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.little.carrots.order.kafka.beans.CustomerOrderBean;

@Component
public class CustomerOrderProducter {

	private @Autowired
	KafkaTemplate<String, CustomerOrderBean> customerOrderInfo;
	
	private String topic = "order2";
	
	public String orderAssign(CustomerOrderBean bean) {
		System.err.println("Pushing bean:"+bean);
		customerOrderInfo.send(topic, bean);
		return "Order Information in Kafka Queue";
	}
}
