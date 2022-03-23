package com.lc.sk.notification.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kafka.email.bean.OrderEmailService;

@Service
public class ReceivedOrderBuilder {

	private TemplateEngine templateEngine;
	
	@Autowired
	public ReceivedOrderBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

    public String build(OrderEmailService messageBean) {
        Context context = new Context();
        context.setVariable("orderDate", messageBean.getDateoforder());
        context.setVariable("fullname", messageBean.getFullname());
        context.setVariable("orderId", messageBean.getOrderid());
        context.setVariable("OrderitemsDetails", messageBean.getOrderitems());
        context.setVariable("transactionid",messageBean.getTransactionid());
        context.setVariable("Total", messageBean.getTotal());
        context.setVariable("EstimatedDelivery", messageBean.getDateofdelivery());
        context.setVariable("Address",messageBean.getAddress());
        return templateEngine.process("orderReceived", context);
        
    }
	
}
