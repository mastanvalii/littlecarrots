package com.little.carrots.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.little.carrots.order.bean.Key;
import com.little.carrots.order.filter.PreOrderTokenHeaderFilter;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.URLMapping;

@Configuration
@EnableWebSecurity
@Order(1)
public class OrderTokenSecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	SecurityKeyOrderManagement securityKeyOrderManagement;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		Key key = securityKeyOrderManagement.getKey(ConstantValues.ORDER_KEY_ID_DB);
		//System.err.println("Key:"+key.getName()+" :"+key.getValue());
		PreOrderTokenHeaderFilter filter = new PreOrderTokenHeaderFilter(key.getName());

		filter.setAuthenticationManager(new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String principal = (String) authentication.getPrincipal();
			//	System.err.println("Request:"+principal);
				if (!key.getValue().equals(principal)) {
					throw new BadCredentialsException(ConstantValues.BAD_CREDENTIALS);
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});

		httpSecurity.antMatcher(URLMapping.ROOT_PATH_FOR_VALIDATION).csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter)
				.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()), filter.getClass())
				.authorizeRequests().anyRequest().authenticated();
	}
}
