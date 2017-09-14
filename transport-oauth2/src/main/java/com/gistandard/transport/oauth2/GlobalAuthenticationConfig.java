package com.gistandard.transport.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 *
 */
@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {
    
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

      
    @Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
}
