package com.gistandard.transport.app.dubbo.wa.service;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.pojo.res.WaQRCodeDubboResBean;
import com.gistandard.transport.app.dubbo.pojo.res.WaSignInDubboResBean;
import com.gistandard.transport.app.dubbo.wa.bean.*;

/**
 * @author: xgw
 * @ClassName: WaOrderDubboService
 * @Date: 2017/7/4
 * @Description: 提供给蛙站的订单dubbo接口
 */
public interface WaOrderDubboService {

    /**
     * 蛙站M-W发起竞价接口
     *
     * @param waBidInitiationReq
     * @return
     */
    MsDubboResBean waBidInitiation(WaBidInitiationReq waBidInitiationReq) throws Exception;

    /**
     * MS3.0 蛙站确认车队竞价接口
     * 1、蛙站发起M-W、W-W、W-M广播竞价
     * 2、车队填写报价、税率，发起竞价
     * 3、蛙站确认车队报价后调用此接口
     * 4、插入MobileBookingForm、MobileSubOrder、MobileGoodsDtl、MobileFleetBidding
     *
     * @param waConfirmOrderReq
     * @return
     */
    MsDubboResBean waConfirmOrder(WaConfirmOrderReq waConfirmOrderReq) throws Exception;

    /**
     * MS3.0 蛙站指派快递员W-POD接口
     * 1、蛙站指派给快递员W-POD订单
     * 2、快递员接W-POD广播单后，蛙站回调
     * 3、插入MobileBookingForm、MobileSubOrder、MobileGoodsDtl
     *
     * @param waAssignOrderReq
     * @return
     */
    MsDubboResBean waAssignOrder(WaAssignOrderReq waAssignOrderReq) throws Exception;


    /**
     * 添加签到信息到数据库
     * @param req 签到信息请求
     * @return 签到结果
     */
    WaSignInDubboResBean addSignInfo(WaSignInDubboReq req) throws Exception;

    /**
     * 获取签到内容的二维码信息
     * @param req 签到所需的二维码请求
     * @return 返回二维码内容结果
     */
    WaQRCodeDubboResBean getQRCodeInfo(WaQRCodeDubboReq req) throws Exception;

}
