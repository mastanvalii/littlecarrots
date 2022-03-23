/**
 * 
 */
package com.lc.sk.auth.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.lc.sk.auth.util.ConstantVariables;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
public class PreAuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

	
	private String authHeaderName;
	 
    public PreAuthTokenHeaderFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }
    
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		 return request.getHeader(authHeaderName);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return ConstantVariables.NOT_ACCEPTABLE;
	}

}
