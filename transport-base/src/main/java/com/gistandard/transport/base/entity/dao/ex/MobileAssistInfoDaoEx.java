package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileAssistInfo;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface MobileAssistInfoDaoEx {


    /**
     * 查询快递员或咪站申请的援助信息
     * @param busiBookNo 订单号
     * @return 快递员或咪站申请的援助信息
     */
    MobileAssistInfo queryMobileAssistInfo(@Param("busiBookNo")String busiBookNo);

}