package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
@MyBatisRepository
public interface MobileGoodsDtlDaoEx {

    /**
     * 批量插入
     * @param recordList
     * @return
     */
    int batchInsert(List<MobileGoodsDtl> recordList);


    /**
     * 根据mobileBookingFormId查询货物列表
     * @param mobileBookingFormId
     * @return
     */
    List<MobileGoodsDtl> selectByMobileBookingFormId(@Param("mobileBookingFormId") Integer mobileBookingFormId);

    /**
     * 根据订单号、子订单号查询货物列表
     * @param mobileBookingFormId
     * @param subOrderId
     * @return
     */
    List<MobileGoodsDtl> selectBySubOrderId(@Param("mobileBookingFormId") Integer mobileBookingFormId, @Param("subOrderId") Integer subOrderId);

    /**
     * 批量删除订单获取信息
     * @param list list
     * @return int
     */
    int batchDeleteMobileGoodsDtl(List<MobileGoodsDtl> list);
}
