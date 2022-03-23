package com.lilttle.carrots.imgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching  
@EnableScheduling
public class LittleCarrotsImagesService2Application {

	public static void main(String[] args) {
		SpringApplication.run(LittleCarrotsImagesService2Application.class, args);
	}
	
	  @Bean public WebMvcConfigurer corsConfigurer() { return new
	  WebMvcConfigurer() {
	  
	  @Override public void addCorsMappings(CorsRegistry registry) {
	  WebMvcConfigurer.super.addCorsMappings(registry);
	 // registry.addMapping("/**").allowedOrigins("https://littlecarrots.com","https://littlecarrots.com:8181","https://localhost","https://localhost:8181","https://baby.littlecarrots.com","https://www.baby.littlecarrots.com","https://192.168.100.19")
	  registry.addMapping("/**").allowedOrigins("*")
	  .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
	  .allowedHeaders("X-Auth-Token", "Content-Type").maxAge(3600);  }
	  
	  
	  }; }
	 

}
