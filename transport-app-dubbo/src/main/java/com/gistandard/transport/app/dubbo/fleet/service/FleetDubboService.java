package com.gistandard.transport.app.dubbo.fleet.service;

import com.gistandard.transport.app.dubbo.fleet.bean.*;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * @author: xgw
 * @ClassName: FleetDubboService
 * @Date: 2017/6/21
 * @Description: 车队系统dubbo接口
 */
public interface FleetDubboService {

    /**
     * 车队系统-分发功能
     * 车队分发 同城运输O单、同城专送O单、同城运输I单 给下属司机
     * @param batchFleetDistributeReq batchFleetDistributeReq
     * @throws Exception Exception
     */
    BatchFleetDistributeRes batchFleetDistribute(BatchFleetDistributeReq batchFleetDistributeReq) throws Exception;

    /**
     * 车队竞价完成后,返回处理消息,并推送消息
     * <p>推送有无车队竞价消息,根据竞价结果,将订单状态插入订单表格中<p/>
     * @param req 请求对象
     * @return 车队竞价完成后返回结果推送消息到
     */
    MsDubboResBean receiveFleetQuotedMsg(FleetOrderMessageReq req);

    /**
     * 同城专送,车队接单
     * @param req 请求对象
     * @return 成功失败
     */
    MsDubboResBean fleetAcceptOrder(FleetAcceptOrderReq req);


    /**
     * ITCYS或OTCYSEX单车队取消订单
     * @param req 请求对象
     * @return 成功或者失败
     */
    MsDubboResBean fleetCancleOrder(FleetCancleOrderReq req);


}
