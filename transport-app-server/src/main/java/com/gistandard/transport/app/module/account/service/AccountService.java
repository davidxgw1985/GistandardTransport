package com.gistandard.transport.app.module.account.service;

import com.gistandard.transport.app.module.account.bean.GetAccountInfoRes;
import com.gistandard.transport.app.module.account.bean.GetRecommendAccountUidResult;
import com.gistandard.transport.app.module.account.bean.QueryAccountByTelephoneReq;
import com.gistandard.transport.app.module.account.bean.QueryAccountByTelephoneResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/5/16.
 */
public interface AccountService {
    /**
     * 通过收件人手机号码查询出账户
     *
     * @param req
     */
    QueryAccountByTelephoneResult queryAccountByTelephone(QueryAccountByTelephoneReq req);

    /**
     * 通过手机号码查询出账户信息
     * @param req
     * @return
     */
    GetAccountInfoRes getAccountInfoByTelephone(QueryAccountByTelephoneReq req);

    GetRecommendAccountUidResult getRecommendAccountUid(AppBaseRequest appBaseRequest) throws Exception;
}
