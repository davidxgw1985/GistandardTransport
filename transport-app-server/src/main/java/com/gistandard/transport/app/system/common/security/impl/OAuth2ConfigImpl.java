package com.gistandard.transport.app.system.common.security.impl;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.oauth2.config.OAuth2Config;
import com.gistandard.transport.oauth2.config.bean.ClientBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shenzhijun on 2016/12/15.
 */
@Component("oAuth2Config")
public class OAuth2ConfigImpl  implements OAuth2Config {

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDaoEx;

    @Autowired
    private ComCustomerDao comCustomerDao;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Override
    public String getApplicationName() {
        return AppServerDefine.APPLICATION_NAME;
    }

    @Override
    public SecurityUser<AppLoginInfo> getSecurityUser(String userName) {

        ComAccount user = comAccountDao.queryByAccount(userName);
        if(user == null){
            throw new UsernameNotFoundException("用户"+userName+" 不存在！");
        }

        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(user.getId());

        List<ComAccountRoleRel> comAccountRoleRelList = comAccountRoleRelDaoEx.queryByAccountId(user.getId());


        //登录信息
        AppLoginInfo appLoginInfo = new AppLoginInfo();
        appLoginInfo.setAccountId(user.getId());
        appLoginInfo.setAcctUsername(user.getAcctUsername());

        appLoginInfo.setApplicationName(AppServerDefine.APPLICATION_NAME);
        appLoginInfo.setLoginTime(new Date());
        appLoginInfo.setComAccount(user);
        appLoginInfo.setComUserinfo(comUserinfo);

        appLoginInfo.setComAccountRoleRels(comAccountRoleRelList);


        if(comUserinfo.getCustomerId() != null){
            ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(comUserinfo.getCustomerId());
            appLoginInfo.setComCustomer(comCustomer);
        }

        return  new SecurityUser(Integer.toString(user.getId()),userName ,appLoginInfo);
    }

    @Override
    public List<ClientBean> getClientList() {
        List<ClientBean> clientBeanList = new ArrayList<>();


        ClientBean  androidClient = new ClientBean();
        androidClient.setName("androidApp");
        androidClient.setSecret("secret");
        androidClient.setAccessTokenValiditySeconds(SystemDefine.ACCESS_TOKEN_VALIDITY_SECONDS);
        androidClient.setRefreshTokenValiditySeconds(SystemDefine.REFRESH_TOKEN_VALIDITY_SECONDS);
        clientBeanList.add(androidClient);

        ClientBean  iosClient = new ClientBean();
        iosClient.setName("iOSApp");
        iosClient.setSecret("secret");
        iosClient.setAccessTokenValiditySeconds(SystemDefine.ACCESS_TOKEN_VALIDITY_SECONDS);
        iosClient.setRefreshTokenValiditySeconds(SystemDefine.REFRESH_TOKEN_VALIDITY_SECONDS);
        clientBeanList.add(iosClient);



        return clientBeanList;
    }

    @Override
    public JedisConnectionFactory getJedisConnectionFactory() {
        return jedisConnectionFactory;
    }

    @Override
    public String[] getIgnoredUrl() {

        return new String[]{"/","/swagger/**","/v2/**",
                "/images/**",
                "/script/**",
                "/favicon.ico",
                "/css/**",
                "/valuePlusUpload/**",
                "/validateCode/**",
                "/transportWebService/**",
                "/checkValidateCode/**",
                AppServerDefine.LOGIN_URL + "/login/**",
                AppServerDefine.LOGIN_URL + "/sendTelMessage/**",
                AppServerDefine.VERSION_URL + "/**",
                AppServerDefine.SMS_URL + "/**",
                AppServerDefine.BASEINFO_URL + "/retrievePassword/**",
                AppServerDefine.REGISTER_URL+"/**",
                AppServerDefine.RECOMMEND_REGISTER_URL+"/**",
                AppServerDefine.CHECK_REGISTER_URL+"/**",
                AppServerDefine.API_URL+"/**",
                AppServerDefine.ORDER_URL+"/**/**/**/goShowPdf",
                AppServerDefine.ORDER_URL+"/**/**/**/showPdf"};
    }
}
