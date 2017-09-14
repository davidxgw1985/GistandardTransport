package com.gistandard.transport.oauth2;

import com.gistandard.transport.oauth2.bean.CustomRedisTokenStore;
import com.gistandard.transport.oauth2.config.OAuth2Config;
import com.gistandard.transport.oauth2.config.bean.ClientBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * OAuth2授权服务 配置
 * 授权服务器提供token的获取、刷新
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {




	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;


	@Autowired
	private UserApprovalHandler userApprovalHandler;


	@Autowired
	private OAuth2Config oAuth2Config;

	/**
	 * 设置允许访问的第三方客户端
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();


		/*Assert.checkNonNull(oAuth2Config);
		Assert.checkNonNull(oAuth2Config.getClientList());*/
		for(ClientBean clientBean : oAuth2Config.getClientList()) {
			builder.withClient(clientBean.getName())
					.resourceIds("rest_api")
					.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
					.authorities("ROLE_CLIENT")
					.scopes("read", "write", "trust")
					.secret(clientBean.getSecret())
					.accessTokenValiditySeconds(clientBean.getAccessTokenValiditySeconds())//单位秒 24小时
					.refreshTokenValiditySeconds(clientBean.getRefreshTokenValiditySeconds());//15天
		}
	}

	@Bean
	public TokenStore tokenStore() {
		return new CustomRedisTokenStore(oAuth2Config.getJedisConnectionFactory(),oAuth2Config.getApplicationName());
	}


	@Bean
	protected AuthenticationEntryPoint authenticationEntryPoint() {
		OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
		return entryPoint;
	}




	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//修改默认请求路径 /oauth/token (令牌端) /msApp/oauth/authorize(授权端)
		// endpoints.pathMapping("/oauth/token","/msApp/oauth/token");

		endpoints.pathMapping("/oauth/authorize","/transport/oauth/authorize")
				 .pathMapping("/oauth/check_token","/transport/oauth/check_token");
		endpoints.tokenStore(tokenStore())
				.userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager)
		 		.reuseRefreshTokens(false);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
		oauthServer.authenticationEntryPoint(authenticationEntryPoint());
	}

}
