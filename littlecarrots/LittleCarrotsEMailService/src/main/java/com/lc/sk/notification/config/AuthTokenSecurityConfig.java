/**
 * 
 */
package com.lc.sk.notification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

import com.lc.sk.notification.bean.KeyBean;
import com.lc.sk.notification.filters.PreAuthTokenHeaderFilter;
import com.lc.sk.notification.util.ConstantValues;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsEMailService
 * 2020
 */
@Configuration
@EnableWebSecurity
//@Order(1)
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	KeyManagementComponent keyComponent;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		KeyBean key = keyComponent.getKey(ConstantValues.EMAIL_API_ACCESS_KEY);
		PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(key.getName());
		filter.setAuthenticationManager(new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String principal = (String) authentication.getPrincipal();

				if (!key.getValue().equals(principal)) {
					throw new BadCredentialsException(ConstantValues.BAD_CREDENTIALS);
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});

		httpSecurity.antMatcher("/**").csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter)
				.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()), filter.getClass())
				.authorizeRequests().anyRequest().authenticated();
	}
}
