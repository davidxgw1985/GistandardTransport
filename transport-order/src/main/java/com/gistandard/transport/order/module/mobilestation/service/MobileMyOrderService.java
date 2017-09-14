package com.gistandard.transport.order.module.mobilestation.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderDetailReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.QueryMyOrderDetailResult;
import com.gistandard.transport.app.dubbo.order.bean.QueryMyOrderListResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.BatchAssignReq;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author xgw
 * @ClassName MobileMyOrderService
 * @Description 我的订单 接口
 * @Version 1.0
 * @Date 2016/6/7
 */
public interface MobileMyOrderService {


    /**
     * MS3.0
     * 获取订单管理-我的订单列表
     *
     * @param myOrderListReq
     * @throws Exception
     */
    QueryMyOrderListResult queryMyOrderList(MobileMyOrderListReq myOrderListReq) throws MobileStationBizException;

    /**
     * MS3.0
     * 获取订单管理-我的订单详细
     *
     * @param myOrderDetailReq
     * @throws Exception
     */
    QueryMyOrderDetailResult queryMyOrderDetail(MobileMyOrderDetailReq myOrderDetailReq) throws MobileStationBizException;

    /**
     * MS3.0
     * 获取订单管理-根据扫描单号查询订单详情
     *
     * @param queryOrderDetailReq
     * @throws Exception
     */
    SMQueryOrderDetailResult queryOrderDetail(QueryOrderDetailReq queryOrderDetailReq) throws MobileStationBizException, InvocationTargetException, IllegalAccessException;

    /**
     * MS3.0放弃订单
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult giveUpOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0发车
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult depart(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0 -订单失败
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult orderFailure(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * MS3.0确认送达
     *
     * @param mobileStatusOperateReq
     * @return
     */
    AppBaseResult deliveryConfirm(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    /**
     * 插入跟踪日志
     *
     * @param waybillTraceOperateBean
     * @throws Exception
     */
    void insertWaybillTrace(WaybillTraceOperateBean waybillTraceOperateBean);

    /**
     * 批量更新货物信息
     * <p>Title: batchModifyGoodsInfo</p>
     * <p>Description: </p>
     *
     * @param batchUpdateGoodsInfoReq
     * @return
     */
    BatchModifyGoodsInfoResult batchModifyGoodsInfo(BatchUpdateGoodsInfoReq batchUpdateGoodsInfoReq) throws MobileStationBizException;

    /**
     * MS指派(或广播)
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param mobileOrderAssignReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult assignOrder(MobileOrderAssignReq mobileOrderAssignReq) throws MobileStationBizException;

    /**
     * MS校验指派(批量优化)(或广播)
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param batchMobileOrderAssignReq
     * @return
     * @throws MobileStationBizException
     */
    CheckAssignOrderforbatchResult checkAssignOrderforbatch(BatchMobileOrderAssignReq batchMobileOrderAssignReq);

    /**
     * MS指派(批量优化)(或广播)
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param checkAssignOrderforbatchSuccesses
     * @return
     * @throws MobileStationBizException
     */
    CheckAssignOrderforbatchResult doBatchMobileOrderAssign(List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> checkAssignOrderforbatchSuccesses,
                                                            BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws MobileStationBizException;

    /**
     * MS广播单继续广播接口
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param broadcastOrderReq
     * @return
     * @throws MobileStationBizException
     */
    void broadcastOrder(BroadcastOrderReq broadcastOrderReq) throws MobileStationBizException;

    /**
     * MS广播单取消订单
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param cacelMobileBroadcastOrderReq
     * @return
     * @throws MobileStationBizException
     */
    void cacelMobileBroadcastOrder(CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq) throws MobileStationBizException;

    /**
     * 重新指派
     * <p>Title: cancleAssign</p>
     * <p>Description: </p>
     *
     * @param mobileStatusOperateReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult cancleAssign(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException;

    AppBaseResult hubAssignOrder(BatchAssignReq batchAssignReq) throws MobileStationBizException;

    /**
     * MS指派MS送达确认接口，当startLocus为POM，destLocus与原始单相同时，送达确认
     *
     * @param deliVeryConfirmReq
     * @return
     * @throws MobileStationBizException
     */
    int deliveryConfirmFromMS(DeliVeryConfirmReq deliVeryConfirmReq) throws MobileStationBizException;

    /**
     * 在送达时调用解冻接口
     *
     * @param busiBookNo
     * @param scheducarno
     * @return
     * @throws MobileStationBizException
     */
    int confirmPayAtMS(String busiBookNo, String scheducarno) throws MobileStationBizException;
    /**
     * 异常收件
    * <p>Title: sendMsg</p>
    * <p>Description: </p>
    * @param req
    * @return
     */
    void exceptionReceive(ExceptionReceiveReq req) throws MobileStationBizException;

    /**
     * 发送IM消息
    * <p>Title: sendMsg</p>
    * <p>Description: </p>
    * @param msgIMReq
    * @return
     */
    BaseResBean pushMessageIM(MsgIMReq msgIMReq);

    /**
     * 发送IM消息
     * @param msgIMReq
     */
    void sendMsg(MsgIMReq msgIMReq);

}
