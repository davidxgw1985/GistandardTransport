package com.gistandard.transport.app.module.recommendRegister.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.sms.bean.*;
import com.gistandard.transport.app.module.recommendRegister.bean.RecommendInfo;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.register.bean.AccountRegisterBean;
import com.gistandard.transport.register.bean.NormalPersonalRegisterBean;
import com.gistandard.transport.register.bean.O2IdRegisterBean;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.register.service.RegisterNewService;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.system.common.annotation.TokenAnnotation;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.token.service.TokenService;
import com.gistandard.transport.tools.util.EncryptUtil;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.AccountInfo;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = AppServerDefine.RECOMMEND_REGISTER_URL)
@ApiIgnore
public class RecommendRegisterController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RecommendRegisterController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsDubboService smsService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegisterNewService registerNewService;

    private static final String REGISTER_STEP1_SESSION = "REGISTER_STEP1_SESSION";

    private static final String REGISTER_STEP2_SESSION = "REGISTER_STEP2_SESSION";

    private static final String O2ID_LIST = "O2ID_LIST";

    private static final String RECOMMEND_INFO = "RECOMMEND_INFO";

    /**
     * 推荐注册介绍页面
     * @return
     */
    @RequestMapping(value = {"/",""}, method = { RequestMethod.GET })
    public ModelAndView goIntroducePage(RecommendInfo recommendInfo) {
        initSession();
        ModelAndView modelAndView = new ModelAndView();
        getSession().setAttribute(RECOMMEND_INFO, recommendInfo);
        modelAndView.setViewName("module/recommendRegister/register-explain");
        return modelAndView;
    }

    /**
     * 注册协议介绍页面
     * @return
     */
    @RequestMapping(value = {"/agreementPage"}, method = { RequestMethod.GET })
    public ModelAndView goAgreementPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("module/recommendRegister/register-agreement");
        return modelAndView;
    }

    /**
     * 个人注册页面
     * @return
     */
    @RequestMapping(value = "/personRegister", method = { RequestMethod.GET })
    public ModelAndView goPersonRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("module/recommendRegister/register-personal-fill");
        return modelAndView;
    }

    /**
     * 注册第一步
     * @param accountRegisterBean
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/acctRegisterSave", method = { RequestMethod.POST })
    public ModelAndView acctRegisterSave(@Valid AccountRegisterBean accountRegisterBean,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        List<FieldError> errorList  = new ArrayList<>();
        // 校验参数
        if (bindingResult.hasErrors()) {
            errorList = bindingResult.getFieldErrors();
        }
        if (CollectionUtils.isNotEmpty(errorList)) {
            tokenService.newToken(request);
            modelAndView.addObject("accountRegisterBean", accountRegisterBean);
            modelAndView.setViewName("module/recommendRegister/register-personal-fill");
            modelAndView.addObject("errorInfo", errorList.get(0).getDefaultMessage());
            return modelAndView;
        }

        // 验证码校验
        SmsValidateRes validateResult = checkValidateCode(accountRegisterBean.getTelPrefix() + accountRegisterBean.getTelephone(),
                accountRegisterBean.getValidateCode());
        if (validateResult.getRetCode() != SystemDefine.SUCCESS) {
            modelAndView.addObject("accountRegisterBean", accountRegisterBean);
            modelAndView.setViewName("module/recommendRegister/register-personal-fill");
            modelAndView.addObject("errorInfo", validateResult.getRetMsg());
            return modelAndView;
        }

        RecommendInfo recommendInfo = (RecommendInfo)getSession().getAttribute(RECOMMEND_INFO);
        if(recommendInfo != null && StringUtils.isNotEmpty(recommendInfo.getRecommendUid())){
            try {
                Integer accountId = NumberUtils.createInteger(recommendInfo.getRecommendUid());
                //Integer accountId = NumberUtils.createInteger(EncryptUtil.aesDecrypt(recommendInfo.getRecommendUid(), "recommend"));
                accountRegisterBean.setRecommendAccountId(accountId);
                accountRegisterBean.setRecommendFrom(recommendInfo.getRecommendFrom());
            } catch (Exception e) {
                logger.error("encryptStr is:{}, error info:{}", recommendInfo.getRecommendUid(), e.toString());
            }
        }
        accountRegisterBean.setArea("CN");
        accountRegisterBean.setTelPrefix("+86");
        accountRegisterBean.setTelCode(getTelCode(accountRegisterBean.getCity()));
        accountRegisterBean.setAcctType(RegisterDefine.ACCOUNT_TYPE_PERSONAL);
        accountRegisterBean.setRegisterType(RegisterDefine.REGISTER_USER_TYPE);
        AccountRegisterBean sessionAccountRegisterBean = (AccountRegisterBean)getSession().getAttribute(REGISTER_STEP1_SESSION);
        if(sessionAccountRegisterBean != null && !sessionAccountRegisterBean.getProvinceAndCity()
                .equals(accountRegisterBean.getProvinceAndCity())){
            getSession().setAttribute(O2ID_LIST, null);
        }
        getSession().setAttribute(REGISTER_STEP1_SESSION, accountRegisterBean);

        if(getSession().getAttribute(O2ID_LIST) != null){
            modelAndView.addObject("o2IdList", getSession().getAttribute(O2ID_LIST));
        }else{
            ServiceResult serviceResult = accountService.generateAccountList(AppServerDefine.buildTransportAuthInfo(),
                    NumberUtils.toInt(accountRegisterBean.getProvinceAndCity_sel()));
            getSession().setAttribute(O2ID_LIST, serviceResult.getData());
            modelAndView.addObject("o2IdList", serviceResult.getData());
        }
        tokenService.newToken(request);
        logger.info("帐号注册第一步信息为：{}", JSON.toJSONString(accountRegisterBean));
        modelAndView.setViewName("module/recommendRegister/register-personal-sets");
        return modelAndView;
    }

    /**
     * 注册第二步，个人
     * @param personalRegisterBean
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/normalPersonalSave", method = {RequestMethod.POST})
    public ModelAndView normalPersonalSave(NormalPersonalRegisterBean personalRegisterBean,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(!checkRegisterStep1(modelAndView)){
            // 跳转页面到注册页面
            return modelAndView;
        }

        List<FieldError> errorList  = new ArrayList<>();
        // 校验参数
        if (bindingResult.hasErrors()) {
            errorList = bindingResult.getFieldErrors();
        }
        if (CollectionUtils.isNotEmpty(errorList)) {
            tokenService.newToken(request);
            modelAndView.addObject("personalRegister", personalRegisterBean);
            modelAndView.setViewName("module/recommendRegister/register-personal-auth");
            modelAndView.addObject("errorInfo", errorList.get(0).getDefaultMessage());
            return modelAndView;
        }

        logger.info("帐号注册第二步信息为：{}", JSON.toJSONString(personalRegisterBean));
        getSession().setAttribute(REGISTER_STEP2_SESSION, personalRegisterBean);

        tokenService.newToken(request);
        modelAndView.setViewName("module/recommendRegister/register-personal-sets");
        modelAndView.addObject("accountRegisterBean", getSession().getAttribute(REGISTER_STEP1_SESSION));
        return modelAndView;
    }

    /**
     * 注册第三步
     * @param o2IdRegisterBean
     * @param bindingResult
     * @return
     */
    @TokenAnnotation(remove = true)
    @RequestMapping(value = "/o2IdRegisterSave", method = { RequestMethod.POST })
    public ModelAndView o2IdRegister(O2IdRegisterBean o2IdRegisterBean,BindingResult bindingResult) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if (!checkRegisterStep1(modelAndView)){
            // 跳转页面到注册页面
            return modelAndView;
        }

        List<FieldError> errorList  = new ArrayList<>();
        // 校验参数
        if (bindingResult.hasErrors()) {
            errorList = bindingResult.getFieldErrors();
        }
        if (CollectionUtils.isNotEmpty(errorList)) {
            tokenService.newToken(request);
            modelAndView.setViewName("module/recommendRegister/register-personal-sets");
            modelAndView.addObject("accountRegisterBean", getSession().getAttribute(REGISTER_STEP1_SESSION));
            modelAndView.addObject("errorInfo", errorList.get(0).getDefaultMessage());
            return modelAndView;
        }
        logger.info("帐号注册第三步信息为：{}", JSON.toJSONString(o2IdRegisterBean));
        ResultBean resultBean = null;
        AccountRegisterBean accountRegisterBean = (AccountRegisterBean)getSession().getAttribute(REGISTER_STEP1_SESSION);
        resultBean = registerNewService.normalPersonalSave(accountRegisterBean,null,o2IdRegisterBean);

        modelAndView.setViewName("module/recommendRegister/redirect");
        if(resultBean.isState()){
            AccountInfo accountInfo = (AccountInfo)resultBean.getData();
            modelAndView.setViewName("module/recommendRegister/register-success");
            modelAndView.addObject("acctUserName",accountInfo.getAcctUsername());
            logger.info("注册帐号成功：{}", accountInfo.getAcctUsername());
            initSession();
        } else {
            modelAndView.setViewName("module/recommendRegister/register-fail");
        }
        return modelAndView;
    }

    /**
     * 点击发送手机验证码
     * @param telephone
     */
    @RequestMapping(value = "/sendTelMessage", method = { RequestMethod.POST })
    @ResponseBody
    public void sendTelMessage(String telephone) {
        if (StringUtils.isNotEmpty(telephone)) {
            SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
            sendSmsVerifyCodeReq.setSystem("TransportReg");
            sendSmsVerifyCodeReq.setModel(0);
            sendSmsVerifyCodeReq.setReceiveNo(telephone);
            SendSmsVerifyCodeResult sendSmsVerifyCodeResult = smsService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
            SendSmsVerifyCodeRes sendSmsVerifyCodeRes = (SendSmsVerifyCodeRes) sendSmsVerifyCodeResult.getData();
            logger.info("发送短信结果：{}", JSON.toJSONString(sendSmsVerifyCodeRes));
            if(sendSmsVerifyCodeRes != null){
                logger.info("获取短信tokenId：{}", sendSmsVerifyCodeRes.getTokenId());
                getSession().setAttribute(AppServerDefine.SESSION_ATTR_TELMSG_TOKEN_ID, sendSmsVerifyCodeRes.getTokenId());
                logger.info("session里短信tokenId：{}", getSession().getAttribute(AppServerDefine.SESSION_ATTR_TELMSG_TOKEN_ID));
            }
            writeJson(sendSmsVerifyCodeResult);
        }
    }

    /**
     * 短信验证码验证
     * @param telephone
     * @param validateCode
     * @return
     */
    private SmsValidateRes checkValidateCode(String telephone,String validateCode) {
        String tokenId = StringUtil.getObjStr(getSession().getAttribute(AppServerDefine.SESSION_ATTR_TELMSG_TOKEN_ID));
        SmsValidateReq smsValidateReq = new SmsValidateReq();
        smsValidateReq.setTokenId(tokenId);
        smsValidateReq.setVerifyCode(validateCode);
        smsValidateReq.setReceiveNo(telephone);
        return smsService.smsValidateLimit(smsValidateReq);
    }

    /**
     * 解析省市区，获取区号
     * @param selectCityId
     * @param selectCityId
     */
    private String getTelCode(String selectCityId) {
        String TelCode = null;
        if (!StringUtil.isEmpty(selectCityId)) {
            Map<String, ComCity> cityMap = comCityService.queryForMap();
            ComCity city = cityMap.get(selectCityId);
            if(city != null && !StringUtil.isEmpty(city.getTelCode())){
                TelCode = city.getTelCode();
            }
        }
        if(!StringUtil.isEmpty(TelCode)){
            int length = TelCode.length();
            StringBuffer prefix = new StringBuffer();
            for(int i = 0;i < 5 - length;i++){
                prefix.append("0");
            }
            prefix.append(TelCode);
            TelCode = prefix.toString();
        }
        return TelCode;
    }

    /**
     * 判断第一步注册后session里是否有值
     * @return
     */
    private boolean checkRegisterStep1(ModelAndView modelAndView){
        boolean result = true;
        if(getSession().getAttribute(REGISTER_STEP1_SESSION) == null){
            modelAndView.setViewName("registerNew/normalRegFail");
            result = false;
            initSession();
        }
        return result;
    }

    /**
     * 判断第二步注册后session里是否有值
     * @return
     */
    private boolean checkRegisterStep2(ModelAndView modelAndView){
        boolean result = true;
        if(getSession().getAttribute(REGISTER_STEP1_SESSION) == null ||
                getSession().getAttribute(REGISTER_STEP2_SESSION) == null ){
            modelAndView.setViewName("registerNew/normalRegFail");
            result = false;
            initSession();
        }
        return result;
    }

    /**
     * 移除session
     */
    private void initSession(){
        getSession().removeAttribute(REGISTER_STEP1_SESSION);
        getSession().removeAttribute(REGISTER_STEP2_SESSION);
        getSession().removeAttribute(AppServerDefine.SESSION_ATTR_TELMSG_TOKEN_ID);
        getSession().removeAttribute(O2ID_LIST);
        getSession().removeAttribute(RECOMMEND_INFO);
    }
}