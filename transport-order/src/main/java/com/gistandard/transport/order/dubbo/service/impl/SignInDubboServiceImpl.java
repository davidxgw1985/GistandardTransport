package com.gistandard.transport.order.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.sign.bean.SignInRecord;
import com.gistandard.transport.app.dubbo.sign.bean.SignInTimeDubboRes;
import com.gistandard.transport.app.dubbo.sign.service.SignInDubboService;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileSignIn;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileSignInDaoEx;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 签到记录获取dubbo接口实现
 * @author 员伟
 */
public class SignInDubboServiceImpl implements SignInDubboService {

    private final Logger logger = LoggerFactory.getLogger(SignInDubboServiceImpl.class);

    @Autowired
    private MobileSignInDaoEx mobileSignInDaoEx;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Override
    public SignInTimeDubboRes getSignInTime(String signInAcctUser, String stationAcctUser) throws Exception {
        logger.info("signInAcctUser --{}", signInAcctUser);
        logger.info("stationAcctUser --{}", stationAcctUser);
        SignInTimeDubboRes res = new SignInTimeDubboRes();
        if (StringUtils.isBlank(signInAcctUser) || StringUtils.isBlank(stationAcctUser)) {
            res.setRetCode(-1);
            res.setRetMsg("参数为空");
            return res;
        }
        ComAccount signInAcct = comAccountDaoEx.queryByAccount(signInAcctUser);
        if (signInAcct == null) {
            res.setRetCode(-1);
            res.setRetMsg("签到人账户不存在");
            return res;
        }
        ComAccount stanInAcct = comAccountDaoEx.queryByAccount(stationAcctUser);
        if (stanInAcct == null) {
            res.setRetCode(-1);
            res.setRetMsg("签到站点账户不存在");
            return res;
        }
        MobileSignIn mobileSignIn = mobileSignInDaoEx.queryMobileSignIn(signInAcctUser, stanInAcct.getId());
        if (mobileSignIn != null) {
            res.setSignInTime(mobileSignIn.getScanTime());
        } else {
            res.setSignInTime(null);
        }
        logger.info("调用签到时间dubbo接口-mobileSignIn{}", JSON.toJSONString(mobileSignIn));
        return res;
    }


    /**
     * 获取用户对用户层级签到最近时间(快递员,司机,咪站在咪站签到)
     * @param signInAcctUser 签到用户层级账户
     * @param stationAcctUser 二维码生成用户层级账户
     * @param signInTime signInTime
     * @return 签到最近时间
     */
    @Override
    public SignInTimeDubboRes getUser2UserSignInfo(String signInAcctUser, String stationAcctUser, Date signInTime) {
        logger.info("getUser2UserSignInfo 获取用户对用户层级签到记录开始-start");
        logger.info("signInAcctUser:{}--stationAcctUser:{}--signInTime:{}", signInAcctUser, stationAcctUser, signInTime);
        SignInTimeDubboRes res = new SignInTimeDubboRes();
        if (validateParam(signInAcctUser, stationAcctUser, signInTime, res)) {
            logger.error("获取最近签到时间dubbo接口校验参数失败");
            return res;
        }
        MobileSignIn mobileSignIn = mobileSignInDaoEx.getUser2UserSignInfo(signInAcctUser, stationAcctUser, signInTime);
        SignInRecord signInRecord;
        try {
            signInRecord = new SignInRecord();
            BeanUtils.copyProperties(signInRecord, mobileSignIn);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(-1);
            res.setRetMsg("getUser2UserSignInfo出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(-1);
            res.setRetMsg("getUser2UserSignInfo出错");
            return res;
        }
        logger.info("getUser2UserSignInfo-dubbo接口-signInRecord{}", JSON.toJSONString(signInRecord));
        res.setSignInTime(signInRecord.getScanTime());
        res.setSignInRecord(signInRecord);
        logger.info("getUser2UserSignInfo-dubbo接口-mobileSignIn{}", JSON.toJSONString(mobileSignIn));
        logger.info("getUser2UserSignInfo 获取用户对用户层级签到记录开始-end-successful");
        return res;
    }

    /**
     * 获取用户对企业层级签到最近时间(快递员,司机,咪站在洼站签到)
     * @param signInAcctUser signInAcctUser 签到用户层级账户
     * @param stationAcctUser stationAcctUser  签到企业层级账户
     * @param signInTime signInTime 签到时间
     * @return 签到最近时间
     */
    @Override
    public SignInTimeDubboRes getUser2CompanySignInfo(String signInAcctUser, String stationAcctUser, Date signInTime) {
        logger.info("getUser2CompanySignInfo 获取用户对企业层级签到记录开始-start");
        logger.info("signInAcctUser:{}--stationAcctUser:{}--signInTime:{}", signInAcctUser, stationAcctUser, signInTime);
        SignInTimeDubboRes res = new SignInTimeDubboRes();
        if (validateParam(signInAcctUser, stationAcctUser, signInTime, res)) {
            logger.error("获取最近签到时间dubbo接口校验参数失败");
            return res;
        }
        MobileSignIn mobileSignIn = mobileSignInDaoEx.getUser2CompanySignInfo(signInAcctUser, stationAcctUser, signInTime);
        SignInRecord signInRecord;
        try {
            signInRecord = new SignInRecord();
            BeanUtils.copyProperties(signInRecord, mobileSignIn);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(-1);
            res.setRetMsg("getUser2CompanySignInfo出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(-1);
            res.setRetMsg("getUser2CompanySignInfo出错");
            return res;
        }
        logger.info("getUser2CompanySignInfo-dubbo接口-signInRecord{}", JSON.toJSONString(signInRecord));
        res.setSignInTime(signInRecord.getScanTime());
        res.setSignInRecord(signInRecord);
        logger.info("getUser2CompanySignInfo-dubbo接口-mobileSignIn{}", JSON.toJSONString(mobileSignIn));
        logger.info("getUser2CompanySignInfo 获取用户对企业层级签到记录开始-end-successful");
        return res;
    }

    /**
     * 获取企业对企业层级签到最近时间(洼站在洼站签到)
     * @param signInAcctUser signInAcctUser 签到企业层级账户
     * @param stationAcctUser stationAcctUser  签到企业层级账户
     * @param signInTime signInTime 签到时间
     * @return 签到最近时间
     */
    @Override
    public SignInTimeDubboRes getCompany2CompanySignInfo(String signInAcctUser, String stationAcctUser, Date signInTime) {
        logger.info("getCompany2CompanySignInfo 获取企业对企业层级签到记录开始-start");
        logger.info("signInAcctUser:{}--stationAcctUser:{}--signInTime:{}", signInAcctUser, stationAcctUser, signInTime);
        SignInTimeDubboRes res = new SignInTimeDubboRes();
        if (validateParam(signInAcctUser, stationAcctUser, signInTime, res)) {
            logger.error("获取最近签到时间dubbo接口校验参数失败");
            return res;
        }
        MobileSignIn mobileSignIn = mobileSignInDaoEx.getCompany2CompanySignInfo(signInAcctUser, stationAcctUser, signInTime);
        SignInRecord signInRecord;
        try {
            signInRecord = new SignInRecord();
            BeanUtils.copyProperties(signInRecord, mobileSignIn);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(-1);
            res.setRetMsg("获取企业对企业层级签到最近时间出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(-1);
            res.setRetMsg("获取企业对企业层级签到最近时间出错");
            return res;
        }
        logger.info("getCompany2CompanySignInfo-dubbo接口-signInRecord{}", JSON.toJSONString(signInRecord));
        res.setSignInTime(signInRecord.getScanTime());
        res.setSignInRecord(signInRecord);
        logger.info("getCompany2CompanySignInfo-dubbo接口-mobileSignIn{}", JSON.toJSONString(mobileSignIn));
        logger.info("getCompany2CompanySignInfo 获取企业对企业层级签到记录开始-end-successful");
        return res;
    }


    /**
     * 获取企业对企业层级签到最近时间(洼站在咪站签到)
     * @param signInAcctUser signInAcctUser 签到企业层级账户
     * @param stationAcctUser stationAcctUser  签到用户层级账户
     * @param signInTime signInTime 签到时间
     * @return 签到最近时间
     */
    @Override
    public SignInTimeDubboRes getCompany2UserSignInfo(String signInAcctUser, String stationAcctUser, Date signInTime) {
        logger.info("getCompany2UserSignInfo 获取企业对企业层级签到记录开始-start");
        logger.info("signInAcctUser:{}--stationAcctUser:{}--signInTime:{}", signInAcctUser, stationAcctUser, signInTime);
        SignInTimeDubboRes res = new SignInTimeDubboRes();
        if (validateParam(signInAcctUser, stationAcctUser, signInTime, res)) {
            logger.error("获取最近签到时间dubbo接口校验参数失败");
            return res;
        }
        MobileSignIn mobileSignIn = mobileSignInDaoEx.getCompany2UserSignInfo(signInAcctUser, stationAcctUser, signInTime);
        SignInRecord signInRecord;
        try {
            signInRecord = new SignInRecord();
            BeanUtils.copyProperties(signInRecord, mobileSignIn);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException-{},", e);
            res.setRetCode(-1);
            res.setRetMsg("getCompany2UserSignInfo出错");
            return res;
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException{},", e);
            res.setRetCode(-1);
            res.setRetMsg("getCompany2UserSignInfo出错");
            return res;
        }
        logger.info("getCompany2UserSignInfo-dubbo接口-signInRecord{}", JSON.toJSONString(signInRecord));
        res.setSignInTime(signInRecord.getScanTime());
        res.setSignInRecord(signInRecord);
        logger.info("getCompany2UserSignInfo-dubbo接口-mobileSignIn{}", JSON.toJSONString(mobileSignIn));
        logger.info("getCompany2UserSignInfo 获取企业对企业层级签到记录开始-end-successful");
        return res;
    }

    private boolean validateParam(String signInAcctUser, String stationAcctUser, Date signInTime, SignInTimeDubboRes res) {
        if (StringUtils.isBlank(signInAcctUser)) {
            logger.error("签到人员账号为空");
            res.setRetCode(-1);
            res.setRetMsg("签到人员账号为空");
            return true;
        }
        if (StringUtils.isBlank(stationAcctUser)) {
            logger.error("生产二维码站点账号为空");
            res.setRetCode(-1);
            res.setRetMsg("生产二维码站点账号为空");
            return true;
        }
        if (signInTime == null) {
            logger.error("获取最近签到时间,签到时间参数为空");
            res.setRetCode(-1);
            res.setRetMsg("时间参数为空");
            return true;
        }
        ComAccount signInAcct = comAccountDaoEx.queryByAccount(signInAcctUser);
        if (signInAcct == null) {
            logger.error("签到人账户数据库中不存在");
            res.setRetCode(-1);
            res.setRetMsg("签到人账户不存在");
            return true;
        }
        ComAccount stanInAcct = comAccountDaoEx.queryByAccount(stationAcctUser);
        if (stanInAcct == null) {
            logger.error("签到站点账户数据库中不存在");
            res.setRetCode(-1);
            res.setRetMsg("签到站点账户不存在");
            return true;
        }
        return false;
    }
}
