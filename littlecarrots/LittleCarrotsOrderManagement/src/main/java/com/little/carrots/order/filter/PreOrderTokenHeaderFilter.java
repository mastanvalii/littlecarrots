package com.little.carrots.order.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.little.carrots.order.util.ConstantValues;

public class PreOrderTokenHeaderFilter  extends AbstractPreAuthenticatedProcessingFilter {

	private String authHeaderName;

	public PreOrderTokenHeaderFilter(String authHeaderName) {
		this.authHeaderName = authHeaderName;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return request.getHeader(authHeaderName);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return ConstantValues.NOT_ACCEPTABLE;
	}
	
	
	
}
