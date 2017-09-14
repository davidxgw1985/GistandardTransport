package com.gistandard.transport.app.module.applytobe.service;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.module.applytobe.bean.CheckRoleResult;
import com.gistandard.transport.app.module.applytobe.bean.MobileRoleBean;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.oauth2.SecurityUser;
import org.springframework.web.servlet.ModelAndView;

public interface MobileApplyToBeService {

    /**
     * 判断跳转页面
     * @param appBaseRequest
     * @return
     */
    CheckRoleResult check(AppBaseRequest appBaseRequest);

    /**
     * 获取页面展示文言
     * @param account
     * @return
     */
    AppBaseResult getShowMessage(String account);

    /**
     * 获取已存在的角色
     * @param accountId
     * @return
     */
    MobileRoleBean getRoleName(Integer accountId);

    /**
     * 判断用户角色是否存在审核状态
     * @param
     * @return
     */
    ModelAndView checkPage(SecurityUser<AppLoginInfo> currentUser);
}
