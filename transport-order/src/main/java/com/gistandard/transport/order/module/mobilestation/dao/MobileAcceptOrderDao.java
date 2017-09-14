package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.app.dubbo.order.bean.MobileStationOrderDetailBean;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.PushRole;
import com.gistandard.transport.order.module.mobilestation.bean.*;

import java.util.List;

/**
 * @author xgw
 * @ClassName MobileAcceptOrderDao
 * @Description MobileStation3.0 接单模块订单操作
 * @Version 3.0
 * @Date 2016-06-14
 */
@MyBatisRepository
public interface MobileAcceptOrderDao {

    /**
     * 查询订单总条数（站点指派单、个人指派单）
     * @param orderListReq
     * @return
     */
    int getOrderListCount(MobileAcceptOrderListReq orderListReq);

    /**
     * 查询订单列表（站点指派单、个人指派单）
     * @param orderListReq
     * @return
     */
    List<MobileAcceptOrderQueryListBean> queryOrderList(MobileAcceptOrderListReq orderListReq);

    /**
     * 查询抢单列表总条数（个人广播单、MS广播单）
     * @param grabOrderListReq
     * @return
     */
    int getGrabOrderListCount(MobileGrabOrderListReq grabOrderListReq);

    /**
     * 查询抢单列表列表（站点指派单、个人指派单）
     * @param grabOrderListReq
     * @return
     */
    List<MobileAcceptOrderQueryListBean> queryGrabOrderList(MobileGrabOrderListReq grabOrderListReq);

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    MobileStationOrderDetailBean queryOrderDetail(Integer orderId);
    /**
     * 查询子订单详细信息
     * @param subOrderId
     * @return
     */
    MobileAcceptSubOrderDetailBean querySubOrderDetail(Integer subOrderId);

    /**
     * 接单
     *
     * @param orderId
     * @param accountId
     * @return
     */
    int acceptOrder(Integer orderId, Integer accountId);

    /**
     * 查询推送规则
     * @param accountId
     * @return
     */
    PushRole getPushRule(Integer accountId);

    /**
     * 接单
     * @param orderOperateBean
     * @return
     */
    int acceptOrder(MobileOrderOperateBean orderOperateBean);

    /**
     * 修改订单表状态
     * @param orderOperateBean
     * @return
     */
    int updateBookingFormStatus(MobileOrderOperateBean orderOperateBean);

    /**
     * 修改订单表状态
     * @param orderOperateBean
     * @return
     */
    int updateBookingFormStatusByBusiNo(MobileOrderOperateBean orderOperateBean);

    /**
     * 放弃订单，修改订单表状态
     * @param orderOperateBean
     * @return
     */
    int giveUpOrder(MobileOrderOperateBean orderOperateBean);
    /**
     * 查询子订单列表
     * @param acceptSubOrderListReq
     * @return
     */
	List<MobileAcceptSubOrderQueryListBean> querySubOrderList(MobileStationOrderDetailReq acceptSubOrderListReq);
	/**
	 * 查询派车单详情
	* <p>Title: queryMainOrderDetail</p>
	* <p>Description: </p>
	* @param orderId
	* @return
	 */
	MobileAcceptOrderDetailBean queryScheducarOrderDetail(Integer orderId);
	/**
	 * 根据派车单号查询子订单
	* <p>Title: querySubOrderListByScheducarno</p>
	* <p>Description: </p>
	* @param scheducarno
	* @return
	 */
	List<MobileAcceptSubOrderDetailBean> querySubOrderListWithGoodsByScheducarno(String scheducarno);
	
	/**
	 * 根据派车单号查询子订单
	* <p>Title: querySubOrderListByScheducarno</p>
	* <p>Description: </p>
	* @param acceptSubOrderListReq
	* @return
	 */
	List<MobileAcceptSubOrderDetailBean> querySubOrderListWithGoods(MobileStationOrderDetailReq acceptSubOrderListReq);

}
