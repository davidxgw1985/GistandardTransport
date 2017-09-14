package com.gistandard.transport.app.module.baseinfo.service;


import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.baseinfo.bean.*;

/**
 * @author kongxm
 * @ClassName CustomerBaseInfoService
 * @Description 基本信息管理接口
 * @Version 1.0
 * @Date 2016/1/26
 */
public interface CustomerBaseInfoService {
    /**
     * 查询用户基本信息
     *
     * @param accountId
     * @return BaseInfoRes
     * @throws MobileStationBizException
     */
    BaseInfoRes queryByAccountId(Integer accountId) throws MobileStationBizException;


    /**
     * 修改密码
     *
     * @param changePasswordReq
     * @throws MobileStationBizException
     */
    void changePassword(ChangePasswordReq changePasswordReq) throws MobileStationBizException;


    /**
     * 绑定手机
     *
     * @param bindPhoneReq
     * @throws MobileStationBizException
     */
    void bindPhone(BindPhoneReq bindPhoneReq) throws MobileStationBizException;

    /**
     * 修改用戶昵称
     *
     * @param changeNickNameReq
     * @throws MobileStationBizException
     */
    void changeNickName(ChangeNickNameReq changeNickNameReq) throws MobileStationBizException;

    /**
     * 修改用户头像
     *
     * @param changeAvatarReq
     * @throws MobileStationBizException
     */
    void changeAvatar(ChangeAvatarReq changeAvatarReq) throws MobileStationBizException;

    /**
     * 找回密码
     *
     * @param req
     */
    void retrievePassword(RetrievePasswordReq req) throws MobileStationBizException;
}
