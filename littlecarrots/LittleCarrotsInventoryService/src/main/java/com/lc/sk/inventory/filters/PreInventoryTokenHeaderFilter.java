package com.lc.sk.inventory.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.lc.sk.inventory.util.ConstantValues;



/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public class PreInventoryTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	private String authHeaderName;
	 
    public PreInventoryTokenHeaderFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }
    
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		 return request.getHeader(this.authHeaderName);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return ConstantValues.NOT_ACCEPTABLE;
	}


}
