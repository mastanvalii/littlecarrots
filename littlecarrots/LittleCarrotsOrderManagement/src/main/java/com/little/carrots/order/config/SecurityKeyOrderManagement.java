package com.little.carrots.order.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.little.carrots.order.bean.Key;

@Component
public class SecurityKeyOrderManagement {

	private ConcurrentHashMap<String, String> data = new ConcurrentHashMap<>();

	public SecurityKeyOrderManagement() {
		super();
		data.put("ORDER_API_ACCESS_KEY", "Li20Il-t920TS-t1212T-Tls5LO-et319E-Rco120-CRar18-15Ar18");
		System.err.println("Access KEY Details");
		System.err.println(data);
	}
	
	@SuppressWarnings("null")
	public Key getKey(String id)
	{
		return new Key(id,data.get(id));	
	}
	
}
