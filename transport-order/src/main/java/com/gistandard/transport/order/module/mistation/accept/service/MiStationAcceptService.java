package com.gistandard.transport.order.module.mistation.accept.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationAcceptOrderCustomResult;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationAcceptOrderReq;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationBatchAcceptOrderReq;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStatusOperateReq;

/**
 * @author xgw
 * @ClassName MiStationAcceptService
 * @Description 咪站接单模块服务
 * @Version 1.0
 * @Date 2016-10-11
 */
public interface MiStationAcceptService {

	/**
	 * 接单操作
	 *
	 * @param acceptOrderReq
	 * @return
	 */
	AppBaseResult acceptOrder(MobileStationAcceptOrderReq acceptOrderReq)
			throws MobileStationBizException;

	/**
	 * 接单操作
	 *
	 * @param acceptOrderReq
	 * @return
	 */
	MobileStationAcceptOrderCustomResult batchAcceptOrder(MobileStationBatchAcceptOrderReq acceptOrderReq)
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
	 * 商管批量指派咪站
	 *
	 * @param acceptOrderReq
	 * @return
	 */
	MobileStationAcceptOrderCustomResult batchAssignMiOrder(MobileStationBatchAcceptOrderReq acceptOrderReq)
			throws MobileStationBizException;
}
