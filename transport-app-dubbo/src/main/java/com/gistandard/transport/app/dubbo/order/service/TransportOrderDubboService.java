package com.gistandard.transport.app.dubbo.order.service;

import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * @author: xgw
 * @ClassName: TransportOrderDubboService
 * @Date: 2017/7/31
 * @Description: 运输平台订单Dubbo服务
 */
public interface TransportOrderDubboService {

    /**
     * 同城快递 同城专送 下单接口
     * @param placeExpressOrderReq 请求对象
     * @return 下单接口返回
     */
    PlaceOrderResult placeExpressOrder(PlaceExpressOrderReq placeExpressOrderReq);

    /**
     * 同城运输 下单接口
     * @param placeTransportOrderReq 请求对象
     * @return 下单接口返回
     */
    PlaceOrderResult placeTransportOrder(PlaceTransportOrderReq placeTransportOrderReq);

    /**
     * 同城运输 用户确认报价
     * @param handleOrderPriceReq 请求对象
     * @return 用户确认报价返回
     */
    MsDubboResBean confirmOrderPrice(HandleOrderPriceReq handleOrderPriceReq);


    /**
     * 同城运输 用户取消报价
     * @param handleOrderPriceReq 请求对象
     * @return 用户取消报价返回
     */
    MsDubboResBean cancleOrderPrice(HandleOrderPriceReq handleOrderPriceReq);



    /**
     * 同城快递、同城专送 用户取消订单
     * @param handleCleOrderReq 请求对象
     * @return 用户取消报价返回
     */
    MsDubboResBean cancleOrder4KDZS(HandleCleOrderReq handleCleOrderReq);

}
