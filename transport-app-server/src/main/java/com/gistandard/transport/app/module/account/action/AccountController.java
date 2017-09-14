package com.gistandard.transport.app.module.account.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.account.bean.GetAccountInfoRes;
import com.gistandard.transport.app.module.account.bean.GetRecommendAccountUidResult;
import com.gistandard.transport.app.module.account.bean.QueryAccountByTelephoneReq;
import com.gistandard.transport.app.module.account.bean.QueryAccountByTelephoneResult;
import com.gistandard.transport.app.module.account.service.AccountService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by m on 2016/5/16.
 */
@Controller
@RequestMapping(value = AppServerDefine.ACCOUNT_URL)
@Api(value = "帐号相关接口模块", tags = "帐号相关接口模块")
public class AccountController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/queryAccountByTelephone", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据手机号码查询帐号信息-V1.0.1", httpMethod = "POST", response = QueryAccountByTelephoneResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryAccountByTelephoneResult queryAccountByTelephone(@RequestBody QueryAccountByTelephoneReq req) {
        logger.info("queryAccountByTelephone jsonStr={}", req.toString());
        return  accountService.queryAccountByTelephone(req);
    }

    @RequestMapping(value = "/getAccountInfoByTelephone", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过手机号码查询出账户信息-V1.0.1", httpMethod = "POST", response = GetAccountInfoRes.class,
            produces = "application/json")
    @ResponseBody
    public GetAccountInfoRes getAccountInfoByTelephone(@RequestBody QueryAccountByTelephoneReq req) {
        logger.info("getAccountInfoByTelephone jsonStr={}", req.toString());
        return  accountService.getAccountInfoByTelephone(req);
    }

    @RequestMapping(value = "/getRecommendUid", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据登录帐号Id获取推广Uid-V1.0.1", httpMethod = "POST", response = GetRecommendAccountUidResult.class,
            produces = "application/json")
    @ResponseBody
    public GetRecommendAccountUidResult getRecommendAccountUid(@RequestBody AppBaseRequest req) throws Exception{
        return  accountService.getRecommendAccountUid(req);
    }
}
