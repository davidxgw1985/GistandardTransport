package com.gistandard.transport.order.webservice.server.mobilestation.service;


import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 * @author yjf
 * @ClassName MobileStationWebService
 * @Description
 * @Version 1.0
 * @Date 2016-03-09
 */
public interface MobileStationWebService {
	
	 /**
     * mobilestation3.0接单(包含I单和O单的逻辑)
     * @param recordMobileStationOrderRequest
     * @return
     */
    @WebMethod(operationName = "receiveMobileStationOrder")
    @WebResult(name = "receiveMobileStationOrderResult")
    String receiveMobileStationOrder(@WebParam(name = "receiveMobileStationOrderRequest") RecordMobileStationOrderRequest recordMobileStationOrderRequest);
	
	 /**
     * mobilestation3.0下单(包含I单和O单的逻辑)
     * @param recordMobileStationOrderRequest
     * @return
     */
    @WebMethod(operationName = "recordMobileStationOrder")
    @WebResult(name = "recordMobileStationOrderResult")
    String recordMobileStationOrder(@WebParam(name = "recordMobileStationOrderRequest") RecordMobileStationOrderRequest recordMobileStationOrderRequest);

	 /**
     * mobilestation3.0下订单W站指派给M站(非派车单)
     * @param recordMWMobileStationOrderRequest
     * @return
     */
    @WebMethod(operationName = "recordMWMobileStationOrder")
    @WebResult(name = "recordMWMobileStationOrderResult")
    String recordMWMobileStationOrder(@WebParam(name = "recordMWMobileStationOrderRequest") RecordMWMobileStationOrderRequest recordMWMobileStationOrderRequest);

    /**
     * 移动station下单
     * @param mobileStationOrderBean
     * @return
     */
    @WebMethod(operationName = "makerMobileStationOrder")
    @WebResult(name = "makerMobileStationOrderResult")
    String  makerMobileStationOrder(@WebParam(name = "mobileStationOrderBean") MobileStationOrderBean mobileStationOrderBean);

    /**
     * 获取订单地址信息
     * @param mobileStationOrderTransportBean
     * @return
     */
    @WebMethod(operationName = "makerMobileStationOrderTransport")
    @WebResult(name = "makerMobileStationOrderTransportResult")
    String  makerMobileStationOrderTransport(@WebParam(name = "mobileStationOrderTransportBean") MobileStationOrderTransportBean mobileStationOrderTransportBean);

    /**
     * 移动station取消下单
     * @param mobileStationCancelOrderBean
     * @return
     */
    @WebMethod(operationName = "cancelMobileStationOrderTransport")
    @WebResult(name = "cancelMobileStationOrderTransportResult")
    String  cancelMobileStationOrderTransport(@WebParam(name = "mobileStationCancelOrderBean") MobileStationCancelOrderBean mobileStationCancelOrderBean);

    /**
     * 移动station取消下单(签派)
     * @param mobileStationCancelOrderBean
     * @return
     */
    @WebMethod(operationName = "cancelMobileStationOrderDispatch")
    @WebResult(name = "cancelMobileStationOrderDispatchResult")
    String  cancelMobileStationOrderDispatch(@WebParam(name = "mobileStationCancelOrderBean") MobileStationCancelOrderBean mobileStationCancelOrderBean);

    /**
     * 移动station下单
     * @param getOrderAddressInfoReq
     * @return
     */
    @WebMethod(operationName = "getOrderAddressInfo")
    @WebResult(name = "getOrderAddressInfoResult")
    GetOrderAddressInfoRes getOrderAddressInfo(@WebParam(name = "getOrderAddressInfoReq") GetOrderAddressInfoReq getOrderAddressInfoReq);

    /**
     * 我要接单，查询订单
     * @param mobileWantReq
     * @return
     */
    @WebMethod(operationName = "getMobileWantOrderInfo")
    @WebResult(name = "getMobileWantOrderInfoResult")
    String getMobileWantOrderInfo(@WebParam(name = "mobileWantReq") MobileWantReq mobileWantReq);

    /**
     * 我要运输，查询订单
     * @param mobileWantSjReq
     * @return
     */
    @WebMethod(operationName = "getMobileWantOrderSjInfo")
    @WebResult(name = "getMobileWantOrderSjInfoResult")
    String getMobileWantOrderSjInfo(@WebParam(name = "mobileWantSjReq") MobileWantSjReq mobileWantSjReq);

    /**
     * 结算完成后更新MobileBookingForm的结算对账单号
     * @param updateValidBillNoReq
     * @return
     */
    @WebMethod(operationName = "updateValidBillno")
    @WebResult(name = "updateValidBillnoRes")
    AppBaseResult updateValidBillno(@WebParam(name = "updateValidBillNoReq") UpdateValidBillNoReq updateValidBillNoReq);

}
