package com.gistandard.transport.order.module.mistation.operate.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiAssignFleetReq;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiConfirmOrderReq;
import com.gistandard.transport.order.module.mistation.operate.service.bean.MiQueryOrderDetailResult;
import com.gistandard.transport.order.module.mobilestation.bean.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by m on 2016/10/13.
 */
public interface MobileOrderOperateService {

    /**
     * 咪站-分拣出库
     * @param mobileStatusOperateReq
     * @return
     * @throws Exception
     */
    AppBaseResult sortStockOut(MobileStatusOperateReq mobileStatusOperateReq) throws Exception;

    /**
     * 咪站-派车(或广播派车)
     * @param mobileOrderAssignReq
     * @return
     * @throws Exception
     */
    AppBaseResult miOrderAssign(MobileOrderAssignReq mobileOrderAssignReq) throws Exception;

    /**
     * 咪站指派校验接口(批量优化)(或广播)
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param batchMobileOrderAssignReq
     * @return
     * @throws MobileStationBizException
     */
    CheckAssignOrderforbatchResult checkAssignOrderforbatch(BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws MobileStationBizException;

    /**
     * 咪站-批量派车(或广播派车)
     * @param checkAssignOrderforbatchSuccesses
     * @return
     * @throws Exception
     */
    CheckAssignOrderforbatchResult doBatchMiOrderAssign(List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> checkAssignOrderforbatchSuccesses,
                                                        BatchMobileOrderAssignReq batchMobileOrderAssignReq) throws Exception;

    /**
     * 咪站广播单继续广播接口
     * <p>Title: assignOrder</p>
     * <p>Description: </p>
     *
     * @param broadcastOrderReq
     * @return
     * @throws MobileStationBizException
     */
    void broadcastOrder(BroadcastOrderReq broadcastOrderReq) throws MobileStationBizException;

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

    /**
     * 重新指派
     * <p>Title: cancleAssign</p>
     * <p>Description: </p>
     *
     * @param batchCancleAssignReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult batchCancleAssign(BatchCancleAssignReq batchCancleAssignReq) throws MobileStationBizException;

    /**
     * 取消广播
     * <p>Title: cancleAssign</p>
     * <p>Description: </p>
     *
     * @param cacelMobileBroadcastOrderReq
     * @return
     * @throws MobileStationBizException
     */
    void cacelMobileBroadcastOrder(CacelMobileBroadcastOrderReq cacelMobileBroadcastOrderReq) throws MobileStationBizException;

    /**
     * 分拣出库打印订单详细查看
     * <p>Title: cancleAssign</p>
     * <p>Description: </p>
     *
     * @param queryOrderDetailReq
     * @return
     * @throws MobileStationBizException
     */
    MiQueryOrderDetailResult queryOrderDetail(QueryOrderDetailReq queryOrderDetailReq) throws MobileStationBizException, InvocationTargetException, IllegalAccessException;

    /**
     * 客户自提
     * <p>Title: cancleAssign</p>
     * <p>Description: </p>
     *
     * @param getGoodsMySelfReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult getGoodsMySelf(GetGoodsMySelfReq getGoodsMySelfReq) throws MobileStationBizException;

    /**
     * 咪站确认车队报价
     *
     * @param miConfirmOrderReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult miConfirmOrder(MiConfirmOrderReq miConfirmOrderReq) throws MobileStationBizException;

    /**
     * 咪站本地交接给蛙站
     *
     * @param miConfirmOrderReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult miLocalHandover(MiConfirmOrderReq miConfirmOrderReq) throws MobileStationBizException;

    /**
     * 咪站-指派车队 车队强制接单
     *
     * @param miAssignFleetReq
     * @return
     * @throws MobileStationBizException
     */
    AppBaseResult miAssignFleet(MiAssignFleetReq miAssignFleetReq) throws MobileStationBizException;

}
