package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BookingForm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/9/29.
 */
@MyBatisRepository
public interface BookingFormDaoEx {


    /**
     * 根据busiNo获取订单信息
     *
     * @param busiNo
     * @return
     */
    BookingForm getBookingFormByBusiNo(@Param(value = "busiNo") String busiNo);

    /**
     * 根据expressOrderNo(四通一达)获取订单信息
     *
     * @param expressOrderNo
     * @return
     */
    BookingForm getBookingFormByExpressOrderNo(@Param(value = "expressOrderNo") String expressOrderNo);

    /**
     * 根据busiNo修改订单状态
     *
     * @param busiNo
     * @return
     */
    int updateBookingFormStatus(@Param(value = "busiNo") String busiNo, @Param(value = "busiCtrl") Integer busiCtrl);

    /**
     * 根据scheducarno获取订单信息
     *
     * @param scheducarno
     * @return
     */
    List<BookingForm> getBookingFormListByScheducarno(@Param(value = "scheducarno") String scheducarno);

//    List<BookingForm> selectByCondition(CenterGridBean centerGridBean);
//
//    Integer selectCount(CenterGridBean centerGridBean);

    int makeSenderOrder(Integer id);

    /**
     * 更新供采收发信息
     *
     * @param record
     * @return
     */
    int updateBookingFormCustom(BookingForm record);

    /**
     * 更新接单信息
     *
     * @param record
     * @return
     */
    int updateBookingFormReceiveBroadcastOrder(BookingForm record);

    /**
     * 更新供采收发信息
     *
     * @param record
     * @return
     */
    int updateBookingForm(BookingForm record);

    int updateCtrl(BookingForm record);

    int deleteByBusiNo(@Param(value = "busiNo") String busiNo, @Param(value = "busiCtrl") Integer busiCtrl);

    int updateBookingFormPayType(@Param(value = "busiBookNo") String busiBookNo, @Param(value = "payType") Integer payType,
                                 @Param(value = "payUser") String payUser, @Param(value = "payUserRealName") String payUserRealName,
                                 @Param(value = "payUserTelephone") String payUserTelephone);


    /**
     * yunwei
     * 车队分发订单,补全订单接单人信息为传递的司机
     *
     * @param record 订单记录
     * @return 补全订单接单人--更新条目
     */
    int updateBookingFormRevUser(BookingForm record);


    /**
     * yunwei
     * 车队竞价完成,修改订单状态
     *
     * @param record 订单记录
     * @return 补全订单接单人--更新条目
     */
    int updateBfCtrlPridict(BookingForm record);


    /**
     * yunwei
     *
     * @param record record
     * @return 用户确认竞价后更新订单表状态
     */
    int updateBookingFormAfterQuoted(BookingForm record);

    /**
     * 根据busiNo修改订单状态
     *
     * @param busiNo
     * @param oldStatus 旧状态
     * @param busiCtrl  新状态
     * @return
     */
    int updateBookingFormOldStatus(@Param(value = "busiNo") String busiNo, @Param(value = "oldStatus") Integer oldStatus,
                                   @Param(value = "busiCtrl") Integer busiCtrl, @Param(value = "fleetId") Integer fleetId);

    /**
     * 根据busiNo修改订单状态
     *
     * @param busiNo
     * @param oldStatus 旧状态
     * @param busiCtrl  新状态
     * @return
     */
    int updateBookingFormOldStatusDriver(@Param(value = "busiNo") String busiNo,
                                         @Param(value = "oldStatus") Integer oldStatus,
                                         @Param(value = "busiCtrl") Integer busiCtrl,
                                         @Param(value = "revUserId") Integer revUserId,
                                         @Param(value = "revUser") String revUser,
                                         @Param(value = "revDate") Date revDate,
                                         @Param(value = "fleetId") Integer fleetId);
}
