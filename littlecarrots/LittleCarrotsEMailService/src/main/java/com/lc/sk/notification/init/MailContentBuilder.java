/**
 * 
 */
package com.lc.sk.notification.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */
@Service
public class MailContentBuilder {
	   private TemplateEngine templateEngine;
	   
	    @Autowired
	    public MailContentBuilder(TemplateEngine templateEngine) {
	        this.templateEngine = templateEngine;
	    }
	 
	    public String build(String message) {
	        Context context = new Context();
	        context.setVariable("message", message);
	        return templateEngine.process("mailTemplate", context);
	    }
}
