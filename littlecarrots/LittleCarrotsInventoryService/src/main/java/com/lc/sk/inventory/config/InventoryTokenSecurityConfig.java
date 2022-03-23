package com.lc.sk.inventory.config;

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

import com.lc.sk.inventory.bean.Key;
import com.lc.sk.inventory.filters.PreInventoryTokenHeaderFilter;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.URLMapping;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */

@Configuration
@EnableWebSecurity
@Order(1)
public class InventoryTokenSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	SecurityKeyInventoryComponent securityKeyInventoryComponent;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		Key key = securityKeyInventoryComponent.getKey(ConstantValues.INVENTORY_KEY_ID_DB);
		//System.err.println("Key:"+key.getName()+" :"+key.getValue());
		PreInventoryTokenHeaderFilter filter = new PreInventoryTokenHeaderFilter(key.getName());

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
