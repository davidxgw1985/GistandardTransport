package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BookingFormErr;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface BookingFormErrDaoEx {
    /**
     * 根据busiNo获取订单信息
     * @param busiNo
     * @return
     */
    BookingFormErr getBookingFormErrByBusiNo(@Param(value="busiNo")String busiNo);

    /**
     * 根据busiNo修改订单状态
     * @param busiNo
     * @param oldStatus 旧状态
     * @param busiCtrl 新状态
     * @return
     */
    int updateBookingFormErrStatus(@Param(value="busiNo")String busiNo, @Param(value = "oldStatus") Integer oldStatus,
                                   @Param(value = "busiCtrl") Integer busiCtrl);
}