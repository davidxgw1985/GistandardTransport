package com.gistandard.transport.app.dubbo.sign.service;

import com.gistandard.transport.app.dubbo.sign.bean.SignInTimeDubboRes;

import java.util.Date;

/**
 * @author: 员伟
 * @ClassName: SignInDubboService
 * @Date: 2017/6/21
 * @Description: 获取签到信息 dubbo服务接口
 */
public interface SignInDubboService {

    SignInTimeDubboRes getSignInTime(String signInAcctUser, String stationAcctUser) throws Exception;

    /**
     * 获取用户对用户层级签到最近时间(快递员,司机,咪站在咪站签到)
     * @param signInAcctUser 签到用户层级账户
     * @param stationAcctUser 二维码生成用户层级账户
     * @param signInTime signInTime
     * @return 签到最近时间
     */
    SignInTimeDubboRes getUser2UserSignInfo(String signInAcctUser, String stationAcctUser,Date signInTime);


    /**
     * 获取用户对企业层级签到最近时间(快递员,司机,咪站在洼站签到)
     * @param signInAcctUser signInAcctUser 签到用户层级账户
     * @param stationAcctUser stationAcctUser  签到企业层级账户
     * @param signInTime signInTime 签到时间
     * @return 签到最近时间
     */
    SignInTimeDubboRes getUser2CompanySignInfo(String signInAcctUser, String stationAcctUser,Date signInTime);



    /**
     * 获取企业对企业层级签到最近时间(洼站在洼站签到)
     * @param signInAcctUser signInAcctUser 签到企业层级账户
     * @param stationAcctUser stationAcctUser  签到企业层级账户
     * @param signInTime signInTime 签到时间
     * @return 签到最近时间
     */
    SignInTimeDubboRes getCompany2CompanySignInfo(String signInAcctUser, String stationAcctUser,Date signInTime);

    /**
     * 获取企业对企业层级签到最近时间(洼站在咪站签到)
     * @param signInAcctUser signInAcctUser 签到企业层级账户
     * @param stationAcctUser stationAcctUser  签到用户层级账户
     * @param signInTime signInTime 签到时间
     * @return 签到最近时间
     */
    SignInTimeDubboRes getCompany2UserSignInfo(String signInAcctUser, String stationAcctUser,Date signInTime);

}
