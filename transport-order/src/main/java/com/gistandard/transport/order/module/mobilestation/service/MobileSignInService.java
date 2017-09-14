package com.gistandard.transport.order.module.mobilestation.service;

import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.QRCodeReq;
import com.gistandard.transport.order.module.customer.bean.QRCodeRes;
import com.gistandard.transport.order.module.customer.bean.SignInReq;
import com.gistandard.transport.order.module.customer.bean.SignInRes;


/**
 * @author 员伟
 * @ClassName MobileSignInService
 * <p>咪站、快递员、司机签到服务接口<p/>
 * <p>生成签到二维码内容接口<p/>
 */
public interface MobileSignInService {

    /**
     * 添加签到信息到数据库
     * @param signInReq 签到信息请求
     * @return 签到结果
     */
    SignInRes addSignInfo(SignInReq signInReq) throws MobileStationBizException;

    /**
     * 获取签到内容的二维码信息
     * @param req 签到所需的二维码请求
     * @return 返回二维码内容结果
     */
    QRCodeRes getQRCodeInfo(QRCodeReq req) throws MobileStationBizException;

}
