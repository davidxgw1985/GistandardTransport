package com.gistandard.transport.order.wechat.dao;

import com.gistandard.transport.app.dubbo.wechat.bean.OrderQueryDetail;
import com.gistandard.transport.app.dubbo.wechat.bean.WeChatOrderQueryReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;

import java.util.List;

/**
 * Created by m on 2017/2/7.
 */
@MyBatisRepository
public interface WeChatOrderDao {
    /**
     * 我的订单列表
     * @param weChatOrderQueryReq
     * @return
     */
    List<OrderQueryDetail> queryOrderListByWeChatId(WeChatOrderQueryReq weChatOrderQueryReq);
}
