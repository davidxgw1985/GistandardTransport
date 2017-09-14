package com.gistandard.transport.order.wechat.dao;

import com.gistandard.transport.app.dubbo.wechat.bean.OrderTraceDetail;
import com.gistandard.transport.app.dubbo.wechat.bean.WeChatOrderTraceReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by m on 2017/2/7.
 */
@MyBatisRepository
public interface WeChatOrderTraceDao {
    /**
     * 根据单号查询订单跟踪信息
     * @param weChatOrderTraceReq
     * @return
     */
    List<OrderTraceDetail> queryOrderTraceByBusiNo(WeChatOrderTraceReq weChatOrderTraceReq);

}
