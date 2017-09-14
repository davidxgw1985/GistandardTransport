package com.gistandard.transport.app.module.login.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeRes;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeResult;
import com.gistandard.transport.app.module.login.bean.*;
import com.gistandard.transport.app.module.login.service.AppLoginService;
import com.gistandard.transport.app.module.register.bean.SendSmsResult;
import com.gistandard.transport.app.module.register.bean.SendTelMessageReq;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import com.gistandard.transport.base.entity.service.MobileMoudleRelService;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.oauth2.bean.AuthTokenInfoResult;
import com.gistandard.transport.oauth2.service.OAuth2TokenService;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.logToPsc.service.LogToPscService;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.PscSendSmsVerifyCodeReq;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = AppServerDefine.LOGIN_URL)
@Api(value = "登录模块", tags = "登录模块")
public class AppLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AppLoginController.class);

    @Autowired
    private AppLoginService appLoginService;

    @Autowired
    private MobileMoudleRelService mobileMoudleRelService;

    @Autowired
    private OAuth2TokenService oAuth2TokenService;

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "登录接口-V1.0.1", httpMethod = "POST", response = CheckLoginResult.class,
            produces = "application/json", notes = "根据用户名和密码进行登录校验，用户名和密码都必填")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CheckLoginResult checkLogin(@RequestBody CheckLoginParam checkLoginParam) {
        logger.debug("登录接口 checkLogin param:{}", JSON.toJSONString(checkLoginParam));
        CheckLoginResult checkLoginResult = new CheckLoginResult(checkLoginParam);
        if(null != checkLoginParam && !StringUtil.isEmpty(checkLoginParam.getLoginType()) && 2 == checkLoginParam.getLoginType()){
            if (StringUtil.isEmpty(checkLoginParam.getLoginAccount())
                    || StringUtil.isEmpty(checkLoginParam.getTokenId())
                    || StringUtil.isEmpty(checkLoginParam.getValidateCode())) {
                checkLoginResult.setRetCode(AppServerDefine.FAILURE);
                checkLoginResult.setRetMsg("手机号与验证码不能为空！");
                return checkLoginResult;
            }
        }else{
            if (checkLoginParam == null || StringUtil.isEmpty(checkLoginParam.getLoginAccount()) || StringUtil.isEmpty(checkLoginParam.getLoginPwd())) {
                checkLoginResult.setRetCode(AppServerDefine.FAILURE);
                checkLoginResult.setRetMsg("用户名与密码不能为空！");
                return checkLoginResult;
            }
        }

        checkLoginResult = appLoginService.checkAppLoginInfo(checkLoginParam);
        if (checkLoginResult.getRetCode() == 1) {
            CheckLoginData checkLoginData = checkLoginResult.getData();
            LoginUserInfo loginUserInfo = new LoginUserInfo();
            loginUserInfo.setApplicationName(getRequest().getParameter(AppServerDefine.APP_REQ_NAME_PARAM));
            if (checkLoginData != null && checkLoginData.getComUserinfo() != null) {
                loginUserInfo.setUserId(checkLoginData.getComUserinfo().getId());
                loginUserInfo.setUserName(checkLoginData.getComUserinfo().getRealName());
                MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
                mobileMoudleRel.setAcctUsername(checkLoginData.getComAccount().getAcctUsername());
                List<MobileMoudleRel> mobileMoudleRels = mobileMoudleRelService.queryAllModule(mobileMoudleRel);
                List<MobileMoudleRel> companyMoudleRels = new ArrayList<>();
                List<MobileMoudleRel> userAllModuleRels = new ArrayList<>();
                List<MobileMoudleRel> userModuleRels = new ArrayList<>();
                for (MobileMoudleRel rel : mobileMoudleRels) {
                    if (rel.getCompanyId() != null) {
                        companyMoudleRels.add(rel);
                    } else {
                        userAllModuleRels.add(rel);
                    }
                }
                for (MobileMoudleRel userRel : userAllModuleRels) {
                    userRel.setIsOn(1);
                    for (MobileMoudleRel companyRel : companyMoudleRels) {
                        if (companyRel.getMoudleCode() != null && companyRel.getMoudleCode().equals(userRel.getMoudleCode())) {
                            userRel.setIsOn(0);
                            break;
                        }
                    }
                    userModuleRels.add(userRel);
                }
                List<CompanyInfo> companyInfoList = checkLoginData.getCompanyInfoList();
                for (CompanyInfo companyInfo : companyInfoList) {
                    List<MobileMoudleRel> tempCompanyList = new ArrayList<MobileMoudleRel>();
                    for (MobileMoudleRel moudleRel : companyMoudleRels) {
                        if (moudleRel.getMoudleCode() != null && companyInfo.getCompanyAccountId().intValue() == moudleRel.getCompanyId().intValue()) {
                            tempCompanyList.add(moudleRel);
                        }
                    }
                    companyInfo.setMobileMoudleRels(tempCompanyList);
                }
                checkLoginData.setMobileMoudleRels(userModuleRels);
            }
            loginUserInfo.setLoginTime(new Date());

            //删除已经授权token
            oAuth2TokenService.removeToken(checkLoginParam.getClientId(), checkLoginData.getComAccount().getAcctUsername());

            //请求获取token信息
            AuthTokenInfoResult tokenInfo = oAuth2TokenService.sendTokenRequest(checkLoginData.getComAccount().getAcctUsername(), checkLoginParam.getClientId(), checkLoginParam.getClientSecret());
            checkLoginResult.setRetCode(tokenInfo.getRetCode());
            checkLoginData.setAuthTokenInfo(tokenInfo.getData());
            logger.debug("访问token:{}", tokenInfo.getData());
        }
        return checkLoginResult;
    }

    @ApiOperation(value = "站点工作人员登录获取站点信息-V1.0.1", httpMethod = "POST", response = CheckLoginResult.class,
            produces = "application/json", notes = "登录时，如果是站点工作人员，如果选择以站点身份登录，获取站点身份信息")
    @RequestMapping(value = "/login/getStationInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public CheckLoginResult getStationInfo(@RequestBody AppBaseRequest appBaseRequest) throws Exception {
        return appLoginService.getStationInfo(appBaseRequest);
    }

    @ApiOperation(value = "获取刷新Token-V1.0.1", httpMethod = "POST", response = CheckLoginResult.class,
            produces = "application/json", notes = "用户登录时候获取的刷新Token，来获取新Token。")
    @RequestMapping(value = "/login/getToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AuthTokenInfoResult getToken(@RequestBody TokenRequest tokenRequest) throws Exception {
        return appLoginService.getToken(tokenRequest);
    }

    @ApiOperation(value = "更新Token选择企业信息-V1.0.1", httpMethod = "POST", response = UpdateSelectCompanyResult.class,
            produces = "application/json", notes = "保存用户选择的企业信息")
    @RequestMapping(value = "/updateSelectCompany", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public UpdateSelectCompanyResult updateSelectCompany(@RequestBody SelectCompanyRequest request,
                                             @AuthenticationPrincipal SecurityUser<AppLoginInfo> currentUser,
                                             Principal principal) throws Exception {
        logger.debug("更新Token选择企业信息 updateSelectCompany :{}", JSON.toJSONString(request));
        return appLoginService.updateSelectCompany(currentUser.getInfo(), principal, request);
    }

    /**
     * 点击发送验证码
     *
     * @param sendTelMessageReq
     */
    @ApiOperation(value = "登陆发送手机验证码-V1.0.1", httpMethod = "POST", response = SendSmsResult.class,
            produces = "application/json", notes = "登陆发送手机验证码，手机号不能为空")
    @RequestMapping(value = "/sendTelMessage", method = {RequestMethod.POST})
    @ResponseBody
    public SendSmsResult sendTelMessage(@RequestBody SendTelMessageReq sendTelMessageReq) {
        logger.info("发送验证码请求参数：{}", JSON.toJSONString(sendTelMessageReq));
        SendSmsResult sendSmsResult = new SendSmsResult(sendTelMessageReq);
        if (sendTelMessageReq != null && !StringUtil.isEmpty(sendTelMessageReq.getTelephone())) {
            PscSendSmsVerifyCodeReq sendSmsVerifyCodeReq = new PscSendSmsVerifyCodeReq();
            sendSmsVerifyCodeReq.setSystem("MobileStation");
            sendSmsVerifyCodeReq.setModel(22);
            sendSmsVerifyCodeReq.setReceiveNo(sendTelMessageReq.getTelephone());
            ServiceResult result = accountService.sendSmsVerifyCode(AppServerDefine.buildTransportAuthInfo(),sendSmsVerifyCodeReq);
            logger.info("发送验证码返回结果：{}", JSON.toJSONString(result));
            if (result.isResult()){
                sendSmsResult.setRetCode(SystemDefine.SUCCESS);
                SendSmsVerifyCodeRes res = (SendSmsVerifyCodeRes) result.getData();
                sendSmsResult.setData(res);
            }else {
                sendSmsResult.setRetCode(SystemDefine.FAILURE);
                sendSmsResult.setRetMsg(result.getMsg());
            }
        }else{
            sendSmsResult.setRetCode(AppServerDefine.FAILURE);
            sendSmsResult.setRetMsg("手机号不能为空");
        }
        return sendSmsResult;
    }
}
