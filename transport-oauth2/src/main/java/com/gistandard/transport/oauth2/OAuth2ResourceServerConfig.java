package com.gistandard.transport.oauth2;

import com.gistandard.transport.oauth2.bean.CustomOAuth2ExceptionRenderer;
import com.gistandard.transport.oauth2.bean.CustomRedisTokenStore;
import com.gistandard.transport.oauth2.config.OAuth2Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *资源服务器
 * 提供 rest_api 访问 token鉴权
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String REST_RESOURCE_ID = "rest_api";

	@Autowired
	private OAuth2Config oAuth2Config;


	
	@Bean
	public TokenStore tokenStore() {
		return new CustomRedisTokenStore(oAuth2Config.getJedisConnectionFactory(),oAuth2Config.getApplicationName());
	}


	@Bean
	protected AuthenticationEntryPoint authenticationEntryPoint() {
		OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
		entryPoint.setExceptionRenderer(new CustomOAuth2ExceptionRenderer());
		return entryPoint;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(REST_RESOURCE_ID).stateless(false);
		resources.authenticationEntryPoint(authenticationEntryPoint());

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		requestMatchers().antMatchers("/**").and()
				.authorizeRequests().antMatchers("/**")
				.access("#oauth2.hasScope('read')");
	}
}
