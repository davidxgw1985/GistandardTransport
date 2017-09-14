package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileFleetBidding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface MobileFleetBiddingDaoEx {

    /**
     * 根据订单查询所有车队竞价信息列表
     *
     * @param busiBookNo
     * @return
     */
    List<MobileFleetBidding> queryAllFleetBiddingInfo(@Param("busiBookNo")String busiBookNo);

    /**
     * 根据订单号查询有效的竞价信息
     *
     * @param busiBookNo
     * @param scheducarno
     * @return
     */
    MobileFleetBidding queryFleetBidding(@Param("busiBookNo")String busiBookNo, @Param("scheducarno")String scheducarno);



    MobileFleetBidding selectByOrderId(@Param("bookingFormId")Integer  bookingFormId);

    /**
     * yunwei
     * 车队分发订单给司机--更新车队竞价表(OTCZS,OTCYS)
     * @param record 竞价记录
     * @return 更新条目
     */
    int updateByBookingFormId(MobileFleetBidding record);

    /**
     * yunwei
     * 车队分发订单给司机--更新车队竞价表(ITCYS)
     * @param record 竞价记录
     * @return 更新条目
     */
    int updateByBusiBookNo(MobileFleetBidding record);
}