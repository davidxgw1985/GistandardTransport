package com.gistandard.transport.order.module.customer;

import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.*;

/**
 * 同城运输专属下单业务接口类
 * @author 员伟
 * @createDate 2017-06-15 8:55
 */
public interface TransportOrderService {


    /**
     * 同城运输专属模块下单
     * @param placeAnOrderReq 请求参数
     * @return PlaceAnOrderRes
     * @throws MobileStationBizException MobileStationBizException
     */
    PlaceAnOrderRes placeAnOrder(PlaceAnOrderReq placeAnOrderReq) throws MobileStationBizException;


    /**
     * 同城运输下单用户下单后,确认报价
     * @param quotePriceReq 请求参数
     * @return PlaceAnOrderRes
     * @throws MobileStationBizException MobileStationBizException
     */
    QuotePriceRes handleQuotePrice(QuotePriceReq quotePriceReq) throws MobileStationBizException;



    /**
     * 用户或者咪站取消订单
     * @param cancleOrderReq cancleOrderReq
     * @return CancleOrderRes
     * @throws MobileStationBizException MobileStationBizException
     */
    CancleOrderRes handleCancleOrder(CancleOrderReq cancleOrderReq) throws MobileStationBizException;

}
