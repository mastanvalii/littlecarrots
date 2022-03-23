package com.lc.sk.inventory.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lc.sk.inventory.security.util.URLMapping;

@SpringBootApplication
public class LittleCarrotsInventorySecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LittleCarrotsInventorySecurityServiceApplication.class, args);
	}
	
	
	  @Bean public WebMvcConfigurer corsConfigurer() { return new
	  WebMvcConfigurer() {
	  
	  @Override public void addCorsMappings(CorsRegistry registry) {
	  WebMvcConfigurer.super.addCorsMappings(registry);
	  registry.addMapping(URLMapping.ROOT_PATH+"**").allowedOrigins("*")
	  .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
	  .allowedHeaders("X-Auth-Token", "Content-Type")
	  .maxAge(3600); }
	  
	  
	  }; }
}
