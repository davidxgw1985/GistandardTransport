package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.app.dubbo.order.bean.MobileStationOrderDetailBean;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import com.gistandard.transport.base.entity.bean.OrderCustomInfo;
import com.gistandard.transport.base.entity.bean.PushRole;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.stock.SubBusiBookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xgw
 * @ClassName MobileStationOrderDao
 * @Description
 * @Version 1.0
 * @Date 2015-08-20
 */
@MyBatisRepository
public interface MobileStationOrderDao {

    /**
     * 查询订单总条数（站点指派单、个人指派单）
     * @param orderListReq
     * @return
     */
    int getOrderListCount(MobileStationOrderListReq orderListReq);

    /**
     * 查询订单列表（站点指派单、个人指派单）
     * @param orderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryOrderList(MobileStationOrderListReq orderListReq);

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    MobileStationOrderDetailBean queryOrderDetail(Integer orderId);

    /**
     * 查询订单详细信息
     * @param scheducarno
     * @return
     */
    MobileStationOrderDetailBean queryOrderDetailByScheducarno(String scheducarno);

    /**
     * 查询货物信息
     * @param getGoodsInfoReq
     * @throws Exception
     */
    GoodsStockInfo getMobileGoodsInfo(GetGoodsInfoReq getGoodsInfoReq);

    /**
     * 查询货物列表信息
     * @param orderDetailReq
     * @return
     */
    List<MobileGoodsDtl> queryGoodsList(MobileStationOrderDetailReq orderDetailReq);

    /**
     * 查询货物列表信息(从业务数据从表中查询)
     * @param orderId
     * @return
     */
    List<MobileGoodsDtl> queryGoodsDtlList(@Param("orderId") Integer orderId);

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
     * 查询待执行订单总条数（取件单、派件单）
     * @param executeOrderListReq
     * @return
     */
    int getExecuteOrderListCount(MobileStationExecuteOrderListReq executeOrderListReq);

    /**
     * 查询待执行订单列表
     * @param executeOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryExecuteOrderList(MobileStationExecuteOrderListReq executeOrderListReq);

    /**
     * 获取已完成和失败订单总条数（取件单、派件单）
     * @param queryFinishOrderReq
     * @return
     */
    int getFinishOrderListCount(QueryFinishOrderReq queryFinishOrderReq);

    /**
     * 获取已完成和失败订单列表
     * @param queryFinishOrderReq
     * @return
     */
    List<MobileStationOrderListBean> queryFinishOrderList(QueryFinishOrderReq queryFinishOrderReq);

    /**
     * 根据派车单编号查询订单列表
     * @param queryScheduOrderInfoReq
     * @return
     */
    List<MobileStationOrderListBean> queryScheduOrderList(QueryScheduOrderInfoReq queryScheduOrderInfoReq);

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
     * 司机送达确认成功修改订单表
     * @param orderOperateBean
     * @return
     */
    int driverUpdateBookingFormStatus(MobileOrderOperateBean orderOperateBean);

    /**
     * 修改订单表状态
     * @param orderOperateBean
     * @return
     */
    int updateBookingFormStatus2(MobileOrderOperateBean2 orderOperateBean);

    /**
     * 修改订单表状态
     * @param orderOperateBean
     * @return
     */
    int updateBookingFormStatusByBusiNo(MobileOrderOperateBean orderOperateBean);

    /**
     * 修改其他派车单状态
     * @param orderOperateBean
     * @return
     */
    int updateScheduBookingFormStatus(MobileOrderOperateBean orderOperateBean);

    /**
     * 放弃订单，修改订单表状态
     * @param orderOperateBean
     * @return
     */
    int giveUpOrder(MobileOrderOperateBean orderOperateBean);

    /**
     * 获取订单地址信息
     *
     * @param busiNo
     * @param customType
     * @return
     */
    OrderCustomInfo queryOrderCustomInfoByBusiNo(@Param("busiNo") String busiNo, @Param("customType") Integer customType);

    /**
     * 根据派车单号查询MobileBookingForm
     * @param scheducarno
     * @return
     */
    List<MobileBookingForm> getMobileBookingFormByScheducarno(@Param("scheducarno") String scheducarno);

    /**
     * 根据签派号查询MobileBookingForm
     * @param dispatchId
     * @return
     */
    List<MobileBookingForm> getMobileBookingFormByDispatchId(@Param("dispatchId") Integer dispatchId);

    /**
     * 根据订单号查询自己的订单信息
     * @param busiBookNo
     * @param acctUsername
     * @return
     */
    List<MobileBookingForm> getMobileBookingFormByBusiBookNo(@Param("busiBookNo") String busiBookNo,
                                                             @Param("acctUsername") String acctUsername,
                                                             @Param("revCompanyId") Integer revCompanyId);

    /**
     * 根据订单号查询自己的订单信息（订单状态为已接单）
     * @param busiBookNo
     * @param acctUsername
     * @return
     */
    List<MobileBookingForm> getMobileBookingFormByBusiBookNo2(@Param("busiBookNo") String busiBookNo,
                                                              @Param("acctUsername") String acctUsername,
                                                              @Param("revCompanyId") Integer revCompanyId,
                                                              @Param("roleId") Integer roleId);

    /**
     * 查询货物列表信息(从业务数据从表中查询)
     * @param scheducarno
     * @return
     */
    List<MobileGoodsDtl> queryGoodsDtlListByScheducarno(@Param("scheducarno") String scheducarno);

    /**
     * 根据派车单号查询派车数据
     * @param scheducarno
     * @return
     */
    List<MobileBookingForm> getTransportDetailByScheducarno(@Param("scheducarno") String scheducarno);

    /**
     * 根据派车单号查询子订单信息列表
     *
     * @param scheducarno
     * @throws Exception
     */
    List<SubBusiBookInfo> querySubBusiBookInfoListByScheducarno(@Param("scheducarno") String scheducarno);
    /**
     * 子派单接单
     * @param orderOperateBean
     * @return
     */
	int acceptSubOrder(MobileOrderOperateBean orderOperateBean);
}
