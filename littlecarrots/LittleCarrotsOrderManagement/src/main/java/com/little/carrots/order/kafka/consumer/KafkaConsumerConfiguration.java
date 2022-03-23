package com.little.carrots.order.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.little.carrots.order.kafka.beans.CustomerOrderBean;
import com.little.carrots.order.kafka.util.CustomJsonDeserializer;
import com.little.carrots.order.util.URLMapping;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

	@Bean
	@ConditionalOnMissingBean(ConsumerFactory.class)
	public ConsumerFactory<String, CustomerOrderBean> jsonconsumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new CustomJsonDeserializer<>());
		
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, CustomerOrderBean> kafkaJsonListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,CustomerOrderBean> factory =  new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(jsonconsumerFactory());
		return factory;
	}
}
