package com.little.carrots.order.kafka.util;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {
	public CustomJsonDeserializer() {
		super();
		this.addTrustedPackages("com.little.carrots.order.kafka.beans");
		
	}
}
