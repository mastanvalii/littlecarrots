package com.lc.sk.auth.config;

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

import com.lc.sk.auth.filters.PreAuthTokenHeaderFilter;
import com.lc.sk.auth.rbeans.KeyBean;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.URLMapping;


/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */

@Configuration
@EnableWebSecurity
@Order(1)
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter { // NO_UCD (unused code)



	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		KeyBean key = new KeyBean("AUTH_API_ACCESS_KEY", "LRtt3I-ARtlos-51TCOi-er1212-1819TE-Tlcr92-01820L-Sa2015");
		PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(key.getName());

		filter.setAuthenticationManager(new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String principal = (String) authentication.getPrincipal();

				if (!key.getValue().equals(principal)) {
					throw new BadCredentialsException(ConstantVariables.BAD_CREDENTIALS);
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
