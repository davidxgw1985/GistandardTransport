package com.gistandard.transport.app.dubbo.wechat.service;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.wechat.bean.*;

/**
 * Created by yjf on 2017/2/6.
 */
public interface WeChatOrderDubboService {

    /**
     * 微信预约下单保存--总方法
     * @param weChatOrderReq
     * @return
     */
    WeChatOrderRes saveWechatOrder(WeChatOrderReq weChatOrderReq);

    /**
     * 下单保存
     * @param req
     * @return
     */
    WeChatOrderRes makeOrder(WeChatOrderReq req);

    /**
     * 更新路由
     * @param orderId
     */
    MsDubboResBean updateOrderRouteId(String orderId) ;

    /**
     * 通知gps
     * @param orderId
     * @param errOrder
     */
    void notifyGPS(Integer orderId,Boolean errOrder) ;

    /**
     * 取消下单
     * @param orderId
     * @return
     */
    Integer cancleWechatOrder(Integer orderId);

    /**
     * 我的订单列表
     * @param weChatOrderQueryReq
     * @return
     */
    WeChatOrderQueryRes queryOrderListByWeChatId(WeChatOrderQueryReq weChatOrderQueryReq);

    /**
     * 根据单号查询订单跟踪信息
     * @param weChatOrderTraceReq
     * @return
     */
    WeChatOrderTracRes queryOrderTraceByBusiNo(WeChatOrderTraceReq weChatOrderTraceReq);

    /**
     * 微信签收订单
     * @param signOrderReq
     * @return
     */
    MsDubboResBean signOrder(SignOrderReq signOrderReq);
}
