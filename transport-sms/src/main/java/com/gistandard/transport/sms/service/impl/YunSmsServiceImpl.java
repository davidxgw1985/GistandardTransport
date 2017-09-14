package com.gistandard.transport.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.transport.app.dubbo.sms.bean.*;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.SmsModel;
import com.gistandard.transport.base.entity.bean.SmsVerificationCode;
import com.gistandard.transport.base.entity.dao.SmsVerificationCodeDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.sms.bean.sms.RemidTitleExt;
import com.gistandard.transport.sms.bean.sms.SmsBean;
import com.gistandard.transport.sms.utils.SmsUtil;
import com.gistandard.transport.system.common.service.SmsModelService;
import com.gistandard.transport.tools.util.*;
import com.valueplus.psc.dubbo.service.common.bean.AccountInfo;
import com.valueplus.psc.dubbo.service.common.bean.ServiceAuthBean;
import com.valueplus.psc.dubbo.service.common.bean.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by m on 2016/6/25.
 */
@Service
public class YunSmsServiceImpl implements SmsDubboService {
    private static final Logger logger = LoggerFactory.getLogger(YunSmsServiceImpl.class);

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    @Autowired
    private SmsModelService smsModelService;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private SmsVerificationCodeDao smsVerificationCodeDao;

    @Autowired(required = false)
    private JedisManager jedisManager;

    private final String SMS_TEL = "sms-tel:";

    @Value("#{spring.apikey}")
    private String apikey;

    @Autowired
    private com.valueplus.psc.dubbo.service.login.AccountService accountService;

    //缓存有效期 单位秒
    private static final int CACHE_VAL_TIME_SPAN = 60;

    @Value("#{customerOrderIM.imSendSystemNotifyUrl}")
    private String imSendSystemNotifyUrl;

    private SmsModel smsModelCustom;

    /**
     * 发送短信验证码
     *
     * @param sendSmsVerifyCodeReq
     * @return
     */
    @Override
    public SendSmsVerifyCodeResult sendSmsVerifyCode(SendSmsVerifyCodeReq sendSmsVerifyCodeReq) {
        logger.debug("准备发送短信：{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        SendSmsVerifyCodeResult baseResBean = new SendSmsVerifyCodeResult(sendSmsVerifyCodeReq);
        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getO2id())) {
            ComAccount comAccount = comAccountDaoEx.queryAccountByParam(sendSmsVerifyCodeReq.getO2id());
            if (comAccount != null) {
                sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
            } else {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("O2ID不存在！");
            }
        }

        try {
            long tpl_id = -1;
            //在短消息中发送的文本
            String randowNum = StringUtil.getRandomNum() + "";
            //获取所有的短信模板，再根据系统以及模块来获取对应的模板
            List<SmsModel> modelList = smsModelService.querySmsModelAll();

            int validTime = 0;
            for (SmsModel smsModel : modelList) {
                //取得短信模板
                if (sendSmsVerifyCodeReq.getSystem().equals(smsModel.getSystem()) && sendSmsVerifyCodeReq.getModel().equals(smsModel.getModel())) {
                    if (sendSmsVerifyCodeReq.getReceiveNo().startsWith("+") && !sendSmsVerifyCodeReq.getReceiveNo().startsWith("+86")) {//非国内
                        //设置对应的模板变量值
                        if (smsModel.getSmsHkCode() != null) {
                            tpl_id = smsModel.getSmsHkCode();
                        } else if (smsModel.getSmsCnCode() != null) {
                            tpl_id = smsModel.getSmsCnCode();
                        }
                    } else {
                        //设置对应的模板变量值
                        if (smsModel.getSmsCnCode() != null) {
                            tpl_id = smsModel.getSmsCnCode();
                        } else if (smsModel.getSmsHkCode() != null) {
                            tpl_id = smsModel.getSmsHkCode();
                        }
                    }
                    if (smsModel.getValidTime() != null) {
                        validTime = smsModel.getValidTime();
                    }

                    smsModelCustom = smsModel;
                    break;
                }


            }

            //判断短信模板是否有时间限制，如果有限制，并且当前时间在限制内，则发送相同验证码
            if (validTime > 0) {
                //读取验证码，重新发送
            }
            String tpl_value = "";
            SmsVerificationCode smsVerificationCode = new SmsVerificationCode();
            if (tpl_id < 0) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("短信发送失败！");
                return baseResBean;
            } else {
                if (((sendSmsVerifyCodeReq.getModel() == 17 || sendSmsVerifyCodeReq.getModel() == 8 || sendSmsVerifyCodeReq.getModel() == 10 || sendSmsVerifyCodeReq.getModel() == 11 || sendSmsVerifyCodeReq.getModel() == 12 || sendSmsVerifyCodeReq.getModel() == 13 || sendSmsVerifyCodeReq.getModel() == 15 || sendSmsVerifyCodeReq.getModel() == 16 || sendSmsVerifyCodeReq.getModel() == 18 || sendSmsVerifyCodeReq.getModel() == 19 || sendSmsVerifyCodeReq.getModel() == 20 || sendSmsVerifyCodeReq.getModel() == 21) && "MobileStation".equals(sendSmsVerifyCodeReq.getSystem())) || (sendSmsVerifyCodeReq.getModel() == 11 && "WORKSPACE".equals(sendSmsVerifyCodeReq.getSystem()))) {

                } else {
                    tpl_value = URLEncoder.encode("#code#", ENCODING) + "="
                            + URLEncoder.encode(randowNum, ENCODING);
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getStartAddress())) {
                    tpl_value += "&" + URLEncoder.encode("#sfrom#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getStartAddress(), ENCODING);//地址
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiBookNo())) {
                    if ("".equals(tpl_value)) {
                        tpl_value += URLEncoder.encode("#sno#", ENCODING) + "="
                                + URLEncoder.encode(sendSmsVerifyCodeReq.getBusiBookNo(), ENCODING);//订单号
                    } else {
                        tpl_value += "&" + URLEncoder.encode("#sno#", ENCODING) + "="
                                + URLEncoder.encode(sendSmsVerifyCodeReq.getBusiBookNo(), ENCODING);//订单号
                    }

                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFriendsfName())) {
                    tpl_value += "&" + URLEncoder.encode("#friendsfName#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getFriendsfName(), ENCODING);//好友姓名
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getAmountOfMoney())) {
                    tpl_value += "&" + URLEncoder.encode("#amountOfMoney#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getAmountOfMoney(), ENCODING);//金额
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverName())) {
                    tpl_value += "&" + URLEncoder.encode("#deliverName#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getDeliverName(), ENCODING);//快递员名称

                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverTel())) {
                    tpl_value += "&" + URLEncoder.encode("#deliverTel#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getDeliverTel(), ENCODING);//快递员手机

                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverName())) {
                    tpl_value += "&" + URLEncoder.encode("#driverName#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getDriverName(), ENCODING);//司机名称
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverTel())) {
                    tpl_value += "&" + URLEncoder.encode("#driverTel#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getDriverTel(), ENCODING);//司机电话
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFleetName())) {
                    tpl_value += "&" + URLEncoder.encode("#fleetName#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getFleetName(), ENCODING);//车队名称
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarOriginal())) {
                    tpl_value += "&" + URLEncoder.encode("#carOriginal#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getCarOriginal(), ENCODING);//被替换车
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarReplace())) {
                    tpl_value += "&" + URLEncoder.encode("#carReplace#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getCarReplace(), ENCODING);//替换之后的车
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverNameBefore())) {
                    tpl_value += "&" + URLEncoder.encode("#driverNamebefore#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getDriverNameBefore(), ENCODING);
                }
                if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiTime())) {
                    tpl_value += "&" + URLEncoder.encode("#stime#", ENCODING) + "="
                            + URLEncoder.encode(sendSmsVerifyCodeReq.getBusiTime(), ENCODING);
                }

            }

            if (null != smsModelCustom && smsModelCustom.getRoadWay() == 1) {//IM渠道优先
                //TODO:授权参数待修改
                ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
                serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
                serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
                serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
                ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, sendSmsVerifyCodeReq.getReceiveNo().trim());
                if (serviceResult.isResult()) {
                    List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();

                    try {
                        Map<String, Object> req = new HashMap<>();

                        req.put("sysCode", "SYS_1006");
                        req.put("sysTag", "0001");
                        String[] xx = new String[1];
                        xx[0] = accountInfos.get(0).getAcctUsername();
                        req.put("platAccounts", xx);
                        String[] yy = new String[1];
                        SmsBean smsBean = new SmsBean();
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiBookNo())) {
                            smsBean.setBusiBookNo(sendSmsVerifyCodeReq.getBusiBookNo());
                        }
                        smsBean.setCode(randowNum);
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverName())) {
                            smsBean.setDeliverName(sendSmsVerifyCodeReq.getDeliverName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverTel())) {
                            smsBean.setDeliverTel(sendSmsVerifyCodeReq.getDeliverTel());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getModel())) {
                            smsBean.setModel(sendSmsVerifyCodeReq.getModel());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getSystem())) {
                            smsBean.setSystem(sendSmsVerifyCodeReq.getSystem());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFriendsfName())) {
                            smsBean.setFriendsfName(sendSmsVerifyCodeReq.getFriendsfName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getAmountOfMoney())) {
                            smsBean.setAmountOfMoney(sendSmsVerifyCodeReq.getAmountOfMoney());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFleetName())) {
                            smsBean.setFleetName(sendSmsVerifyCodeReq.getFleetName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverTel())) {
                            smsBean.setDriverTel(sendSmsVerifyCodeReq.getDriverTel());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverName())) {
                            smsBean.setDriverName(sendSmsVerifyCodeReq.getDriverName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarReplace())) {
                            smsBean.setCarReplace(sendSmsVerifyCodeReq.getCarReplace());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarOriginal())) {
                            smsBean.setCarOriginal(sendSmsVerifyCodeReq.getCarOriginal());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverO2id())) {
                            smsBean.setDeliverO2id(sendSmsVerifyCodeReq.getDeliverO2id());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverNameBefore())) {
                            smsBean.setDriverNameBefore(sendSmsVerifyCodeReq.getDriverNameBefore());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiTime())) {
                            smsBean.setBusiTime(sendSmsVerifyCodeReq.getBusiTime());
                        }
                        smsBean.setNotifyTime(DateUtil.getFormatCurrentTime());
                        smsBean.setSmsContent(smsBean.toString());
                        yy[0] = JSONObject.toJSONString(smsBean);
                        req.put("content", yy);
                        req.put("remindCode", "PUSH_MS_000003");
                        RemidTitleExt[] a_rte = new RemidTitleExt[1];
                        a_rte[0] = new RemidTitleExt();
                        a_rte[0].setBody(smsBean.toString());
                        a_rte[0].setTitle("系统");
                        req.put("titleExt", a_rte);
                        req.put("isAPNS", 0);
                        logger.debug("IM req:" + JSONObject.toJSONString(req));
                        String resultStr = HttpClientUtil.jsonPost(imSendSystemNotifyUrl, HeadAuthentication.setIMHead("002"), req);
                        logger.debug("IM result:" + resultStr);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        logger.info("SEND IM MESSAGE ERROR:" + e.getMessage());
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("推送IM消息失败！");
                        return baseResBean;
                    }

                } else {
                    logger.debug("apikey={},tpl_id={},tpl_value={},mobile={}", apikey, tpl_id, tpl_value, sendSmsVerifyCodeReq.getReceiveNo());
                    String result = tplSendSms(smsModelCustom.getApiKey(), tpl_id, tpl_value, sendSmsVerifyCodeReq.getReceiveNo().trim());
                    logger.debug("result={}", result);
                    if (null != result) {
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (null != jsonObject) {
                            Integer code = jsonObject.getInteger("code");
                            if (null != code && code.intValue() != 0) {
                                baseResBean.setRetCode(SystemDefine.FAILURE);
                                String msg = jsonObject.getString("detail");
                                baseResBean.setRetMsg("发送短信失败！" + msg == null ? "" : msg);
                                return baseResBean;
                            } else {
                                logger.debug("短信发送完成，号码为：" + sendSmsVerifyCodeReq.getReceiveNo());
                            }
                        }
                    } else {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("发送短信失败！");
                        return baseResBean;
                    }
                }

            } else if (null != smsModelCustom && smsModelCustom.getRoadWay() == 2) {//IM渠道、短信并行
                //TODO:授权参数待修改
                ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
                serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
                serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
                serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
                ServiceResult serviceResult = accountService.queryAccountByTelephone(serviceAuthBean, sendSmsVerifyCodeReq.getReceiveNo().trim());
                if (serviceResult.isResult()) {
                    List<AccountInfo> accountInfos = (List<AccountInfo>) serviceResult.getData();

                    try {
                        Map<String, Object> req = new HashMap<>();

                        req.put("sysCode", "SYS_1006");
                        req.put("sysTag", "0001");
                        String[] xx = new String[1];
                        xx[0] = accountInfos.get(0).getAcctUsername();
                        req.put("platAccounts", xx);
                        String[] yy = new String[1];
                        SmsBean smsBean = new SmsBean();
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiBookNo())) {
                            smsBean.setBusiBookNo(sendSmsVerifyCodeReq.getBusiBookNo());
                        }
                        smsBean.setCode(randowNum);
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverName())) {
                            smsBean.setDeliverName(sendSmsVerifyCodeReq.getDeliverName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverTel())) {
                            smsBean.setDeliverTel(sendSmsVerifyCodeReq.getDeliverTel());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getModel())) {
                            smsBean.setModel(sendSmsVerifyCodeReq.getModel());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getSystem())) {
                            smsBean.setSystem(sendSmsVerifyCodeReq.getSystem());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFriendsfName())) {
                            smsBean.setFriendsfName(sendSmsVerifyCodeReq.getFriendsfName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getAmountOfMoney())) {
                            smsBean.setAmountOfMoney(sendSmsVerifyCodeReq.getAmountOfMoney());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFleetName())) {
                            smsBean.setFleetName(sendSmsVerifyCodeReq.getFleetName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverTel())) {
                            smsBean.setDriverTel(sendSmsVerifyCodeReq.getDriverTel());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverName())) {
                            smsBean.setDriverName(sendSmsVerifyCodeReq.getDriverName());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarReplace())) {
                            smsBean.setCarReplace(sendSmsVerifyCodeReq.getCarReplace());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarOriginal())) {
                            smsBean.setCarOriginal(sendSmsVerifyCodeReq.getCarOriginal());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverO2id())) {
                            smsBean.setDeliverO2id(sendSmsVerifyCodeReq.getDeliverO2id());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverNameBefore())) {
                            smsBean.setDriverNameBefore(sendSmsVerifyCodeReq.getDriverNameBefore());
                        }
                        if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiTime())) {
                            smsBean.setBusiTime(sendSmsVerifyCodeReq.getBusiTime());
                        }
                        smsBean.setNotifyTime(DateUtil.getFormatCurrentTime());
                        smsBean.setSmsContent(smsBean.toString());
                        yy[0] = JSONObject.toJSONString(smsBean);
                        req.put("content", yy);
                        req.put("remindCode", "PUSH_MS_000003");
                        RemidTitleExt[] a_rte = new RemidTitleExt[1];
                        a_rte[0] = new RemidTitleExt();
                        a_rte[0].setBody(smsBean.toString());
                        a_rte[0].setTitle("系统");
                        req.put("titleExt", a_rte);
                        req.put("isAPNS", 0);
                        logger.debug("IM req:" + JSONObject.toJSONString(req));
                        String resultStr = HttpClientUtil.jsonPost(imSendSystemNotifyUrl, HeadAuthentication.setIMHead("002"), req);
                        logger.debug("IM result:" + resultStr);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        logger.info("SEND IM MESSAGE ERROR:" + e.getMessage());
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("推送IM消息失败！");
                        return baseResBean;
                    }

                }

                logger.debug("apikey={},tpl_id={},tpl_value={},mobile={}", apikey, tpl_id, tpl_value, sendSmsVerifyCodeReq.getReceiveNo());
                String result = tplSendSms(smsModelCustom.getApiKey(), tpl_id, tpl_value, sendSmsVerifyCodeReq.getReceiveNo().trim());
                logger.debug("result={}", result);
                if (null != result) {
                    JSONObject jsonObject = JSON.parseObject(result);
                    if (null != jsonObject) {
                        Integer code = jsonObject.getInteger("code");
                        if (null != code && code.intValue() != 0) {
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            String msg = jsonObject.getString("detail");
                            baseResBean.setRetMsg("发送短信失败！" + msg == null ? "" : msg);
                            return baseResBean;
                        } else {
                            logger.debug("短信发送完成，号码为：" + sendSmsVerifyCodeReq.getReceiveNo());
                        }
                    }
                } else {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("发送短信失败！");
                    return baseResBean;
                }


            } else {//短信渠道
                //模板发送的调用
                logger.debug("apikey={},tpl_id={},tpl_value={},mobile={}", apikey, tpl_id, tpl_value, sendSmsVerifyCodeReq.getReceiveNo());
                String result = tplSendSms(smsModelCustom.getApiKey(), tpl_id, tpl_value, sendSmsVerifyCodeReq.getReceiveNo().trim());
                logger.debug("result={}", result);
                if (null != result) {
                    JSONObject jsonObject = JSON.parseObject(result);
                    if (null != jsonObject) {
                        Integer code = jsonObject.getInteger("code");
                        if (null != code && code.intValue() != 0) {
                            baseResBean.setRetCode(SystemDefine.FAILURE);
                            String msg = jsonObject.getString("detail");
                            baseResBean.setRetMsg("发送短信失败！" + msg == null ? "" : msg);
                            return baseResBean;
                        } else {
                            logger.debug("短信发送完成，号码为：" + sendSmsVerifyCodeReq.getReceiveNo());
                        }
                    }
                } else {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("发送短信失败！");
                    return baseResBean;
                }
            }


            String tokenId = UUID.randomUUID().toString();
            smsVerificationCode.setTokenId(tokenId);
            smsVerificationCode.setCode(randowNum);
            smsVerificationCode.setSystem(sendSmsVerifyCodeReq.getSystem());
            smsVerificationCode.setModel(sendSmsVerifyCodeReq.getModel());
            Date createDate = new Date();
            smsVerificationCode.setCreateDate(createDate);
            smsVerificationCode.setCreateUser(sendSmsVerifyCodeReq.getAccountId());
            smsVerificationCode.setReceiveNo(sendSmsVerifyCodeReq.getReceiveNo().trim());
            if (validTime != 0) {
                //设置有效时间
                Date validDate = new Date(createDate.getTime() + validTime * 1000);
                smsVerificationCode.setValidDate(validDate);
            }
            if (((sendSmsVerifyCodeReq.getModel() == 17 || sendSmsVerifyCodeReq.getModel() == 8 || sendSmsVerifyCodeReq.getModel() == 10 || sendSmsVerifyCodeReq.getModel() == 11 || sendSmsVerifyCodeReq.getModel() == 12 || sendSmsVerifyCodeReq.getModel() == 13 || sendSmsVerifyCodeReq.getModel() == 18 || sendSmsVerifyCodeReq.getModel() == 19 || sendSmsVerifyCodeReq.getModel() == 20 || sendSmsVerifyCodeReq.getModel() == 21) && "MobileStation".equals(sendSmsVerifyCodeReq.getSystem())) || (sendSmsVerifyCodeReq.getModel() == 11 && "WORKSPACE".equals(sendSmsVerifyCodeReq.getSystem()))) {

            } else {
                if ((sendSmsVerifyCodeReq.getModel() == 14 || sendSmsVerifyCodeReq.getModel() == 9 || sendSmsVerifyCodeReq.getModel() == 7) && "MobileStation".equals(sendSmsVerifyCodeReq.getSystem())) {
                    SmsBean smsBean = new SmsBean();
                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiBookNo())) {
                        smsBean.setBusiBookNo(sendSmsVerifyCodeReq.getBusiBookNo());
                        smsVerificationCode.setBusiBookNo(sendSmsVerifyCodeReq.getBusiBookNo());
                    }
                    smsBean.setCode(randowNum);
                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverName())) {
                        smsBean.setDeliverName(sendSmsVerifyCodeReq.getDeliverName());
                        smsVerificationCode.setDeliverName(sendSmsVerifyCodeReq.getDeliverName());
                    }
                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverTel())) {
                        smsBean.setDeliverTel(sendSmsVerifyCodeReq.getDeliverTel());
                        smsVerificationCode.setDeliverTel(sendSmsVerifyCodeReq.getDeliverTel());
                    }
                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getModel())) {
                        smsBean.setModel(sendSmsVerifyCodeReq.getModel());
                    }
                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getSystem())) {
                        smsBean.setSystem(sendSmsVerifyCodeReq.getSystem());
                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFriendsfName())) {
//                        smsBean.setFriendsfName(sendSmsVerifyCodeReq.getFriendsfName());
//                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getAmountOfMoney())) {
//                        smsBean.setAmountOfMoney(sendSmsVerifyCodeReq.getAmountOfMoney());
//                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFleetName())) {
//                        smsBean.setFleetName(sendSmsVerifyCodeReq.getFleetName());
//                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverTel())) {
//                        smsBean.setDriverTel(sendSmsVerifyCodeReq.getDriverTel());
//                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverName())) {
//                        smsBean.setDriverName(sendSmsVerifyCodeReq.getDriverName());
//                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarReplace())) {
//                        smsBean.setCarReplace(sendSmsVerifyCodeReq.getCarReplace());
//                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarOriginal())) {
//                        smsBean.setCarOriginal(sendSmsVerifyCodeReq.getCarOriginal());
//                    }
                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverO2id())) {
                        smsBean.setDeliverO2id(sendSmsVerifyCodeReq.getDeliverO2id());
                        smsVerificationCode.setDeliverO2id(sendSmsVerifyCodeReq.getDeliverO2id());
                    }
//                    if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverNameBefore())) {
//                        smsBean.setDriverNameBefore(sendSmsVerifyCodeReq.getDriverNameBefore());
//                    }

                    smsVerificationCode.setSmsContent(smsBean.toString());
                }
                //插入验证码
                smsVerificationCodeDao.insert(smsVerificationCode);
            }
            SendSmsVerifyCodeRes sendSmsVerifyCodeRes = new SendSmsVerifyCodeRes();
            sendSmsVerifyCodeRes.setTokenId(tokenId);
            baseResBean.setData(sendSmsVerifyCodeRes);
        } catch (Exception e) {
            e.printStackTrace();
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("发送短信失败！");
        }
        return baseResBean;
    }

    /**
     * 校验验证码是否正确
     *
     * @param smsValidateReq
     * @return
     */
    @Override
    public SmsValidateRes smsValidate(SmsValidateReq smsValidateReq) {
        logger.debug("校验验证码是否正确，校验数据：{}", JSON.toJSONString(smsValidateReq));
        SmsValidateRes baseResBean = new SmsValidateRes();
        try {
            SmsVerificationCode smsVerificationCode = smsVerificationCodeDao.selectByPrimaryKey(smsValidateReq.getTokenId());
            if (smsVerificationCode == null || !smsValidateReq.getVerifyCode().equals(smsVerificationCode.getCode())) {
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("验证码错误。");
            } else {
                if (smsVerificationCode.getValidDate() != null) {
                    Date curDate = new Date();
                    if (smsVerificationCode.getValidDate().getTime() < curDate.getTime()) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("验证码超时。");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("验证码错误。");
        }
        return baseResBean;
    }

    /**
     * 校验验证码是否正确
     * 验证码有时间限制
     *
     * @param smsValidateReq
     * @return
     */
    @Override
    public SmsValidateRes smsValidateLimit(SmsValidateReq smsValidateReq) {
        logger.debug("校验验证码是否正确，校验数据：{}", JSON.toJSONString(smsValidateReq));
        SmsValidateRes baseResBean = new SmsValidateRes();
        try {
            SmsValidateRes smsValidateRes = new SmsValidateRes();
            smsValidateRes.setReceiveNo(smsValidateReq.getReceiveNo().trim());
            baseResBean.setData(smsValidateRes);
            SmsVerificationCode smsVerificationCode = smsVerificationCodeDao.selectByPrimaryKey(smsValidateReq.getTokenId());
            if (smsVerificationCode != null) {
                if (!smsValidateReq.getReceiveNo().trim().equals(smsVerificationCode.getReceiveNo())) {
                    baseResBean.setRetCode(SystemDefine.FAILURE);
                    baseResBean.setRetMsg("验证码与对应手机号不一致。");
                } else {
                    if (!smsValidateReq.getVerifyCode().equals(smsVerificationCode.getCode())) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        logger.debug("校验验证码失败，验证码错误!");
                        baseResBean.setRetMsg("验证码错误。");
                    } else {
                        if (smsVerificationCode.getValidDate() != null) {
                            Date curDate = new Date();
                            if (smsVerificationCode.getValidDate().getTime() < curDate.getTime()) {
                                baseResBean.setRetCode(SystemDefine.FAILURE);
                                baseResBean.setRetMsg("验证码超时。");
                            }
                        }
                    }
                }
            } else {
                logger.debug("校验验证码失败，未查到!");
                baseResBean.setRetCode(SystemDefine.FAILURE);
                baseResBean.setRetMsg("验证码错误。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("参数异常。");
        }
        return baseResBean;
    }

    private String buildRedisSmsKey(String receiveNo) {
        return SMS_TEL + receiveNo;
    }

    /**
     * 发送短信接口，调用方组织短信内容，本接口只负责发送短信
     *
     * @param sendSmsReq
     * @return
     */
    @Override
    public Boolean sendSms(SendSmsReq sendSmsReq) {
        logger.debug("准备发送短信，号码为：" + sendSmsReq.getReceiveNo().trim());
        if (jedisManager != null) {
            try {
                byte[] value = jedisManager.getValueByKey(SerializationUtils.serialize(buildRedisSmsKey(sendSmsReq.getReceiveNo().trim())));
                if (null != value) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {

            logger.debug("发送短信完成，号码为：" + sendSmsReq.getReceiveNo().trim());
            byte[] key = SerializationUtils.serialize(buildRedisSmsKey(sendSmsReq.getReceiveNo().trim()));
            byte[] value = SerializationUtils.serialize(buildRedisSmsKey(sendSmsReq.getReceiveNo().trim()));
            if (jedisManager != null) {
                try {
                    jedisManager.saveValueByKey(key, value, CACHE_VAL_TIME_SPAN);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return SmsUtil.post(URI_TPL_SEND_SMS, params);
    }
}
