package com.lc.sk.inventory.security.kafka;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {

	public CustomJsonDeserializer() {
		super();
		this.addTrustedPackages("com.kafka.email.bean");
		
	}
}
