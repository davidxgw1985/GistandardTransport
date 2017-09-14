package com.gistandard.transport.app.module.register.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.register.bean.*;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.bean.ComCounty;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.register.bean.AccountRegisterBean;
import com.gistandard.transport.register.bean.O2IdRegisterBean;
import com.gistandard.transport.register.bean.OcrResultBean;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.register.service.RegisterNewService;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeResult;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateReq;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateRes;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import com.valueplus.psc.dubbo.service.login.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = AppServerDefine.REGISTER_URL)
@Api(value = "注册模块", tags = "注册模块" )
public class MobileStationRegisterController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MobileStationRegisterController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private RegisterNewService registerNewService;

    @Autowired
    private SmsDubboService smsService;

    /**
     * APP个人帐号注册第一步
     * @param accountRegisterBean
     * @return
     */
    @ApiOperation(value = "APP个人帐号注册第一步-V1.0.1", httpMethod = "POST", response = RegisterStep1Result.class,
            produces = "application/json", notes = "APP个人帐号注册第一步，居住地区、居住城市、手机号码不能为空")
    @RequestMapping(value = "/acctRegisterSave", method = {RequestMethod.POST})
    @ResponseBody
    public RegisterStep1Result acctRegisterSave(@RequestBody CustomerRegStep1 accountRegisterBean) {
        logger.info("APP帐号注册第一步请求信息为：{}", JSON.toJSONString(accountRegisterBean));

        RegisterStep1Result appBaseResult = new RegisterStep1Result(accountRegisterBean);
        Validator validator = new Validator();
        List<ConstraintViolation> ret = validator.validate(accountRegisterBean);
        if (CollectionUtils.isNotEmpty(ret)) {
            logger.info("注册第一步校验错误：{}", ret.toString());
            appBaseResult.setRetMsg(ret.get(0).getMessage());
            appBaseResult.setRetCode(AppServerDefine.FAILURE);
        } else {
            accountRegisterBean.setTelCode(getTelCode(accountRegisterBean.getProvinceAndCity(), accountRegisterBean.getProvinceAndCity_sel()));
            //注册类型默认为用户
            accountRegisterBean.setRegisterType(RegisterDefine.REGISTER_USER_TYPE);
            accountRegisterBean.setAcctType(RegisterDefine.ACCOUNT_TYPE_PERSONAL);

            appBaseResult.setData(accountRegisterBean);
        }
        logger.info("APP帐号注册第一步返回结果：{}", JSON.toJSONString(appBaseResult));
        return appBaseResult;
    }

    /**
     * APP个人帐号注册第二步
     * @param customerRegStep2
     * @return
     */
    @ApiOperation(value = "APP个人帐号注册第二步-V1.0.1", httpMethod = "POST", response = RegisterStep2Result.class,
            produces = "application/json", notes = "APP个人帐号注册第二步，身份证号码、真实姓名、身份证正面照片、身份证反面照片不能为空")
    @RequestMapping(value = "/normalPersonalSave", method = {RequestMethod.POST})
    @ResponseBody
    public RegisterStep2Result normalPersonalSave(@RequestBody CustomerRegStep2 customerRegStep2) {
        logger.info("APP个人帐号注册第二步请求信息为：{}", JSON.toJSONString(customerRegStep2));
        RegisterStep2Result registerStep2Result = new RegisterStep2Result(customerRegStep2);
        //传递参数为
        Validator validator = new Validator();
        List<ConstraintViolation> ret = validator.validate(customerRegStep2);
        if (CollectionUtils.isNotEmpty(ret)) {
            logger.info("APP个人帐号注册第二步校验错误：" + ret.toString());
            registerStep2Result.setRetMsg(ret.get(0).getMessage());
            registerStep2Result.setRetCode(AppServerDefine.FAILURE);
        } else {
            ServiceResult<String> result = accountService.verifyIdentityNoUnique(AppServerDefine.buildTransportAuthInfo(),
                    customerRegStep2.getIdentno());
            if (!result.isResult()) {
                registerStep2Result.setRetMsg("该身份证号已经被注册！");
                registerStep2Result.setRetCode(AppServerDefine.FAILURE);
            }
            registerStep2Result.setData(customerRegStep2);
        }
        logger.info("APP个人帐号注册第二步返回结果：{}", JSON.toJSONString(registerStep2Result));
        return registerStep2Result;
    }


    /**
     * APP个人帐号注册第三步
     * @param customerRegStep3
     * @return
     */
    @ApiOperation(value = "APP个人帐号注册第三步-V1.0.1", httpMethod = "POST", response = RegisterStep3Result.class,
            produces = "application/json", notes = "APP个人帐号注册第三步，密码、帐号后8位不能为空")
    @RequestMapping(value = "/o2IdRegisterSave", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public RegisterStep3Result o2IdRegister(@RequestBody CustomerRegStep3 customerRegStep3) throws Exception {
        logger.info("APP个人帐号注册第三步请求信息为：{}", JSON.toJSONString(customerRegStep3));

        RegisterStep3Result registerStep3Result = new RegisterStep3Result(customerRegStep3);
        //传递参数为
        Validator validator = new Validator();
        List<ConstraintViolation> ret = validator.validate(customerRegStep3);
        if (CollectionUtils.isNotEmpty(ret)) {
            logger.info("APP个人帐号注册第三步校验错误：" + ret.toString());
            registerStep3Result.setRetMsg(ret.get(0).getMessage());
            registerStep3Result.setRetCode(AppServerDefine.FAILURE);
        } else {
            ResultBean resultBean = null;
            AccountRegisterBean accountRegisterBean = new AccountRegisterBean();
            BeanUtils.copyProperties(customerRegStep3, accountRegisterBean); //第一步参数
            String message = null;
            if (accountRegisterBean.getAcctType() != null && RegisterDefine.ACCOUNT_TYPE_PERSONAL.equals(accountRegisterBean.getAcctType())) {
                O2IdRegisterBean o2IdRegisterBean = new O2IdRegisterBean();
                //NormalPersonalRegisterBean personalRegisterBean = new NormalPersonalRegisterBean();
                //BeanUtils.copyProperties(customerRegStep3, personalRegisterBean); //第二步参数
                BeanUtils.copyProperties(customerRegStep3, o2IdRegisterBean); //第三步参数
                o2IdRegisterBean.setAccountId(customerRegStep3.getAccountUuid());
                resultBean = registerNewService.normalPersonalSave(accountRegisterBean, null, o2IdRegisterBean);
            }

            if (resultBean != null) {
                //判断信息由app端进行处理
                if (!resultBean.isState()) {
                    registerStep3Result.setRetCode(AppServerDefine.FAILURE);
                    registerStep3Result.setRetMsg(resultBean.getMessage());
                }
                registerStep3Result.setData(resultBean);
            } else {
                registerStep3Result.setRetMsg(message);
                registerStep3Result.setRetCode(AppServerDefine.FAILURE);
            }
        }
        logger.info("APP个人帐号注册第三步返回结果：{}", JSON.toJSONString(registerStep3Result));
        return registerStep3Result;
    }

    /**
     * 手机app验证码检测
     *
     * @param checkTelephoneReq
     * @return
     */
    @ApiOperation(value = "手机验证码验证-V1.0.1", httpMethod = "POST", response = SmsCheckResult.class,
            produces = "application/json", notes = "手机验证码验证，手机号、验证码不能为空")
    @RequestMapping(value = "/checkTeleValidateCode", method = {RequestMethod.POST})
    @ResponseBody
    private SmsCheckResult checkTeleValidateCode(@RequestBody CheckTelephoneCodeReq checkTelephoneReq) {
        logger.info("app验证码检测请求参数：{}", JSON.toJSONString(checkTelephoneReq));
        SmsValidateReq smsValidateReq = new SmsValidateReq();
        smsValidateReq.setTokenId(checkTelephoneReq.getTokenId());
        smsValidateReq.setVerifyCode(checkTelephoneReq.getValidateCode());
        smsValidateReq.setReceiveNo(checkTelephoneReq.getTelephone());
        smsValidateReq.setReqId(checkTelephoneReq.getReqId());
        SmsValidateRes baseResBean = smsService.smsValidateLimit(smsValidateReq);
        logger.info("app验证码检测：{}", JSON.toJSONString(baseResBean));

        SmsCheckResult smsCheckResult = new SmsCheckResult(checkTelephoneReq);
        smsCheckResult.setData((SmsValidateRes) baseResBean.getData());
        smsCheckResult.setRetCode(baseResBean.getRetCode());
        smsCheckResult.setRetMsg(baseResBean.getRetMsg());
        return smsCheckResult;
    }


    /**
     * 手机号唯一性检测
     * @param checkTelephoneReq
     */
    @ApiOperation(value = "手机号唯一性检测-V1.0.1", httpMethod = "POST", response = TelephoneUniqueResult.class,
            produces = "application/json", notes = "手机号唯一性检测，手机号不能为空")
    @RequestMapping(value = "/checkTelephone", method = {RequestMethod.POST})
    @ResponseBody
    public TelephoneUniqueResult checkTelephone(@RequestBody CheckTelephoneReq checkTelephoneReq) {
        logger.info("手机号唯一性检测请求参数：{}", JSON.toJSONString(checkTelephoneReq));

        TelephoneUniqueResult telephoneUniqueResult = new TelephoneUniqueResult(checkTelephoneReq);
        telephoneUniqueResult.setReqId(checkTelephoneReq.getReqId());

        if (StringUtils.isNotEmpty(checkTelephoneReq.getTelephone())) {
            ServiceResult<String> result = accountService.verifyTelephoneUnique(AppServerDefine.buildTransportAuthInfo(),
                    checkTelephoneReq.getTelephone());
            if (!result.isResult()) {
                telephoneUniqueResult.setRetCode(AppServerDefine.FAILURE);
                telephoneUniqueResult.setRetMsg(result.getMsg());
            }
            telephoneUniqueResult.setData(result.getData());
            logger.info("手机号唯一性检测返回结果：{}", JSON.toJSONString(telephoneUniqueResult));
        }else{
            telephoneUniqueResult.setRetCode(AppServerDefine.FAILURE);
            telephoneUniqueResult.setRetMsg("手机号不能为空");
        }

        return telephoneUniqueResult;
    }

    /**
     * 点击发送验证码
     *
     * @param sendTelMessageReq
     */
    @ApiOperation(value = "发送手机验证码-V1.0.1", httpMethod = "POST", response = SendSmsResult.class,
            produces = "application/json", notes = "发送手机验证码，手机号不能为空")
    @RequestMapping(value = "/sendTelMessage", method = {RequestMethod.POST})
    @ResponseBody
    public SendSmsResult sendTelMessage(@RequestBody SendTelMessageReq sendTelMessageReq) {
        logger.info("发送验证码请求参数：{}", JSON.toJSONString(sendTelMessageReq));

        SendSmsResult sendSmsResult = new SendSmsResult(sendTelMessageReq);
        if (sendTelMessageReq != null && !StringUtil.isEmpty(sendTelMessageReq.getTelephone())) {
            SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
            sendSmsVerifyCodeReq.setSystem("TransportReg");
            sendSmsVerifyCodeReq.setModel(0);
            sendSmsVerifyCodeReq.setReceiveNo(sendTelMessageReq.getTelephone());
            sendSmsVerifyCodeReq.setReqId(sendTelMessageReq.getReqId());

            SendSmsVerifyCodeResult baseResBean = smsService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
            logger.info("发送验证码返回结果：{}", JSON.toJSONString(baseResBean));

            sendSmsResult.setRetCode(baseResBean.getRetCode());
            sendSmsResult.setRetMsg(baseResBean.getRetMsg());
            sendSmsResult.setData(baseResBean.getData());
        }else{
            sendSmsResult.setRetCode(AppServerDefine.FAILURE);
            sendSmsResult.setRetMsg("手机号不能为空");
        }
        return sendSmsResult;
    }

    /**
     * 校验身份证正面照片
     *
     * @param checkIdentityFileReq
     * @throws Exception
     */
    @ApiOperation(value = "校验身份证正面照片-V1.0.1", httpMethod = "POST", response = CheckIdentityFileResult.class,
            produces = "application/json", notes = "校验身份证正面照片，获取身份证号码和姓名")
    @RequestMapping(value = "/checkIdentityPositiveFile", method = {RequestMethod.POST})
    @ResponseBody
    public CheckIdentityFileResult checkIdentityPositiveFile(@RequestBody CheckIdentityFileReq checkIdentityFileReq) throws Exception {
        logger.info("校验身份证正面照片请求参数：{}", JSON.toJSONString(checkIdentityFileReq));

        CheckIdentityFileResult checkIdentityFileResult = new CheckIdentityFileResult(checkIdentityFileReq);

        ResultBean resultBean = registerNewService.ocrRetriveIdentityInfo(checkIdentityFileReq.getIdentityPositiveFileId(), checkIdentityFileReq.getArea());
        logger.info("校验身份证正面照片返回结果：{}", JSON.toJSONString(resultBean));

        if (!resultBean.isState()) {
            checkIdentityFileResult.setRetCode(AppServerDefine.FAILURE);
            checkIdentityFileResult.setRetMsg("身份证正面认证失败");
        } else {
            Map<String, Object> identity = objectToMap(resultBean.getData());
            String identno = identity.get("identno") == null ? null : identity.get("identno").toString();
            String realName = identity.get("realName") == null ? null : identity.get("realName").toString();
            if (StringUtils.isBlank(identno) || StringUtils.isBlank(realName)) {
                checkIdentityFileResult.setRetCode(AppServerDefine.FAILURE);
                checkIdentityFileResult.setRetMsg("身份证正面认证失败");
            }
        }
        checkIdentityFileResult.setData((OcrResultBean) resultBean.getData());
        checkIdentityFileResult.setReqId(checkIdentityFileReq.getReqId());

        return checkIdentityFileResult;
    }

    /**
     * 校验身份证反面照片
     *
     * @param checkIdentityFileReq
     * @throws Exception
     */
    @ApiOperation(value = "校验身份证反面照片-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "校验身份证反面照片")
    @RequestMapping(value = "/checkIdentityNegativeFile", method = {RequestMethod.POST})
    @ResponseBody
    public AppBaseResult checkIdentityNegativeFile(@RequestBody CheckIdentityFileReq checkIdentityFileReq) {
        logger.info("校验身份证反面照片请求参数：{}", JSON.toJSONString(checkIdentityFileReq));
        AppBaseResult appBaseResult = new AppBaseResult(checkIdentityFileReq);
        ResultBean resultBean = registerNewService.ocrVerifyIdentityBack(checkIdentityFileReq.getIdentityNegativeFileId(), checkIdentityFileReq.getArea());

        if (!resultBean.isState()) {
            appBaseResult.setRetCode(AppServerDefine.FAILURE);
            appBaseResult.setRetMsg("身份证反面验证失败");
        }
        logger.info("校验身份证反面照片返回结果：{}", JSON.toJSONString(appBaseResult));
        return appBaseResult;
    }


    /**
     * 文件上传
     *
     * @param request
     * @throws Exception
     */
    @ApiOperation(value = "文件上传-V1.0.1", httpMethod = "POST",
            produces = "application/json", notes = "文件上传,上传成功返回附件ID")
    @RequestMapping(value = "/fileUpload", method = {RequestMethod.POST})
    @ResponseBody
    public void registerFileUpload(MultipartHttpServletRequest request) throws Exception {
        BaseResBean baseResBean = new BaseResBean();
        MultipartFile file = null;
        String fileId = "";
        Map<String, MultipartFile> fileMap = request.getFileMap();
        if (fileMap != null && fileMap.size() > 0) {
            for (String key : fileMap.keySet()) {
                fileId = key;
                file = fileMap.get(key);
            }
        }
        logger.info("文件上传fileId：" + fileId);
        ResultBean resultBean = checkFileInfo(file, fileId);
        if (!resultBean.isState()) {
            //writeJson(resultBean);
            baseResBean.setRetCode(AppServerDefine.FAILURE);
            baseResBean.setRetMsg(resultBean.getMessage());
            baseResBean.setData(resultBean.getData());
        } else {
            resultBean = registerNewService.registerFileUpload(file, fileId);
            if (!resultBean.isState()) {
                baseResBean.setRetCode(AppServerDefine.FAILURE);
                baseResBean.setRetMsg(resultBean.getMessage());
            }
            baseResBean.setData(resultBean);
            //writeJson(resultBean);
        }
        writeJson(baseResBean);
    }

    /**
     * 上传文件校验
     *
     * @param file
     * @param fileId
     * @return
     */
    private ResultBean checkFileInfo(MultipartFile file, String fileId) {
        ResultBean resultBean = new ResultBean();
        if (file == null || file.getSize() == 0) {
            resultBean.setMessage("文件为空");
            return resultBean;
        }
        String fileName = file.getOriginalFilename();
        if (!StringUtils.isEmpty(fileName)) {
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            if (!"jpg".equals(fileType) && !"jpeg".equals(fileType) && !"png".equals(fileType)) {
                resultBean.setMessage("文件格式不正确");
                return resultBean;
            }
        }
        if (fileId.equals(RegisterDefine.PORTRAIT_FILE_ID)) {
            if (file.getSize() > RegisterDefine.PORTRAIT_FILE_SIZE) {
                resultBean.setMessage("文件超过指定大小");
                return resultBean;
            }
        } else {
            if (file.getSize() > RegisterDefine.NORMAL_FILE_SIZE) {
                resultBean.setMessage("文件超过指定大小");
                return resultBean;
            }
        }
        resultBean.setState(true);
        return resultBean;
    }

    /**
     * 选择账号
     *
     * @return
     */
    @ApiOperation(value = "根据所选城市随机生成10个帐号-V1.0.1", httpMethod = "POST",
            produces = "application/json", notes = "根据所选城市随机生成10个帐号,供用户选择，组成O2Id")
    @RequestMapping(value = "/o2IdLayer", method = {RequestMethod.POST})
    @ResponseBody
    public void goO2IdLayer(@RequestBody GoO2IdLayerReq goO2IdLayerReq) {
        logger.info("选择账号请求参数：{}", JSON.toJSONString(goO2IdLayerReq));
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setReqId(goO2IdLayerReq.getReqId());
        ServiceResult serviceResult = accountService.generateAccountList(AppServerDefine.buildTransportAuthInfo(),
                NumberUtils.toInt(goO2IdLayerReq.getProvinceAndCitySel()));
        baseResBean.setData(serviceResult.getData());
        logger.info("选择账号返回参数：{}", JSON.toJSONString(serviceResult));
        writeJson(baseResBean);
    }

    /**
     * 解析省市区，获取区号
     *
     * @param provinceAndCity
     * @param selectCityId
     */
    private String getTelCode(String provinceAndCity, String selectCityId) {
        String TelCode = null;
        if (!StringUtils.isEmpty(provinceAndCity) && !StringUtils.isEmpty(selectCityId)) {
            String[] tempArray = provinceAndCity.split("-");
            if (tempArray != null && tempArray.length > 0) {
                ComCity city = null;
                // 只选择到城市
                Map<String, ComCity> cityMap = comCityService.queryForMap();
                if (tempArray.length == 2) {
                    city = cityMap.get(selectCityId);
                } else if (tempArray.length == 3) {
                    // 选择到区县
                    Map<String, ComCounty> countyMap = comCountyService.queryForMap();
                    ComCounty comCounty = countyMap.get(selectCityId);
                    if (comCounty != null) {
                        city = cityMap.get(comCounty.getCityId());
                    }
                }

                if (city != null && !StringUtils.isEmpty(city.getTelCode())) {
                    TelCode = city.getTelCode();
                }
            }
        }
        if (!StringUtil.isEmpty(TelCode)) {
            int length = TelCode.length();
            StringBuffer prefix = new StringBuffer();
            for (int i = 0; i < 5 - length; i++) {
                prefix.append("0");
            }
            prefix.append(TelCode);
            TelCode = prefix.toString();
        }
        return TelCode;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null)
            return null;
        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }
}