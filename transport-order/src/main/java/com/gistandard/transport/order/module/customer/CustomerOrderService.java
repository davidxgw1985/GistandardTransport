package com.gistandard.transport.order.module.customer;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.*;

import java.util.List;

/**
 * Created by yjf on 2016/9/30.
 */
public interface CustomerOrderService {
    /**
     * 客户下订单
     *
     * @param req
     */
    PlaceAnOrderRes placeAnOrder(PlaceAnOrderReq req) throws MobileStationBizException;

    /**
     * 客户下错误地址订单
     *
     * @param req
     */
    PlaceAnOrderRes placeAnErrOrder(PlaceAnOrderReq req) throws MobileStationBizException;

    /**
     * 订单查询
     *
     * @return
     */
    List<OrderQueryRes> queryList(OrderQueryReq req) throws MobileStationBizException;


    /**
     * 根据订单号查询订单
     * @return  List<OrderQueryRes>
     */
    List<OrderQueryRes> queryOrderByBusiNo(OrderQueryReq req) throws MobileStationBizException;


    /**
     * 同城专送和同城快递取消订单
     * @param req req
     * @throws MobileStationBizException MobileStationBizException
     */
    void cancelOrder4KDZS(CancleOrderReq req) throws MobileStationBizException;
    /**
     *
     * 记录数合计
     *
     * @param req
     * @return
     */
    int count(OrderQueryReq req);


    /**
     *
     * 订单更新
     *
     * @param req
     */
    void update(OrderUpdateReq req) throws MobileStationBizException;

    /**
     *
     * 订单关注
     *
     * @param req
     */
    void follow(OrderFollowReq req) throws MobileStationBizException;

    /**
     *
     * 订单关注取消
     *
     * @param req
     */
    void cancelFollow(OrderFollowReq req) throws MobileStationBizException;

    /**
     *
     * 广播单查询接口
     *
     * @param req
     */
    OrderQueryRes queryBroadcastOrder(ReceiveCustomerOrderReq req) throws Exception;

    /**
     *
     * 广播单接单接口
     *
     * @param req
     */
    boolean receiveBroadcastOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException;

    /**
     *
     * 广播单接单接口
     *
     * @param req
     */
    void cacelBroadcastOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException;

    /**
     * 广播单广播接口
     * @param req
     * @throws MobileStationBizException
     */
    void broadcastOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException;

    /**
     * 客户下单接单接口(包含广播单和指派单逻辑)
     * @title receiveCustomerOrder
     * @param req
     * @return
     * @throws MobileStationBizException
     * @author M.simple
     * @version 1.0
     * @datetime 2016年7月5日 下午5:59:32
     */
    boolean receiveCustomerOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException;

    /**
     * 校验是否可以投保
     * @param goodsTypeBean
     * @return
     */
    boolean checkInsured(GoodsTypeBean goodsTypeBean) throws MobileStationBizException;

    /**
     * 调用太保支付接口
     * @param insuredPayReq
     * @return
     */
    InsuredPayRes goInsuredPay(InsuredPayReq insuredPayReq)throws MobileStationBizException;

    /**
     * 投保支付成功
     * @param req
     * @throws MobileStationBizException
     */
    void paySuccessed(ReceiveCustomerOrderReq req)throws MobileStationBizException;

    /**
     * 取消投保继续下单
     * @param req
     * @throws MobileStationBizException
     */
    void cancelInsuredContinueOrder(ReceiveCustomerOrderReq req) throws MobileStationBizException;

    /**
     * 投保查询接口
     * @param queryPolicyReq
     * @return
     */
    QueryPolicyRes queryPolicy(QueryPolicyReq queryPolicyReq)throws MobileStationBizException;

    /**
     * 退保
     * @param busiNo
     */
    void withdrawInsure(String busiNo)throws  MobileStationBizException;

    /**
     * 货物确认核保接口
     */
    boolean orderPolicy(OrderPolicyBean req) throws MobileStationBizException;

    /**
     * 客户下单APP编辑保单
     */
    AppBaseResult updateOrderPolicy(UpdateOrderPolicyReq req) throws MobileStationBizException;

    /**
     * 修改一口价
     */
    AppBaseResult orderOnePrice(QuoteOnePriceReq quoteInfoReq) throws MobileStationBizException;

    /**
     * 把平台报价数据设入MobileBookingForm
     * @param busiBookNo
     * @return
     * @throws MobileStationBizException
     */
    void setQuote2MobileBooingForm(String busiBookNo) throws MobileStationBizException;

    /**
     * 投保查询接口
     * @param queryPolicyReq
     * @return
     */
    QueryPolicyRes queryPolicyForAndroid(QueryPolicyReq queryPolicyReq)throws MobileStationBizException;

    /**
     * 通知gps
     * @param placeAnOrderRes
     * @param errOrder
     */
    void notifyGps(PlaceAnOrderRes placeAnOrderRes,Boolean errOrder);
}
