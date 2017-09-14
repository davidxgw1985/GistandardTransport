package com.gistandard.transport.order.module.mobilestation.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderDetailReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.UserOrderQueryDetailResult;
import com.gistandard.transport.app.dubbo.order.bean.UserOrderQueryListResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStatusOperateReq;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.*;

/**
 * @author xgw
 * @ClassName MobileUserOrderService
 * @Description
 * @Version 1.0
 * @Date 2016-01-12
 */
public interface MobileUserOrderService {

    /**
     * MS3.0用户订单 - 订单列表查询
     *
     * @param mobileUserOrderListReq
     * @return
     */
    UserOrderQueryListResult queryList(MobileUserOrderListReq mobileUserOrderListReq) throws MobileStationBizException;

    /**
     * MS3.0用户订单 - 订单详细查询
     *
     * @param mobileUserOrderDetailReq
     * @return
     */
    UserOrderQueryDetailResult queryDetail(MobileUserOrderDetailReq mobileUserOrderDetailReq) throws MobileStationBizException;

    /**
     * MS3.0 用户订单-放弃订单
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult giveUpOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0 用户订单-发车
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult depart(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0 用户订单-订单失败
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult orderFailure(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0 用户订单-确认送达
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult deliveryConfirm(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0 用户订单-退回失败派件单
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult returnFailureOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0 获取距离范围内附近的站点
     *
     * @param getNearStationReq
     * @return
     */
    UserOrderGetNearStationResult getNearStation(GetNearStationReq getNearStationReq) throws MobileStationBizException;

    /**
     * MS3.0 用户订单-批量指派
     * MS在签派时，可以选择多个待签派单，对某个站点进行批量指派
     * @param batchAssignReq
     * @return
     */
    AppBaseResult batchAssign(BatchAssignReq batchAssignReq) throws MobileStationBizException;

    /**
     * 获取订单的支付状态接口
     *
     * @param getOrderPayStatus
     * @throws Exception
     */
    UserOrderGetOrderPayStatusResult getOrderPayStatus(GetOrderPayStatusReq getOrderPayStatus) throws MobileStationBizException;

    /**
     * 根据电话号码获取账号信息
     * @param telephone
     * @return
     */
    String queryAccountByTelephone(String telephone);

    /**
     * MS3.0用户订单 - 订单列表查询(增加快递但
     *
     * @param mobileUserOrderListReq
     * @return
     */
    UserOrderQueryListResult queryListCustom(MobileUserOrderListReq mobileUserOrderListReq) throws MobileStationBizException;
}
