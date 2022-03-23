package com.lc.sk.notification.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kafka.email.bean.OrderEmailService;

@Service
public class EmailMarketingContentBuilder {

	 private TemplateEngine templateEngine;
	 
	 @Autowired
	 public EmailMarketingContentBuilder(TemplateEngine templateEngine) {
	        this.templateEngine = templateEngine;
	 }
	 
	 public String build( ) {
	        Context context = new Context();
	        return templateEngine.process("emailmarketing1", context);
	        
	    }
	
}
