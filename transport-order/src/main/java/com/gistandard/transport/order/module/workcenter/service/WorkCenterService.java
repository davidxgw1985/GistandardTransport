package com.gistandard.transport.order.module.workcenter.service;

import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterListReq;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterListResult;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterMapListReq;
import com.gistandard.transport.order.module.workcenter.bean.WorkCenterMapListResult;

/**
 * @author xgw
 * @ClassName MobileOrderService
 * @Description 订单管理 接口
 * @Version 1.0
 * @Date 2016/12/9
 */
public interface WorkCenterService {

    /**
     * MS3.0
     * 工作中心列表查询(地图)
     *
     * @param workCenterMapListReq
     * @throws Exception
     */
    WorkCenterMapListResult queryMapOrderList(WorkCenterMapListReq workCenterMapListReq) throws MobileStationBizException;

    /**
     * MS3.0
     * 工作中心列表查询(根据订单ID和订单号查询)
     *
     * @param workCenterListReq
     * @throws Exception
     */
    WorkCenterListResult queryOrderList(WorkCenterListReq workCenterListReq) throws MobileStationBizException;

}
