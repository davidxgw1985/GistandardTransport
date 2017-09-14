package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.app.dubbo.order.bean.MobileMyOrderListReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileStationOrderDetailBean;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.bean.MobileSingleCenter;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.bean.MobileAssignBean;
import com.gistandard.transport.order.module.mobilestation.bean.takeout.TakeOutMobileOrderListRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xgw
 * @ClassName MobileMyOrderDao
 * @Description MS3.0 订单管理-我的订单
 * @Version 3.0
 * @Date 2016-06-07
 */
@MyBatisRepository
public interface MobileMyOrderDao {

    /**
     * MS3.0
     * 查询我的订单总条数（全部订单、待执行、执行中、失败单）
     * @param myOrderListReq
     * @return
     */
    int getMyOrderListCount(MobileMyOrderListReq myOrderListReq);

    /**
     * MS3.0
     * 查询我的订单列表（全部订单、待执行、执行中、失败单）
     * @param myOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryMyOrderList(MobileMyOrderListReq myOrderListReq);

    /**
     * MS3.0
     * 查询咪站商户订单总条数（全部订单、待执行、执行中、失败单）
     * @param myOrderListReq
     * @return
     */
    int getMiOrderListCount(MobileMyOrderListReq myOrderListReq);

    /**
     * MS3.0
     * 查询咪站商户订单列表（全部订单、待执行、执行中、失败单）
     * @param myOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryMiOrderList(MobileMyOrderListReq myOrderListReq);

    /**
     * 查询订单详细信息（用户订单、商户订单）
     * @param orderId
     * @return
     */
    MobileStationOrderDetailBean queryOrderDetail(Integer orderId);

    /**
     * 根据订单号和订单状态查询 （用户订单、商户订单)
     */
    MobileStationOrderDetailBean queryOrderByBusiNoAndBusiCtrl(@Param("busiBookNo") String busiBookNo, @Param("busiCtrl") Integer busiCtrl);
   /**
     * 查询子订单详细信息（查询派车单下的子订单的详细信息）
     * @param busiBookNo
     * @return
     */
    MobileStationOrderDetailBean queryOrderDetailByScheducarno(String busiBookNo);
    
    /**
     * 根据派车单号放弃子订单
    * <p>Title: updateSubOrderStatusByScheducarno</p>
    * <p>Description: </p>
    * @param mobileOrderOperateBean
    * @return
     */
    int updateSubOrderStatusByScheducarno(MobileOrderOperateBean mobileOrderOperateBean);
    
   /**
    * 根据子订单id放弃子订单
   * <p>Title: updateSubOrderStatusBySubOrderId</p>
   * <p>Description: </p>
   * @param mobileOrderOperateBean
   * @return
    */
    int updateSubOrderStatusBySubOrderId(MobileOrderOperateBean mobileOrderOperateBean);
    /**
     * 根据发车单号查询派车单
    * <p>Title: selectMobileOrderByScheducarno</p>
    * <p>Description: </p>
    * @param scheducarno
    * @return
     */
    List<MobileBookingForm> selectMobileOrderByScheducarno(String scheducarno);

    /**
     * 根据发车单号查询派车单
    * <p>Title: selectMobileOrderByScheducarno</p>
    * <p>Description: </p>
    * @param scheducarno
    * @return
     */
    List<MobileScheduSubOrder> selectMobileSubOrderByScheducarno(String scheducarno);
    /**
     * 根据主派车单ID查询子派车单
    * <p>Title: selectMobileSubOrderByMobileId</p>
    * <p>Description: </p>
    * @param orderId
    * @return
     */
    List<MobileScheduSubOrder> selectMobileSubOrderByMobileId(Integer orderId);

    /**
     * 根据派车单号查询集包号和散件订单列表
     * @param scheducarno
     * @return
     */
    List<String> selectMobileSubOrderNoList(String scheducarno);
    
    int deleteGoodsByOrderId(Integer orderId);
    /**
     * 查询签派指派单
    * <p>Title: selectMobileOrderList</p>
    * <p>Description: </p>
    * @param mobileAssignBean
    * @return
     */
    List<MobileBookingForm> selectMobileOrderList(MobileAssignBean mobileAssignBean);

    /**
     * MS3.0根据订单ID获取状态正常的转单记录
     *
     * @param orderId
     * @return
     */
    MobileSingleCenter querySingleCenter(@Param("orderId") Integer orderId, @Param("busiCtrl") Integer busiCtrl, @Param("createUserId") Integer createUserId);

    /**
     * MS3.0根据转单接单人获取状态正常的转单记录
     *
     * @param orderId
     * @return
     */
    List<MobileSingleCenter> querySingleCenterByRev(@Param("busiBookNo") String orderId, @Param("busiCtrl") Integer busiCtrl, @Param("createUser") String createUser, @Param("revUser") String revUser);

    /**
     * 根据订单查询子单
     */
    List<MobileScheduSubOrder> selectMobileSubOrderByOrderId(Integer orderId);
    
    /**
     * 根据订单ID查询货物
     */
    List<MobileGoodsDtl> selectMobileGoodsInfoByOrderId(Integer orderId);

    /**
     * MS查询状态正常的订单记录
     * @param deliVeryConfirmReq
     * @return
     */
    List<MobileBookingForm> selectValidOrderList(DeliVeryConfirmReq deliVeryConfirmReq);
    /**
     * 更新状态
    * <p>Title: updateByPrimaryKey2</p>
    * <p>Description: </p>
    * @param assignForm
    * @return
     */
	int updateByPrimaryKey2(MobileBookingForm assignForm);

    /**
     * 批量更新状态
     * @param ids
     * @param busiCtrl
     * @return
     */
	int batchUpdateByPrimaryKey2(@Param("ids") String ids, @Param("busiCtrl") Integer busiCtrl);

	 /**
     * MS3.0
     * 查询同城外卖订单总条数（全部订单、待执行、执行中、失败单、待接单）
     * @param myOrderListReq
     * @return
     */
    int getTakeOutOrderListCount(MobileMyOrderListReq myOrderListReq);

    /**
     * MS3.0
     * 查询同城外卖订单列表（全部订单、待执行、执行中、失败单、待接单）
     * @param myOrderListReq
     * @return
     */
    List<TakeOutMobileOrderListRes> queryTakeOutOrderList(MobileMyOrderListReq myOrderListReq);

    List<String> queryBusiBookNoByPkgNo(@Param("pkgNo") String pkgNo);

    /**
     * 更新咪站的M-POD状态为已接单
     * @param busiBookNo
     * @return
     */
    int updateMiOrderStatus(@Param("busiBookNo") String busiBookNo);

    /**
     * 根据蛙站简称查询蛙站账号
     * @param custTtl
     * @return
     */
    String queryAcctUsernameByCustTtl(@Param("custTtl") String custTtl);

    /**
     * 根据busi号查询咪站订单 M-POD，当前已接单
     * @param busiBookNo
     * @return
     */
    MobileBookingForm queryMiOrderByBusiBookNo(@Param("busiBookNo") String busiBookNo);

    /**
     * 根据条件查询订单
     * @param mobileAssignBean
     * @return
     */
    List<MobileBookingForm> selectMobileOrderListByCondition(MobileAssignBean mobileAssignBean);
}
