package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BatchUpMBF;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/9/30.
 */
@MyBatisRepository
public interface MobileBookingFormDaoEx {
    int insert2(MobileBookingForm record);

    int batchInsert(List<MobileBookingForm> list);

    int updateByBusiCtrlLock(Map<String, Object> param);

    /**
     * 修改保险类状态
     * @return
     */
    int updatePolicyByBookBusiNo(@Param("busibookno") String busibookno, @Param("needInsure") Boolean needInsure,
                                 @Param("premiumValue") BigDecimal premiumValue, @Param("goodsValue") BigDecimal goodsValue, @Param("payType") Integer payType);

    /**
     * 根据发车单号查询派车单
     * <p>Title: selectMobileOrderByScheducarno</p>
     * <p>Description: </p>
     * @param scheducarno
     * @return
     */
    List<MobileBookingForm> selectMobileOrderByScheducarno(String scheducarno);

    MobileBookingForm selectByConditions(@Param("busiBookNo") String busiBookNo,
                                         @Param("revUser") String revUser,
                                         @Param("revCompanyId") Integer revCompanyId,
                                         @Param("busiCtrl") Integer busiCtrl,
                                         @Param("roleId") Integer roleId);

    MobileBookingForm selectBySubOrder(@Param("busiBookNo") String busiBookNo,
                                       @Param("revUser") String revUser,
                                       @Param("revCompanyId") Integer revCompanyId,
                                       @Param("busiCtrl") Integer busiCtrl,
                                       @Param("roleId") Integer roleId);

    MobileBookingForm selectByConditions2(@Param("busiBookNo") String busiBookNo,
                                          @Param("revUser") String revUser,
                                          @Param("busiCtrl") Integer busiCtrl);

    /**
     * 根据派车单号，查询咪站订单
     * @param scheducarno
     * @return
     */
    MobileBookingForm queryMiScheduOrder(@Param("scheducarno") String scheducarno);

    int deleteByBusiNo(@Param(value = "busiNo") String busiNo, @Param(value = "busiCtrl") Integer busiCtrl);

    List<MobileBookingForm> queryByConditions(@Param("busiBookNo") String busiBookNo, @Param("revUser") String revUser, @Param("busiCtrls") String busiCtrls);

    List<MobileBookingForm> queryOrderListByConditions(@Param("busiBookNo") String busiBookNo,
                                                       @Param("revUser") String revUser,
                                                       @Param("revCompanyId") Integer revCompanyId,
                                                       @Param("busiCtrl") Integer busiCtrl);

    List<MobileBookingForm> queryOrderListByConditions2(@Param("busiBookNo") String busiBookNo,
                                                        @Param("revUser") String revUser,
                                                        @Param("revCompanyId") Integer revCompanyId,
                                                        @Param("busiCtrl") Integer busiCtrl,
                                                        @Param("roleId") Integer roleId);

    /**
     * 查询咪站的POP-M、M-POD两条记录的单
     * @param busiBookNo
     * @param revUser
     * @param revCompanyId
     * @return
     */
    List<MobileBookingForm> queryMiOrderListByConditions(@Param("busiBookNo") String busiBookNo,
                                                         @Param("revUser") String revUser,
                                                         @Param("revCompanyId") Integer revCompanyId,
                                                         @Param("roleId") Integer roleId);

    /**
     * 根据单号查询
     * <p>Title: selectMobileOrderByBookBusiNo</p>
     * <p>Description: </p>
     * @param busiBookNo
     * @return
     */
    List<MobileBookingForm> selectMobileOrderByBookBusiNo(@Param("busiBookNo") String busiBookNo);

    /**
     * 根据子订单号查询派车单信息
     * <p>Title: selectMobileOrderByBookBusiNo</p>
     * <p>Description: </p>
     * @param busiBookNo
     * @return
     */
    MobileBookingForm queryMobileOrderBySonBookBusiNo(@Param("busiBookNo") String busiBookNo,
                                                      @Param("roleId") Integer roleId,
                                                      @Param("busiCtrl") Integer busiCtrl);


    /**
     * 批量更新订单状态
     * @param mobileBookingFormList
     * @return
     */
    int batchUpdateOrderBusi(List<MobileBookingForm> mobileBookingFormList);

    /**
     * 根据条件查询订单
     * @param mobileBookingForm
     * @return
     */
    MobileBookingForm getMobileBookingFormByConditions(MobileBookingForm mobileBookingForm);

    /**
     * 根据条件查询订单
     * @param mobileBookingForm
     * @return
     */
    MobileBookingForm getMobileBookingFormFailure(MobileBookingForm mobileBookingForm);

    /**
     * 根据订单ID，和原始订单状态，修改订单状态
     * @param orderId
     * @param currBusiCtrl
     * @param destBusiCtrl
     * @return
     */
    int updateOrderBusi(@Param("orderId") Integer orderId, @Param("currBusiCtrl") Integer currBusiCtrl, @Param("destBusiCtrl") Integer destBusiCtrl);

    int updateOrderByParam(MobileBookingForm mobileBookingForm);

    int getMerchantOrderCount(@Param("acctUserName") String acctUserName, @Param("roleId") Integer roleId);

    /**
     * 根据单号查询 快递员、司机 待送达的订单
     * <p>Title: getMobileOrderByBookBusiNo</p>
     * <p>Description: </p>
     * @param busiBookNo
     * @return
     */
    MobileBookingForm getMobileOrderByBookBusiNo(@Param("busiBookNo") String busiBookNo, @Param("roleId") Integer roleId, @Param("destnLocus") String destnLocus);


    /**
     * yunwei
     * 车队分发订单业务中
     * 根据订单编号和产品类型获取订单信息
     * @param busiBookNo 订单编号
     * @param productType 产品类型
     * @return 订单信息
     */
    MobileBookingForm getMobOrderByBusiNoPdType(@Param("busiBookNo") String busiBookNo, @Param("productType") String productType, @Param("roleId") Integer roleId);


    /**
     * yunwei
     * 车队分发业务中,在同城快递运输段，分发司机，
     * 更新MobileBookingForm表中接单人和接单人id为当前分配司机账号
     * @param mobileBookingForm mobileBookingForm
     * @return 更新MobileBookingForm条目
     */
    int updateMobBookingFormRevUser(MobileBookingForm mobileBookingForm);


    /**
     * queryMobOrderByCondition
     * @param busiBookNo busiBookNo
     * @param revCompanyId revCompanyId
     * @param busiCtrl busiCtrl
     * @return MobileBookingForm
     */
    MobileBookingForm queryMobOrderByCondition(@Param("busiBookNo") String busiBookNo,
                                               @Param("revCompanyId") Integer revCompanyId,
                                               @Param("busiCtrl") Integer busiCtrl);

    /**
     * 咪站确认车队报价
     * @param orderId orderId
     * @param currBusiCtrl currBusiCtrl
     * @param destBusiCtrl destBusiCtrl
     * @param predictValue predictValue
     * @param predictCurr predictCurr
     * @return int
     */
    int updateMiConfirmOrder(@Param("orderId") Integer orderId,
                             @Param("currBusiCtrl") Integer currBusiCtrl,
                             @Param("destBusiCtrl") Integer destBusiCtrl,
                             @Param("predictValue") BigDecimal predictValue,
                             @Param("predictCurr") String predictCurr);

    /**
     * 咪站-指派车队 车队强制接单
     * @param orderId
     * @param currBusiCtrl
     * @param destBusiCtrl
     * @param selfQuoteValue
     * @param selfQuoteCurr
     * @return
     */
    int updateMiAssignOrder(@Param("orderId") Integer orderId,
                            @Param("currBusiCtrl") Integer currBusiCtrl,
                            @Param("destBusiCtrl") Integer destBusiCtrl,
                            @Param("selfQuoteValue") BigDecimal selfQuoteValue,
                            @Param("selfQuoteCurr") String selfQuoteCurr,
                            @Param("mileage") BigDecimal mileage);

    /**
     * 批量删除订单
     * @param list list
     * @return int
     */
    int batchDeleteMobileBookingForm(List<MobileBookingForm> list);


    List<MobileBookingForm> querySignInOrderList(@Param("revUser") String revUser,
                                                 @Param("busiCtrl") Integer busiCtrl,
                                                 @Param("roleId") Integer roleId,
                                                 @Param("destnLocusId") Integer destnLocusId);


    /**
     * 批量更新订单签到时间
     * @param batchUpMBF batchUpMBF
     * @return int
     */
    int batchUpdateSignInOrder(BatchUpMBF batchUpMBF);


    /**
     * 查询洼站指派订单
     * @param busiBookNo 订单号
     * @return 订单信息
     */
    MobileBookingForm queryWaAssignOrder(@Param("busiBookNo") String busiBookNo);


}
