package com.lc.sk.inventory.security.kafka;

import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kafka.email.bean.CommonMail;
import com.kafka.email.bean.CommonMail1;
import com.kafka.email.bean.OrderEmailService;
import com.kafka.email.bean.OrderedData;
import com.lc.sk.inventory.security.util.URLMapping;

@Configuration
public class KafkaNotificationProducerConfiguration {

	@Bean
	public ProducerFactory<String, OrderEmailService> producerFactory(){
		
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaProducerFactory<>(configs);
	}
	

	@Bean
	public ProducerFactory<String, CommonMail> producerFactory1(){
		
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public ProducerFactory<String, OrderedData> producerFactory2(){
		
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	
	@Bean
	public ProducerFactory<String, CommonMail1> producerFactory3(){
		
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	
	@Bean
	public KafkaTemplate<String,OrderEmailService> kafkaTemplate1(){
		return new KafkaTemplate<>(producerFactory());
	}
	
	@Bean
	public KafkaTemplate<String,CommonMail> kafkaTemplate2(){
		return new KafkaTemplate<>(producerFactory1());
	}
	
	@Bean
	public KafkaTemplate<String,OrderedData> kafkaTemplate3(){
		return new KafkaTemplate<>(producerFactory2());
	}
	
	@Bean
	public KafkaTemplate<String,CommonMail1> kafkaTemplate4(){
		return new KafkaTemplate<>(producerFactory3());
	}
	
	
	
	@Bean
	public ProducerFactory<String, String> producerFactory5(){
		
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URLMapping.KAFKA_SERVER_URL);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,String> kafkaTemplate5(){
		return new KafkaTemplate<>(producerFactory5());
	}

}
