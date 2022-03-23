package com.lc.sk.notification;

//import org.apache.catalina.Context;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class LittleCarrotsEMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LittleCarrotsEMailServiceApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				WebMvcConfigurer.super.addCorsMappings(registry);
			//	registry.addMapping("/**").allowedOrigins("https://littlecarrots.com","https://littlecarrots.com:8181","https://littlecarrots.com:8283","https://localhost","https://localhost:8181","https://localhost:8283","https://baby.littlecarrots.com","https://www.baby.littlecarrots.com")
				registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				  .allowedHeaders("X-Auth-Token", "Content-Type").maxAge(3600); 
				
			}
			
		
		};
	}
/*
	   @Bean
	    public ServletWebServerFactory servletContainer() {
	        // Enable SSL Trafic
	        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	            @Override
	            protected void postProcessContext(Context context) {
	                SecurityConstraint securityConstraint = new SecurityConstraint();
	                securityConstraint.setUserConstraint("CONFIDENTIAL");
	                SecurityCollection collection = new SecurityCollection();
	                collection.addPattern("/*");
	                securityConstraint.addCollection(collection);
	                context.addConstraint(securityConstraint);
	            }
	        };

	        // Add HTTP to HTTPS redirect
	       // tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

	        return tomcat;
	    }
	    */
}
