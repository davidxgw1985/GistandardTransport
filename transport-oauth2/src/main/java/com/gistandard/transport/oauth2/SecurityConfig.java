package com.gistandard.transport.oauth2;


import com.gistandard.transport.oauth2.config.OAuth2Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 安全配置
 *
 */
@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 6)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	@Qualifier("preAuthProvider")
	private AuthenticationProvider preAuthProvider;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private OAuth2Config oAuth2Config;



	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		if(oAuth2Config.getIgnoredUrl() != null){
			web.ignoring().antMatchers(oAuth2Config.getIgnoredUrl());
		}

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.anonymous().disable()
				.authorizeRequests()
				.antMatchers("/oauth/token")
				.permitAll();
		return;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder)
			throws Exception {
		authManagerBuilder.userDetailsService(customUserDetailsService);
		authManagerBuilder.authenticationProvider(authenticationProvider)
				.authenticationProvider(preAuthProvider);
	}
	
}
