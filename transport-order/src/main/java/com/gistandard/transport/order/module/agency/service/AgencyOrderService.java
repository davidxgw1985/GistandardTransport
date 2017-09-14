package com.gistandard.transport.order.module.agency.service;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.agency.bean.*;
import com.gistandard.transport.order.module.customer.bean.ReceiveCustomerOrderReq;

/**
 * @author xgw
 * @ClassName AgencyOrderService
 * @Description MS3.0代理客户下单
 * @Version 3.0
 * @Date 2016/6/22
 */
public interface AgencyOrderService {

    /**
     * 代理下单订单列表查询
     *
     * @return
     */
    AgencyOrderListRes queryList(AgencyOrderListReq req) throws MobileStationBizException;

    /**
     * 代理下单订单详细查询
     *
     * @return
     */
    AppBaseResult queryDetail(AgencyOrderDetailReq req) throws MobileStationBizException;

    /**
     * 代理下单逻辑
     * 1、订单类型：Outter单+指派单
     * 2、下单到booking_form  订单状态为已接单（1）
     * 3、下单到moblie_booking_form  订单状态为已接单（1）
    * @title placeAnOrder 
    * @describe TODO
    * @param req
    * @return
    * @throws MobileStationBizException 
    * @author M.simple
     */
    AgencyPlaceOrderRes agencyPlaceOrder(PlaceAnOrderReq req) throws MobileStationBizException;

    /**
     * 代理下单逻辑
     * 1、订单类型：Outter单+指派单
     * 2、下单到booking_form  订单状态为已接单（1）
     * 3、下单到moblie_booking_form  订单状态为已接单（1）
     *
    * @title placeAnOrder
    * @describe TODO
    * @param agencyPlaceOrderRes
    * @return
    * @throws MobileStationBizException
    * @author M.simple
     */
    AppBaseResult agencyPlaceOrderAfter(AgencyPlaceOrderRes agencyPlaceOrderRes);

    /**
     * 投保支付成功
     * @param req
     * @throws MobileStationBizException
     */
    AppBaseResult paySuccessed(ReceiveCustomerOrderReq req)throws MobileStationBizException;

    /**
     * 取消投保继续下单
     * @param req
     * @throws MobileStationBizException
     */
    AppBaseResult cancelInsuredContinueOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException;

}
