package com.gistandard.transport.order.module.workcenter.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationOrderListBean;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterMapListBean;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterMapListReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xgw
 * @ClassName WorkCenterDao
 * @Description MS3.0 订单管理-我的订单
 * @Version 3.0
 * @Date 2016-12-12
 */
@MyBatisRepository
public interface WorkCenterDao {

    /**
     * MS3.0
     * 工作中心列表查询 查询已接单、待收货、待派送
     * @param workCenterMapListReq
     * @return
     */
    List<WorkCenterMapListBean> queryMapOrderList(WorkCenterMapListReq workCenterMapListReq);

    /**
     * MS3.0
     * 工作中心列表查询 根据订单ID查询已接单、待收货、待派送
     * @param orderIdList
     * @return
     */
    List<MobileStationOrderListBean> queryOrderList(@Param("orderIdList") List<Integer> orderIdList);

}
