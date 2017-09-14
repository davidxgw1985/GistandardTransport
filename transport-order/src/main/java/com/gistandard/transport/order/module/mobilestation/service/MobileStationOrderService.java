package com.gistandard.transport.order.module.mobilestation.service;

import com.gistandard.transport.app.dubbo.order.bean.RemoveStaffReq;
import com.gistandard.transport.app.dubbo.order.bean.UpdateStaffRoleReq;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeResult;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.*;

/**
 * @author xgw
 * @ClassName MobileStationOrderService
 * @Description
 * @Version 1.0
 * @Date 2016-01-12
 */
public interface MobileStationOrderService {

    /**
     * 根据订单编号和货物编号，获取货物信息
     *
     * @param getGoodsInfoReq
     * @throws Exception
     */
    GetGoodsInfoResult getGoodsInfo(GetGoodsInfoReq getGoodsInfoReq);

    /**
     * 发送派件通知
     *
     * @param mobileSendMessageReq
     * @return
     */
    SendSmsVerifyCodeResult sendOrderMessageNotice(MobileSendMessageReq mobileSendMessageReq);


    /**
     * 获取某段时间所有单据的收支列表
     *
     * @param queryOrderBalanceListByTimeReq
     * @return
     */
    QueryOrderBalanceListByTimeResult queryOrderBalanceListByTime(QueryOrderBalanceListByTimeReq queryOrderBalanceListByTimeReq);

    /**
     * 移动station下单
     *
     * @param mobileStationOrderBean
     * @return
     */
    AppBaseResult makerOrder(MobileStationOrderBean mobileStationOrderBean) throws MobileStationBizException;

    /**
     * 移动station下单
     *
     * @param mobileStationOrderTransportBean
     * @return
     */
    AppBaseResult makerOrder(MobileStationOrderTransportBean mobileStationOrderTransportBean) throws MobileStationBizException;

    /**
     * 移动station 取消下单（运输）
     *
     * @param mobileStationCancelOrderBean
     * @return
     * @throws CustomerBizException
     */
    AppBaseResult cancelOrder(MobileStationCancelOrderBean mobileStationCancelOrderBean) throws MobileStationBizException;

    /**
     * 移动station 取消下单（签派）
     *
     * @param mobileStationCancelOrderBean
     * @return
     * @throws CustomerBizException
     */
    AppBaseResult cancelOrderByDispatch(MobileStationCancelOrderBean mobileStationCancelOrderBean) throws MobileStationBizException;

    /**
     * mobilestation 3.0下单接口(包含I单和O单逻辑)
     *
     * @param recordMobileStationOrderRequest
     * @return
     * @title recordMobileStationOrder
     * @author M.simple
     * @version 3.0
     * @datetime 2016年6月13日 下午6:06:06
     */
    MobileStationOrderResult recordMobileStationOrder(RecordMobileStationOrderRequest recordMobileStationOrderRequest) throws MobileStationBizException;

    /**
     * mobilestation 3.0下单接口(包含I单和O单逻辑)
     *
     * @param recordMWMobileStationOrderRequest
     * @return
     * @title recordMobileStationOrder
     * @author M.simple
     * @version 3.0
     * @datetime 2016年6月13日 下午6:06:06
     */
    MobileStationOrderResult recordMWMobileStationOrder(RecordMWMobileStationOrderRequest recordMWMobileStationOrderRequest) throws MobileStationBizException;

    /**
     * 插入跟踪日志
     *
     * @param waybillTraceOperateBean
     * @throws Exception
     */
    void insertWaybillTrace(WaybillTraceOperateBean waybillTraceOperateBean);
	
	/**
     * 接单接口
     * @title receiveMobileStationOrder 
     * @param recordMobiStaOrderRequest
     * @return
     * @throws CustomerBizException 
     * @author M.simple  
     * @version 1.0
     * @datetime 2016年6月20日 上午11:27:51
     */
    AppBaseResult receiveMobileStationOrder(RecordMobileStationOrderRequest recordMobiStaOrderRequest)
   			throws MobileStationBizException;

    /**
     * 修改订单线路信息
     * @param updateLineInfoReq
     * @return
     */
    ModifyLineInfoResult modifyLineInfo(UpdateLineInfoReq updateLineInfoReq) throws MobileStationBizException;


    /**
     * 结算完成后更新MobileBookingForm的结算对账单号
     * @param updateValidBillNoReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult updateValidBillno(UpdateValidBillNoReq updateValidBillNoReq) throws MobileStationBizException;

    /**
     * MS3.0企业修改员工角色
     * @param updateStaffRoleReq
     * @return
     * @throws Exception
     */
    AppBaseResult updateStaffRole(UpdateStaffRoleReq updateStaffRoleReq) throws Exception ;

    /**
     * MS3.0 企业移除员工
     * @param removeStaffReq
     * @return
     * @throws Exception
     */
    AppBaseResult removeStaff(RemoveStaffReq removeStaffReq) throws Exception ;
}
