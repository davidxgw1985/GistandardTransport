package com.gistandard.transport.oauth2.bean;


import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;


/**
 * 自定义储存
 * Created by shenzhijun on 2016/10/19.
 */
public class CustomRedisTokenStore implements TokenStore {


    private String applicationName;
    private String tokenAccessPrefix ;
    private String tokenRefreshPrefix ;
    private String clientIdPrefix ;
    private String authAccessPrefix ;
    private String clientUsernamePrefix ;
    private String accessToRefreshPrefix  ;
    private String refreshToAccessPrefix ;
    private static final int EXPIRE_TIME = 3 * 60;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
    private RedisTemplate<String, Object> redisTemplate;


    public CustomRedisTokenStore(RedisConnectionFactory connectionFactory,String applicationName) {
        redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.applicationName = applicationName;
        this.setTokenAccessPrefix(getApplicationName()+":oauth2:access:");
        this.setTokenRefreshPrefix( getApplicationName()+":oauth2:refresh:");
        this.setClientIdPrefix(getApplicationName()+":oauth2:client:id:");
        this.setAuthAccessPrefix(getApplicationName()+":oauth2:auth:access:");
        this.setClientUsernamePrefix( getApplicationName()+":oauth2:username:");
        this.setAccessToRefreshPrefix(getApplicationName()+":oauth2:access:refresh:");
        this.setRefreshToAccessPrefix(getApplicationName()+":oauth2:refresh:access:");
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        Object entity = redisTemplate.opsForValue().get(fetchAccessTokenKey(token));
        return entity != null ? ((AccessTokenEntity) entity).authentication : null;
    }

    @Override
    public void storeAccessToken(final OAuth2AccessToken token, final OAuth2Authentication authentication) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
                String tokenKey = fetchAccessTokenKey(token.getValue());
                operations.opsForValue().set(tokenKey, new AccessTokenEntity(token, authentication));
                String authKey = fetchAuthKey(authentication);
                operations.opsForValue().set(authKey, tokenKey);
                if (hasClient(authentication)) {
                    operations.opsForSet().add(fetchClientUsernameKey(authentication), tokenKey);
                }
                String clientIdKey = fetchClintIdKey(authentication);
                operations.opsForSet().add(clientIdKey, tokenKey);
                if (hasRefreshToken(token)) {
                    operations.opsForValue().set(fetchAccessToRefreshKey(token), tokenKey);
                    operations.opsForValue().set(fetchRefreshToAccessKey(token), tokenKey);
                }
                Date expiration = token.getExpiration();
                if (expiration != null && expiration.before(new Date())) {
                    long time = expiration.getTime();
                    operations.expire(tokenKey, time, MILLISECONDS);
                    operations.expire(authKey, time, MILLISECONDS);
                    operations.expire(clientIdKey, time, MILLISECONDS);
                    if (hasClient(authentication)) {
                        operations.expire(fetchClientUsernameKey(authentication), time, MILLISECONDS);
                    }
                    if (hasRefreshToken(token)) {
                        operations.expire(fetchAccessToRefreshKey(token), time, MILLISECONDS);
                        operations.expire(fetchRefreshToAccessKey(token), time, MILLISECONDS);
                    }
                }
                return null;
            }
        });
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        Object entity = redisTemplate.opsForValue().get(fetchAccessTokenKey(tokenValue));
        return entity != null ? ((AccessTokenEntity) entity).token : null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        removeAccessToken(fetchAccessTokenKey(token.getValue()),EXPIRE_TIME);
    }


    public void removeAccessToken(OAuth2AccessToken token,Integer expireTime) {
        removeAccessToken(fetchAccessTokenKey(token.getValue()),expireTime);
    }

    private void removeAccessToken(final String tokenValue, final Integer expireTime) {
        Object obj = redisTemplate.opsForValue().get(tokenValue);
        if (obj == null) {
            return;
        }
        final AccessTokenEntity entity = (AccessTokenEntity) obj;
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
                if(expireTime>0){
                    operations.expire(fetchAccessTokenKey(entity.token), expireTime,TimeUnit.SECONDS);
                    operations.expire(fetchAuthKey(entity.authentication), expireTime,TimeUnit.SECONDS);
                }else{
                    operations.delete(fetchAccessTokenKey(entity.token));
                    operations.delete(fetchAuthKey(entity.authentication));
                }

                if (hasRefreshToken(entity.token)) {
                    if(expireTime>0){
                        operations.expire(fetchAccessToRefreshKey(entity.token), expireTime,TimeUnit.SECONDS);
                        operations.expire(fetchRefreshToAccessKey(entity.token), expireTime,TimeUnit.SECONDS);
                    }else{
                        operations.delete(fetchAccessToRefreshKey(entity.token));
                        operations.delete(fetchRefreshToAccessKey(entity.token));
                    }
                }
                if (hasClient(entity.authentication)) {
                    operations.opsForSet().remove(fetchClientUsernameKey(entity.authentication), fetchAccessTokenKey(entity.token));
                }
                operations.opsForSet().remove(fetchClintIdKey(entity.authentication), fetchAccessTokenKey(entity.token));
                return null /*operations.exec()*/;
            }
        });
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        redisTemplate.opsForValue().set(fetchRefreshTokenKey(refreshToken.getValue()), new RefreshTokenEntity(refreshToken, authentication));
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        Object refreshTokenEntity = redisTemplate.opsForValue().get(fetchRefreshTokenKey(tokenValue));
        return refreshTokenEntity != null ? ((RefreshTokenEntity) refreshTokenEntity).token : null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        Object refreshTokenEntity = redisTemplate.opsForValue().get(fetchRefreshTokenKey(token.getValue()));
        return refreshTokenEntity != null ? ((RefreshTokenEntity) refreshTokenEntity).authentication : null;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        redisTemplate.delete(fetchRefreshTokenKey(token.getValue()));
    }

    public void removeRefreshToken(OAuth2RefreshToken token,Integer expireTime) {
        if(expireTime>0){
            redisTemplate.expire(fetchRefreshTokenKey(token.getValue()),expireTime,TimeUnit.SECONDS);
        }else{
            redisTemplate.delete(fetchRefreshTokenKey(token.getValue()));
        }
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        String refreshToAccessKey = fetchRefreshToAccessKey(refreshToken.getValue());
        Object accessTokenKey = redisTemplate.opsForValue().get(refreshToAccessKey);
        redisTemplate.delete(refreshToAccessKey);
        if (accessTokenKey != null) {
            removeAccessToken((String) accessTokenKey,EXPIRE_TIME);
        }
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authKey = fetchAuthKey(authentication);
        Object tokenKey = redisTemplate.opsForValue().get(authKey);
        if (tokenKey == null) {
            return null;
        }
        Object accessTokenEntity = redisTemplate.opsForValue().get(tokenKey);
        return accessTokenEntity != null ? ((AccessTokenEntity) accessTokenEntity).token : null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        String clientUsernameKey = fetchClientUsernameKey(clientId, userName);
        Set<Object> tokenKeys = redisTemplate.opsForSet().members(clientUsernameKey);
        return readAccessTokensByTokenKeys(tokenKeys);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        String clintIdKey = fetchClintIdKey(clientId);
        Set<Object> tokenKeys = redisTemplate.opsForSet().members(clintIdKey);
        return readAccessTokensByTokenKeys(tokenKeys);
    }

    private Collection<OAuth2AccessToken> readAccessTokensByTokenKeys(Set<Object> tokenKeys) {
        Collection<OAuth2AccessToken> result = new HashSet<OAuth2AccessToken>(tokenKeys.size());
        for (Object tokenKey : tokenKeys) {
            Object obj = redisTemplate.opsForValue().get(tokenKey);
            if (obj != null && ((AccessTokenEntity) obj).token != null) {
                result.add(((AccessTokenEntity) obj).token);
            }
        }
        return result;
    }

    public void setTokenAccessPrefix(String prefix) {
        this.tokenAccessPrefix = prefix;
    }

    public void setTokenRefreshPrefix(String tokenRefreshPrefix) {
        this.tokenRefreshPrefix = tokenRefreshPrefix;
    }

    public void setClientIdPrefix(String prefix) {
        this.clientIdPrefix = prefix;
    }

    public void setAuthAccessPrefix(String prefix) {
        this.authAccessPrefix = prefix;
    }

    public void setClientUsernamePrefix(String prefix) {
        this.clientUsernamePrefix = prefix;
    }

    public void setAccessToRefreshPrefix(String prefix) {
        this.accessToRefreshPrefix = prefix;
    }

    public void setRefreshToAccessPrefix(String prefix) {
        this.refreshToAccessPrefix = prefix;
    }

    private String getApprovalKey(OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication() == null ? "" : authentication.getUserAuthentication().getName();
        return getApprovalKey(authentication.getOAuth2Request().getClientId(), userName);
    }

    private String fetchClintIdKey(OAuth2Authentication authentication) {
        return fetchClintIdKey(authentication.getOAuth2Request().getClientId());
    }

    private String fetchClintIdKey(String clientId) {
        return clientIdPrefix + clientId;
    }

    private String fetchAuthKey(OAuth2Authentication authentication) {
        return authAccessPrefix + authenticationKeyGenerator.extractKey(authentication);
    }

    private String fetchAccessTokenKey(OAuth2AccessToken token) {
        return fetchAccessTokenKey(token.getValue());
    }

    private String fetchAccessTokenKey(String token) {
        return tokenAccessPrefix + digest(token);
    }

    private String fetchRefreshTokenKey(String token) {
        return tokenRefreshPrefix + digest(token);
    }

    private String fetchRefreshToAccessKey(OAuth2AccessToken token) {
        return fetchRefreshToAccessKey(token.getRefreshToken().getValue());
    }

    private String fetchRefreshToAccessKey(String refreshToken) {
        return refreshToAccessPrefix + digest(refreshToken);
    }

    private String fetchAccessToRefreshKey(OAuth2AccessToken token) {
        return fetchAccessToRefreshKey(token.getValue());
    }

    private String fetchAccessToRefreshKey(String accessToken) {
        return accessToRefreshPrefix + digest(accessToken);
    }

    private String fetchClientUsernameKey(OAuth2Authentication authentication) {
        return clientUsernamePrefix + getApprovalKey(authentication);
    }

    private String fetchClientUsernameKey(String clientId, String userName) {
        return clientUsernamePrefix + getApprovalKey(clientId, userName);
    }

    private String getApprovalKey(String clientId, String userName) {
        return clientId + (userName == null ? "" : ":" + userName);
    }

    private boolean hasClient(OAuth2Authentication authentication) {
        return !authentication.isClientOnly();
    }

    private boolean hasRefreshToken(OAuth2AccessToken token) {
        return token.getRefreshToken() != null && token.getRefreshToken().getValue() != null;
    }

    private String digest(String value) {
        if (value == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }

        try {
            byte[] bytes = digest.digest(value.getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    public void updateAuthentication(OAuth2Authentication authentication) {
        /*String key = authenticationKeyGenerator.extractKey(authentication);
        byte[] serializedKey = serializeKey(AUTH_TO_ACCESS + key);
        byte[] bytes = null;
        RedisConnection conn = getConnection();
        try {
            bytes = conn.get(serializedKey);
        } finally {
            conn.close();
        }
        OAuth2AccessToken accessToken = deserializeAccessToken(bytes);
        if (accessToken != null) {
            // Keep the stores consistent (maybe the same user is
            // represented by this authentication but the details have
            // changed)
            storeAccessToken(accessToken, authentication);
        }*/

        String authKey = fetchAuthKey(authentication);
        Object tokenKey = redisTemplate.opsForValue().get(authKey);
        if (tokenKey == null) {
            return ;
        }
        Object accessTokenEntity = redisTemplate.opsForValue().get(tokenKey);


        if(accessTokenEntity != null){
            //(AccessTokenEntity) accessTokenEntity).token
            storeAccessToken(((AccessTokenEntity) accessTokenEntity).token, authentication);
        }

    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    private static class AccessTokenEntity extends AbstractTokenEntity {

        OAuth2AccessToken token;

        public AccessTokenEntity() {
        }

        public AccessTokenEntity(OAuth2AccessToken token, OAuth2Authentication authentication) {
            this.token = token;
            this.authentication = authentication;
        }

        public OAuth2AccessToken getToken() {
            return token;
        }

        public void setToken(OAuth2AccessToken token) {
            this.token = token;
        }
    }

    private static class RefreshTokenEntity extends AbstractTokenEntity {

        OAuth2RefreshToken token;

        public RefreshTokenEntity() {
        }

        public RefreshTokenEntity(OAuth2RefreshToken token, OAuth2Authentication authentication) {
            this.token = token;
            this.authentication = authentication;
        }

        public OAuth2RefreshToken getToken() {
            return token;
        }

        public void setToken(OAuth2RefreshToken token) {
            this.token = token;
        }
    }

    private static abstract class AbstractTokenEntity implements Serializable {

        OAuth2Authentication authentication;

        public AbstractTokenEntity() {
        }

        public OAuth2Authentication getAuthentication() {
            return authentication;
        }

        public void setAuthentication(OAuth2Authentication authentication) {
            this.authentication = authentication;
        }
    }
}
