package com.gistandard.transport.order.async.order.mobileStation.service;


import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderListReq;

import java.util.concurrent.Future;

/**
 * Created by m on 2016/11/14.
 */
public interface MobileStationOrderQueryAsyncService {
    /**
     * MS3.0
     * 查询我的订单总条数（全部订单、待执行、执行中、失败单）
     * @param myOrderListReq
     * @return
     */
    Future<Integer> getMyOrderListCount(MobileMyOrderListReq myOrderListReq);

    Future<Integer> queryListCount(MobileUserOrderListReq mobileUserOrderListReq);
}
