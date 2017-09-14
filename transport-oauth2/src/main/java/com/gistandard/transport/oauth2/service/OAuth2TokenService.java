package com.gistandard.transport.oauth2.service;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.oauth2.bean.AuthTokenInfo;
import com.gistandard.transport.oauth2.bean.AuthTokenInfoResult;
import com.gistandard.transport.oauth2.bean.CustomRedisTokenStore;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

/**
 * OAuth2Token帮助类
 */
@Component
public class OAuth2TokenService {

    @Value("#{spring.msAppUrl}")
    private String msServiceURI ;

    private String authServerURI ;

    private String passwordGrantToken = "?grant_type=password";

    private String refreshGrantToken = "?grant_type=refresh_token&refresh_token=";

    public static final String DEFAULT_PASSWORD = "NGQxODU0ODcwNWY1ZjVjMzY4MjA0M2Ux";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomRedisTokenStore customRedisTokenStore;


    /**
     * 请求头信息
     * @return
     */
    private static HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    /**
     * 头信息添加权限信息
     * @return
     */
    private static HttpHeaders getHeadersWithClientCredentials(String clientId,String clientSecret){
        String plainClientCredentials=clientId+":"+clientSecret;
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return headers;
    }

    /**
     * 发送POST请求
     * 用户名/密码 获取Token
     * @return
     */
    public AuthTokenInfoResult sendTokenRequest(String username,String clientId,String clientSecret){
        AuthTokenInfoResult resBean = new AuthTokenInfoResult();
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials(clientId,clientSecret));
        ResponseEntity<String> response = restTemplate.exchange(this.getAuthServerURI()+getPasswordGrantToken()
                +"&username="+username+"&password="+ OAuth2TokenService.DEFAULT_PASSWORD, HttpMethod.POST, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            resBean.setData(JSON.parseObject(response.getBody(), AuthTokenInfo.class));
            resBean.setRetCode(1);
        }else{
            resBean.setRetCode(2);
            resBean.setRetMsg("授权失败！");
        }
        return resBean;
    }

    /**
     * 根据refreshToken刷新token
     * @param refreshToken
     * @return
     */
    public AuthTokenInfoResult sendRefreshTokenRequest(String refreshToken,String clientId,String clientSecret){
        AuthTokenInfoResult resBean = new AuthTokenInfoResult();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials(clientId,clientSecret));
        ResponseEntity<String> response = null;
            try{
                response = restTemplate.exchange(getAuthServerURI()+getRefreshGrantToken()+refreshToken, HttpMethod.POST, request, String.class);
            }catch (Exception e){
                resBean.setRetCode(40002);
                resBean.setRetMsg("授权失败！");
                return resBean;
            }

        if(response.getStatusCode() == HttpStatus.OK){
            resBean.setData(JSON.parseObject(response.getBody(),AuthTokenInfo.class));
            resBean.setRetCode(1);
        }else{
            resBean.setRetCode(40002);
            resBean.setRetMsg("授权失败！");
        }
        return resBean;
    }

    /**
     * 客户端 用户名 获取 已经授权 token
     */
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId,String userName){
      return tokenStore.findTokensByClientIdAndUserName(clientId,userName);
    }


    /**
     *
     * 删除token
     */
    public void removeToken(String clientId,String userName){
        Collection<OAuth2AccessToken> oAuth2AccessTokenCollection = this.findTokensByClientIdAndUserName(clientId,userName);
        if(oAuth2AccessTokenCollection != null){
            for(OAuth2AccessToken oAuth2AccessToken : oAuth2AccessTokenCollection){
                customRedisTokenStore.removeAccessToken(oAuth2AccessToken,0);
                customRedisTokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken(),0);
            }
        }
    }

    /**
     *
     * 删除访问token
     */
    public void removeAccessToken(String clientId,String userName){
        Collection<OAuth2AccessToken> oAuth2AccessTokenCollection = this.findTokensByClientIdAndUserName(clientId,userName);
        if(oAuth2AccessTokenCollection != null){
            for(OAuth2AccessToken oAuth2AccessToken : oAuth2AccessTokenCollection){
                customRedisTokenStore.removeAccessToken(oAuth2AccessToken,0);
            }
        }
    }

    /**
     * 获取授权信息
     * @param token
     * @return
     */
    public OAuth2Authentication getAuthentication(String token){
        return  customRedisTokenStore.readAuthentication(token);

    }

    /**
     *  更新授权信息
     * @param authentication
     */
    public void updateAuthentication(OAuth2Authentication authentication){
        customRedisTokenStore.updateAuthentication(authentication);
    }

    public String getMsServiceURI() {
        return msServiceURI;
    }

    public String getAuthServerURI() {
        return getMsServiceURI()+"/oauth/token";
    }

    public String getPasswordGrantToken() {
        return passwordGrantToken;
    }

    public String getRefreshGrantToken() {
        return refreshGrantToken;
    }

    public void setMsServiceURI(String msServiceURI) {
        this.msServiceURI = msServiceURI;
    }

    public TokenStore getTokenStore() {
        return tokenStore;
    }

    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }
}
