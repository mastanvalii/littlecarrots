package com.little.carrots.order.kafka.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.little.carrots.order.kafka.beans.CustomerOrderBean;
import com.little.carrots.order.util.URLMapping;

@Configuration
public class KafkaProducerConfiguration {

	@Bean
	public ProducerFactory<String, CustomerOrderBean> customerOrderFactory1(){
		
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,CustomerOrderBean> kafkaTemplateForCustomerOrder(){
		return new KafkaTemplate<>(customerOrderFactory1());
	}
}
