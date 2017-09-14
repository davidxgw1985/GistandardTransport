package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.order.module.mobilestation.bean.MoStationOrderDbQueryReq;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationDbOrder;

import java.util.List;


/**
 * Created by yujie on 2016/10/6.
 */
@MyBatisRepository
public interface MobileStationOrderDetailDao {

    /**
     * MS3.0订单详细数据
     *
     * @param mobileStationFormId
     * @return
     */
    MobileStationDbOrder queryStationOrderDetail(Integer mobileStationFormId);

    /**
     * MS3.0订单详细数据 - 获取订单列表总条数
     *
     * @param moStationOrderDbQueryReq
     * @return
     */
    int queryMobileStationOrderListCount(MoStationOrderDbQueryReq moStationOrderDbQueryReq);

    /**
     * MS3.0订单详细数据- 订单列表查询
     *
     * @param moStationOrderDbQueryReq
     * @return
     */
    List<MobileStationDbOrder> queryMobileStationOrderList(MoStationOrderDbQueryReq moStationOrderDbQueryReq);
}
