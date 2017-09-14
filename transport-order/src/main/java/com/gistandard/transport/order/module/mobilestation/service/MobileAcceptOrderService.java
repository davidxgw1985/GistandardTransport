package com.gistandard.transport.order.module.mobilestation.service;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.BatchOperateReq;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.BatchOperateResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author xgw
 * @ClassName MobileStationOrderService
 * @Description
 * @Version 1.0
 * @Date 2016-01-12
 */
public interface MobileAcceptOrderService {

	/**
	 * 接单-指派单订单列表查询
	 * 
	 * @param acceptOrderListReq
	 * @throws MobileStationBizException
	 */
	QueryOrderListRes queryOrderList(MobileAcceptOrderListReq acceptOrderListReq)
			throws MobileStationBizException;

	/**
	 * 接单-抢单列表查询
	 *
	 * @param grabOrderListReq
	 * @throws MobileStationBizException
	 */
	QueryGrabOrderListRes queryGrabOrderList(MobileGrabOrderListReq grabOrderListReq)
			throws MobileStationBizException;

	/**
	 * 接单-订单详细查询
	 * 
	 * @param orderDetailReq
	 * @return
	 */
	QueryOrderDetailResult queryOrderDetail(MobileStationOrderDetailReq orderDetailReq)
			throws MobileStationBizException;

	/**
	 * 接单操作
	 * 
	 * @param acceptOrderReq
	 * @return
	 */
	AppBaseResult acceptOrder(MobileStationAcceptOrderReq acceptOrderReq)
			throws MobileStationBizException;

	/**
	 * 拒绝订单、取消订单
	 * 
	 * @param mobileStatusOperateReq
	 * @return
	 */
	AppBaseResult refuseOrder(MobileStatusOperateReq mobileStatusOperateReq)
			throws MobileStationBizException;

	/**
	 * 批量拒绝订单、取消订单
	 *
	 * @param batchRefuseOrderReq
	 * @return
	 */
	BatchRefuseOrderResult checkBatchRefuseOrder(BatchRefuseOrderReq batchRefuseOrderReq)
			throws MobileStationBizException;

	/**
	 * 批量拒绝订单、取消订单
	 *
	 * @param successes
	 * @return
	 */
	void doBatchRefuseOrder(List<CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>> successes)
			throws MobileStationBizException;

	/**
	 * 查询推送规则
	 * 
	 * @param baseReqBean
	 * @return
	 */
	QueryPushRuleResult queryPushRule(AppBaseRequest baseReqBean)
			throws MobileStationBizException, InvocationTargetException, IllegalAccessException;

	/**
	 * 设置推送规则
	 * 
	 * @param setPushRuleReq
	 * @return
	 */
	AppBaseResult setPushRule(MobileStationSetPushRuleReq setPushRuleReq)
			throws MobileStationBizException;

	/**
	 * 设置推送规则状态
	 * 
	 * @param setPushStatusReq
	 * @return
	 */
	AppBaseResult setPushStatus(SetPushStatusReq setPushStatusReq)
			throws MobileStationBizException;

	/**
	 * 查询派车单详情
	* <p>Title: queryScheducarOrderDetail</p>
	* <p>Description: </p>
	* @param orderDetailReq
	* @return
	* @throws MobileStationBizException
	 */
	BaseResBean queryScheducarOrderDetail(MobileStationOrderDetailReq orderDetailReq) throws MobileStationBizException;

	/**
	 * 批量接单操作
	 *
	 * @param acceptOrderReq
	 * @return
	 */
	MobileStationAcceptOrderCustomResult batchAcceptOrder(MobileStationBatchAcceptOrderReq acceptOrderReq)
			throws MobileStationBizException;

	/**
	 * 批量取消广播单
	 *
	 * @param batchOperateReq
	 * @return
	 */
	BatchOperateResult batchCancelOrder(BatchOperateReq batchOperateReq) throws MobileStationBizException;


	/**
	 * 获取商管指派单个数
	 *
	 * @param acctUserName
	 * @param roleId
	 * @return
	 */
	int getMerchantOrderCount(String acctUserName,Integer roleId);

	/**
	 * 商管批量指派快递员
	 *
	 * @param acceptOrderReq
	 * @return
	 */
	MobileStationAcceptOrderCustomResult batchAssignOrder(MobileStationBatchAcceptOrderReq acceptOrderReq)
			throws MobileStationBizException;
}
