package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.app.dubbo.order.bean.MobileAssignOrderListReq;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.MobileOrderDetailBean;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.MobileOrderListReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.MobileSingleCenter;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationOrderListBean;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.MobileOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xgw
 * @ClassName MobileOrderDao
 * @Description MS3.0 订单管理-我的订单
 * @Version 3.0
 * @Date 2016-12-12
 */
@MyBatisRepository
public interface MobileOrderDao {

    /**
     * MS3.0
     * 查询我的订单总条数（全部订单、待执行、执行中、失败单）
     *
     * @param mobileOrderListReq
     * @return
     */
    int getOrderListCount(MobileOrderListReq mobileOrderListReq);

    /**
     * MS3.0
     * 查询我的订单列表（全部订单、待执行、执行中、失败单）
     *
     * @param mobileOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryOrderList(MobileOrderListReq mobileOrderListReq);

    /**
     * MS3.0
     * 查询咪站商户订单总条数（全部订单、待执行、执行中、失败单）
     *
     * @param mobileOrderListReq
     * @return
     */
    int getMiOrderListCount(MobileOrderListReq mobileOrderListReq);

    /**
     * MS3.0
     * 查询咪站商户订单列表（全部订单、待执行、执行中、失败单）
     *
     * @param mobileOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryMiOrderList(MobileOrderListReq mobileOrderListReq);

    /**
     * 查询订单详细信息（用户订单、商户订单）
     *
     * @param orderId
     * @return
     */
    MobileOrderDetailBean queryOrderDetail(Integer orderId);

    /**
     * MS3.0根据订单ID获取状态正常的转单记录
     *
     * @param orderId
     * @return
     */
    MobileSingleCenter querySingleCenter(@Param("orderId") Integer orderId, @Param("busiCtrl") Integer busiCtrl, @Param("createUserId") Integer createUserId);

    /**
     * MS3.0
     * 查询已指派订单总条数
     *
     * @param mobileAssignOrderListReq
     * @return
     */
    int getAssignOrderListCount(MobileAssignOrderListReq mobileAssignOrderListReq);

    /**
     * MS3.0 商管中心
     * 查询已指派订单列表
     *
     * @param mobileAssignOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryAssignOrderList(MobileAssignOrderListReq mobileAssignOrderListReq);

    /**
     * MS3.0 支付平台
     * 根据订单号列表查询订单信息
     *
     * @param busiBookNoList
     * @return
     */
    List<BookingForm> queryBookingFormList(@Param("busiBookNoList") List<String> busiBookNoList);

    /**
     * MS3.0
     * 根据订单号查询I单结算列表订单信息
     *
     * @param busiBookNo
     * @return
     */
    List<MobileOrderInfo> queryICalcOrderList(@Param("busiBookNo") String busiBookNo);

    /**
     * MS3.0
     * 根据子订单订单号查询I单结算派车单订单信息
     *
     * @param busiBookNo
     * @return
     */
    List<MobileOrderInfo> queryICalcScheduOrderList(@Param("busiBookNo") String busiBookNo);
}
