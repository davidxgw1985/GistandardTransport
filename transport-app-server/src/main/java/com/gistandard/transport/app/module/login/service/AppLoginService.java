package com.gistandard.transport.app.module.login.service;

import com.gistandard.transport.app.module.login.bean.*;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.oauth2.bean.AuthTokenInfoResult;

import java.security.Principal;

public interface AppLoginService {

    /**
     * 登录信息校验接口
     * @param checkLoginParam 登录信息参数对象
     * @return 登录结果
     */
    CheckLoginResult checkAppLoginInfo(CheckLoginParam checkLoginParam);

    /**
     * 获取站点信息
     * @param appBaseRequest 请求基类信息
     * @return 登录结果
     * @throws MobileStationBizException
     */
    CheckLoginResult getStationInfo(AppBaseRequest appBaseRequest)  throws MobileStationBizException;

    /**
     * 根据登录信息更新企业数据
     * @param appLoginInfo 登录信息
     * @param principal 授权信息
     * @param request 企业id
     * @return
     */
    UpdateSelectCompanyResult updateSelectCompany(AppLoginInfo appLoginInfo, Principal principal,SelectCompanyRequest request);

    AuthTokenInfoResult getToken(TokenRequest tokenRequest);
}
