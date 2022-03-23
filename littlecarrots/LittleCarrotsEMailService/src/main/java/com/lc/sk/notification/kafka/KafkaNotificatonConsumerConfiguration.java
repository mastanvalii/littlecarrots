package com.lc.sk.notification.kafka;

import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.*;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.email.bean.CommonMail;
import com.kafka.email.bean.OrderEmailService;
import com.lc.sk.notification.util.URLMapping;


@EnableKafka
@Configuration
public class KafkaNotificatonConsumerConfiguration {
	
	/*
	@Bean
	public  ConsumerFactory<String, String> consumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	*/
	
	
	@Bean
	public  ConsumerFactory<String, OrderEmailService> jsonconsumerFactory(){
		 
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new CustomJsonDeserializer<>());
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrderEmailService> kafkaJsonListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,OrderEmailService> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(jsonconsumerFactory());
		return factory;
	}
	
	
	@Bean
	public  ConsumerFactory<String, CommonMail> jsonconsumerFactory1(){
		 
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new CustomJsonDeserializer<>());
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CommonMail> kafkaJsonListenerFactory1(){
		ConcurrentKafkaListenerContainerFactory<String,CommonMail> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(jsonconsumerFactory1());
		return factory;
	}
	
	
	@Bean
	public  ConsumerFactory<String, String> jsonconsumerFactory2(){
		 
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new CustomJsonDeserializer<>());
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,String> kafkaJsonListenerFactory2(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(jsonconsumerFactory2());
		return factory;
	}
}
