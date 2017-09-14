package com.gistandard.transport.oauth2.config;

import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.oauth2.config.bean.ClientBean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.List;

/**
 * Created by shenzhijun on 2016/12/8.
 */
public interface OAuth2Config {

    /**
     * 应用名称如：msApp
     * @return
     */
    String getApplicationName();

    /**
     * 保存用户信息
     * 自定义信息 存放在 info中，类型自定义
     * @param userName 平台CN 开头
     * @return
     */
    SecurityUser getSecurityUser(String userName);

    /**
     * 登录平台 如IOS 、android 指定 过期时间
     * @return
     */
    List<ClientBean> getClientList();

    /**
     * redis 数据源
     * @return
     */
    JedisConnectionFactory getJedisConnectionFactory();

    /**
     * 无需权限校验Url
     * @return
     */
    String[] getIgnoredUrl();
}
