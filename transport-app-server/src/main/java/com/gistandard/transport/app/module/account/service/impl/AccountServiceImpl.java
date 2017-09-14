package com.gistandard.transport.app.module.account.service.impl;

import com.gistandard.transport.app.module.account.bean.GetAccountInfoRes;
import com.gistandard.transport.app.module.account.bean.GetRecommendAccountUidResult;
import com.gistandard.transport.app.module.account.bean.QueryAccountByTelephoneReq;
import com.gistandard.transport.app.module.account.bean.QueryAccountByTelephoneResult;
import com.gistandard.transport.app.module.account.service.AccountService;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.tools.util.EncryptUtil;
import com.gistandard.transport.tools.util.UUIDGenerator;
import com.valueplus.psc.dubbo.service.common.bean.AccountInfo;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by m on 2016/5/16.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private com.valueplus.psc.dubbo.service.login.AccountService accountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public QueryAccountByTelephoneResult queryAccountByTelephone(QueryAccountByTelephoneReq req) {
        QueryAccountByTelephoneResult baseResBean = new QueryAccountByTelephoneResult(req);
        //TODO:授权参数待修改
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
        ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, req.getPhoneNumber());
        if (serviceResult.isResult()) {
            List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();
            baseResBean.setData(accountInfos.get(0).getAcctUsername());
        } else {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(serviceResult.getMsg());
        }
        return baseResBean;
    }

    /**
     * 通过手机号码查询出账户信息
     *
     * @param req
     * @return
     */
    @Override
    public GetAccountInfoRes getAccountInfoByTelephone(QueryAccountByTelephoneReq req) {
        GetAccountInfoRes baseResBean = new GetAccountInfoRes(req);
        //TODO:授权参数待修改
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
        ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, req.getPhoneNumber());
        if (serviceResult.isResult()) {
            List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();
            baseResBean.setAcctUserName(accountInfos.get(0).getAcctUsername());
            baseResBean.setRealName(accountInfos.get(0).getRealName());
        } else {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(serviceResult.getMsg());
        }
        return baseResBean;
    }

    @Override
    public GetRecommendAccountUidResult getRecommendAccountUid(AppBaseRequest appBaseRequest) throws Exception {
        GetRecommendAccountUidResult getRecommendAccountUidResult = new GetRecommendAccountUidResult();
        getRecommendAccountUidResult.setData(appBaseRequest.getAccountId().toString());
        //设置缓存120分钟过期
        redisTemplate.opsForValue().set(getRecommendAccountUidResult.getData(), appBaseRequest.getAccountId(), 120, TimeUnit.MINUTES);
        return getRecommendAccountUidResult;
    }
}
