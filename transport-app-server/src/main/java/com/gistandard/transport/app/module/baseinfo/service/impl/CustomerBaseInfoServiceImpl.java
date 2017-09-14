package com.gistandard.transport.app.module.baseinfo.service.impl;


import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateRes;
import com.gistandard.transport.app.module.baseinfo.service.CustomerBaseInfoService;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComUserinfoDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.register.define.RegisterDefine;
import com.gistandard.transport.system.common.baseinfo.bean.*;
import com.valueplus.psc.dubbo.service.common.bean.*;
import com.valueplus.psc.dubbo.service.common.define.RecordOperatorTypeEnum;
import com.valueplus.psc.dubbo.service.common.define.ServiceFailEnum;
import com.valueplus.psc.dubbo.service.login.AccountService;
import com.valueplus.psc.dubbo.service.record.PscRecordService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kongxm
 * @ClassName CustomerBaseInfoServiceImpl
 * @Description 基本信息维护实现类
 * @Version 1.0
 * @Date 2016/1/28
 */
@Service
public class CustomerBaseInfoServiceImpl implements CustomerBaseInfoService {

    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(CustomerBaseInfoServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Autowired
    private SmsDubboService smsService;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

//    /**
//     * 备案数据查询服务
//     */
//    @Autowired
//    private OperaterAuditInfoService operaterAuditInfoService;

    /**
     * 备案dubbo
     */
    @Autowired
    private PscRecordService pscRecordService;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerBaseInfoServiceImpl.class);

    @Override
    public BaseInfoRes queryByAccountId(Integer accountId) throws MobileStationBizException {
        ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(accountId);
        if (comUserinfo == null) {
            throw new MobileStationBizException("账号不存在");
        }
        BaseInfoRes baseInfoRes = new BaseInfoRes();
        baseInfoRes.setAvatar(comUserinfo.getUserImg());
        baseInfoRes.setNickName(comUserinfo.getNickName());
        baseInfoRes.setPhone(comUserinfo.getTelephone());
        return baseInfoRes;
    }

    @Override
    public void changePassword(ChangePasswordReq changePasswordReq) throws MobileStationBizException {
        Integer accountId = changePasswordReq.getAccountId();
        String loginAccount = changePasswordReq.getLoginAccount();
        String oldPassword = changePasswordReq.getOldPassword();
        String newPassword = changePasswordReq.getNewPassword();
        String confirmNewPassword = changePasswordReq.getComfirmNewPassword();
        //verify param
        if (accountId == null) {
            throw new MobileStationBizException("账号ID不能为空");
        }
        if (StringUtils.isEmpty(oldPassword)) {
            throw new MobileStationBizException("原密码不能为空");
        }
        if (StringUtils.isEmpty(newPassword)) {
            throw new MobileStationBizException("新密码不能为空");
        }
        if (StringUtils.isEmpty(confirmNewPassword)) {
            throw new MobileStationBizException("确认密码不能为空");
        }
        if (newPassword.equals(confirmNewPassword) == false) {
            throw new MobileStationBizException("新密码与确认密码不一致");
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$";
        if (newPassword.matches(regex) == false) {
            throw new MobileStationBizException("新密码至少6位字符，并且要同时包含数值和字母。");
        }
        if (oldPassword.equals(newPassword)) {
            throw new MobileStationBizException("新密码与原密码不能一致");
        }
        //update password
        ServiceResult result = modifyPassword(loginAccount, newPassword, oldPassword);
        if (!result.isResult()) {
            //修改失败
            throw new MobileStationBizException(result.getMsg());
        }
    }

    @Override
    public void bindPhone(BindPhoneReq bindPhoneReq) throws MobileStationBizException {
        //check param.
        Integer accountId = bindPhoneReq.getAccountId();
        String phone = bindPhoneReq.getPhone();
        String verifyCode = bindPhoneReq.getVerifyCode();
        if (accountId == null) {
            throw new MobileStationBizException("账号ID不能为空");
        }
        if (StringUtils.isEmpty(phone)) {
            throw new MobileStationBizException("手机号码不能为空");
        }
        if (StringUtils.isEmpty(verifyCode)) {
            throw new MobileStationBizException("手机验证码不能为空");
        }
        //TODO:check verify code.
//        if (!"0000".equals(verifyCode)) {
//            throw new MobileStationBizException("手机验证码不正确");
//        }
        SmsValidateReq smsValidateReq = new SmsValidateReq();
        smsValidateReq.setTokenId(bindPhoneReq.getTokenId());
        smsValidateReq.setVerifyCode(verifyCode);
        SmsValidateRes smsRes = smsService.smsValidate(smsValidateReq);
        if (smsRes.getRetCode() != SystemDefine.SUCCESS) {
            throw new MobileStationBizException("短信验证码不正确");
        }
        //save phone Num
        ComUserinfo comUserInfo = comUserinfoDao.queryByAcctId(accountId);
        if (comUserInfo == null) {
            throw new MobileStationBizException("账户不存在");
        }
        ComUserinfo user = new ComUserinfo();
        user.setId(comUserInfo.getId());
        user.setTelephone(phone);
        comUserinfoDao.updateByPrimaryKeySelective(user);

        //同步psc进行修改
//        modifyTelephone(bindPhoneReq);
    }

//    /**
//     * 调用psc接口进行密码修改
//     * @title modifyTelephone
//     * @describe TODO
//     * @param bindPhoneReq
//     * @return
//     * @author M.simple
//     * @version 1.0
//     */
//    public ServiceResult<AccountInfo> modifyTelephone(BindPhoneReq bindPhoneReq){
//        //获取账号com_account信息
//        ComAccount comAccount = comAccountDao.selectByPrimaryKey(bindPhoneReq.getAccountId());
//
//        //获取运输平台的系统认证信息
//        ServiceAuthBean buildTransportAuthInfo = buildTransportAuthInfo();
//
//        AuditParamBean auditShowParamBean = new AuditParamBean();
//        auditShowParamBean.setAccountName(comAccount.getAcctUsername());
//        ServiceResult<OperatorRecordBean> result = operaterAuditInfoService.queryAuditShowInfo(auditShowParamBean);
//        RecordInfoBean recordInfo = result.getData().getRecordInfoBean();
//
//        // 认证信息
//        recordInfo.setRecordType(RecordOperatorTypeEnum.UPDATE.getType());
//        recordInfo.setOperatePermitNo(null);
//        recordInfo.setAcctType(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
//        recordInfo.setAuditType(AuditTypEnum.UPDATE_AUDIT.getCode());
//        recordInfo.setAcctUsername(comAccount.getAcctUsername());
//
//        AccountInfo accountInfo = result.getData().getRecordInfoBean().getAccountInfo();
//        accountInfo.setTelephone(bindPhoneReq.getPhone());
//        accountInfo.setAccountId(null);
//        recordInfo.setAccountInfo(accountInfo);
////		accountInfo.setUserImg(userImg);
//
//        //调用psc接口，修改手机号码
//        ServiceResult<AccountInfo> record = pscRecordService.record(buildTransportAuthInfo, recordInfo);
//
//        LOG.warn("----------------- ---------------------->psc返回" + record.isResult());
//        return record;
//    }

    @Override
    public void changeNickName(ChangeNickNameReq changeNickNameReq) throws MobileStationBizException {
        Integer accountId = changeNickNameReq.getAccountId();
        String nickName = changeNickNameReq.getNickName();
        if (accountId == null) {
            throw new MobileStationBizException("账号ID不能为空");
        }
        if (StringUtils.isEmpty(nickName)) {
            throw new MobileStationBizException("昵称不能为空");
        }
        ComUserinfo comUserInfo = comUserinfoDao.queryByAcctId(accountId);
        if (comUserInfo == null) {
            throw new MobileStationBizException("账户不存在");
        }
        ComUserinfo user = new ComUserinfo();
        user.setId(comUserInfo.getId());
        user.setNickName(nickName);
        comUserinfoDao.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeAvatar(ChangeAvatarReq changeAvatarReq) throws MobileStationBizException {
        Integer accountId = changeAvatarReq.getAccountId();
        String url = changeAvatarReq.getUrl();
        if (accountId == null) {
            throw new MobileStationBizException("账号ID不能为空");
        }
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        if (comAccount == null) {
            throw new MobileStationBizException("账户不存在");
        }
        RecordInfoBean recordInfoBean = new RecordInfoBean();
        // 帐号相关信息
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAcctUsername(comAccount.getAcctUsername());
        accountInfo.setUserImg(url);
        recordInfoBean.setAccountInfo(accountInfo);
        if (changeAvatarReq.getBizAttachment()!= null){
            List<AttachmentInfo> attachmentList = new ArrayList<AttachmentInfo>();
            AttachmentInfo attachmentInfo = new AttachmentInfo();
            attachmentInfo.setAttachmentTypeId(changeAvatarReq.getBizAttachment().getFileType());
            attachmentInfo.setAccount(comAccount.getAcctUsername());
            attachmentInfo.setAttachmentName(changeAvatarReq.getBizAttachment().getAttachName());
            attachmentInfo.setAttachmentOldName(changeAvatarReq.getBizAttachment().getAttachOldName());
            attachmentInfo.setUploadDomain(changeAvatarReq.getBizAttachment().getAttachDomain());
            String filePath =changeAvatarReq.getBizAttachment().getAttachPath();
            attachmentInfo.setSavePath(filePath.substring(0, filePath.lastIndexOf("/")));
            attachmentInfo.setAttachmentMime(changeAvatarReq.getBizAttachment().getAttachType());
            attachmentInfo.setAttachmentSize(changeAvatarReq.getBizAttachment().getAttachSize());
            attachmentInfo.setServerIp(changeAvatarReq.getBizAttachment().getAttachServerIp());
            attachmentInfo.setAttachmentCreateTime(new Date());
            attachmentList.add(attachmentInfo);
            recordInfoBean.setAttachmentList(attachmentList);
        }

        recordInfoBean.setRecordType(RecordOperatorTypeEnum.UPDATE.getType());
        String acctType = SysAccountRole.NORMAL_PERSONAL.getRoleCode();
        if (comAccount.getAcctType()=="2"){
            acctType = SysAccountRole.NORMAL_COMPANY.getRoleCode();
        }
        recordInfoBean.setAcctType(acctType);
        recordInfoBean.setAcctUsername(comAccount.getAcctUsername());
        ServiceResult result = pscRecordService.record(RegisterDefine.buildTransportAuthInfo(), recordInfoBean);
        Logger.info("修改头像返回结果：{}", JSON.toJSONString(result));
        if (result.isResult()){
            ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(accountId);
            if (comUserinfo == null) {
                throw new MobileStationBizException("用户不存在");
            }
            ComUserinfo user = new ComUserinfo();
            user.setId(comUserinfo.getId());
            user.setUserImg(url);
            comUserinfoDao.updateByPrimaryKeySelective(user);
        }else {
            throw new MobileStationBizException("更新头像失败");
        }
    }

    @Override
    public void retrievePassword(RetrievePasswordReq req) throws MobileStationBizException {
        //根据用户传入的O2Id账号或者手机号获取账号
        ComAccount comAccount = comAccountDaoEx.queryAccountByParam(req.getAcctUsername());
        if(comAccount==null){
            throw new MobileStationBizException("账号不存在");
        }
        SmsValidateReq smsValidateReq = new SmsValidateReq();
        smsValidateReq.setTokenId(req.getTokenId());
        smsValidateReq.setVerifyCode(req.getVerifyCode());
        SmsValidateRes smsRes = smsService.smsValidate(smsValidateReq);
        if (smsRes.getRetCode() != SystemDefine.SUCCESS) {
            throw new MobileStationBizException("短信验证码不正确");
        }

        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.MOBILE_STATION_SYS_FLAG);

        UpdatePwdBean updatePwdBean = new UpdatePwdBean();
        updatePwdBean.setAcctUsername(comAccount.getAcctUsername());
        updatePwdBean.setNewPassword(req.getNewPassword());

        ServiceResult serviceResult = accountService.resetPassword(serviceAuthBean, updatePwdBean);
        LOG.info("userAccount={},newPassword={},result={}", req.getAcctUsername(), req.getNewPassword(), serviceResult == null ? false : serviceResult.isResult());
        if (!serviceResult.isResult()) {
            if(serviceResult.getFailCode() == ServiceFailEnum.NEW_PWD_ERROR.getValue()){
                throw new MobileStationBizException("密码长度不符合要求(8-20位)！");
            }else{
                throw new MobileStationBizException("密码重置失败！");
            }

        }

    }

    private ServiceResult modifyPassword(String userAccount, String newPassword, String oldPassword) {
        UpdatePwdBean updatePwdBean = new UpdatePwdBean();
        updatePwdBean.setAcctUsername(userAccount);
        updatePwdBean.setNewPassword(newPassword);
        updatePwdBean.setOldPassword(oldPassword);
        //TODO:授权参数待修改
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.CUSTOMER_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.CUSTOMER_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.MOBILE_STATION_SYS_FLAG);

        ServiceResult modifyResult = accountService.modifyPassword(serviceAuthBean, updatePwdBean);
        LOG.info("userAccount={},newPassword={},oldPassword={},result={}",userAccount,newPassword,oldPassword,modifyResult==null?false:modifyResult.isResult());
        return modifyResult;
    }

    /**
     * 组装运输平台的系统认证信息
     */
    public static ServiceAuthBean buildTransportAuthInfo() {
        ServiceAuthBean serviceAuthBean = new ServiceAuthBean();
        serviceAuthBean.setAuthPwd(SystemDefine.TRANSPORT_AUTH_PWD);
        serviceAuthBean.setAuthUser(SystemDefine.TRANSPORT_AUTH_USER);
        serviceAuthBean.setSysFlag(SystemDefine.TRANSPORT_SYS_FLAG);
        return serviceAuthBean;
    }
}