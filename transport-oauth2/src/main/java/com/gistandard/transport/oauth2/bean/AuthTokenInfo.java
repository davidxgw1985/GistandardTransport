package com.gistandard.transport.oauth2.bean;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;

/**
 *访问令牌信息
 */
public class AuthTokenInfo {
	//访问令牌
	private String value;

	//令牌类型 bearer
	private String tokenType;

	//更新令牌
	private Oauth2RefreshToken refreshToken;

	//到期时间
	private String expiration;

	//过期时间 单位秒
	private String expiresIn;

	//是否过期
	private boolean expired;

	private String scope;

	@Override
	public String toString() {
		return "AuthTokenInfo [access_token=" + value + ", token_type=" + tokenType + ", refresh_token="
				+ refreshToken.getValue() +", expiration="+expiration+ ", expires_in=" + expiresIn + ", scope=" + scope + "]";
	}


	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Oauth2RefreshToken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(Oauth2RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * 刷新token
	 */
	public class Oauth2RefreshToken implements OAuth2RefreshToken {

		//到期时间
		private  String expiration;

		//刷新令牌
		private String value;

		@Override
		public String getValue() {
			return value;
		}

		public String getExpiration() {
			return expiration;
		}

		public void setExpiration(String expiration) {
			this.expiration = expiration;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	
}
