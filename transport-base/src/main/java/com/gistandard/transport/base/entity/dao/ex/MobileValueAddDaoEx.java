package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileValueAdd;
import com.gistandard.transport.base.entity.bean.MobileValueAddRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileValueAddDaoEx
 * @Date: 2017/6/20
 * @Description:
 */
@MyBatisRepository
public interface MobileValueAddDaoEx {
    /**
     * 根据bookingFormId获取O单增值服务信息
     *
     * @param bookingFormId
     * @return
     */
    List<MobileValueAdd> queryMobileValueAddList(@Param(value = "bookingFormId") Integer bookingFormId);
    /**
     * 根据mobileBookingFormId获取I单增值服务信息
     *
     * @param mobileBookingFormId
     * @return
     */
    List<MobileValueAdd> queryMobileValueAddListByMoblieOrderId(@Param(value = "mobileBookingFormId") Integer mobileBookingFormId);

    /**
     * 批量添加增值服务
     * @param recordList recordList
     * @return 批量添加增值服务结果
     */
    int batchInsert(List<MobileValueAddRel> recordList);


    /**
     * 删除增值服务
     * @param bookingFormId bookingFormId
     * @return 删除增值服务结果
     */
    int delMVAByBookingFormId(Integer bookingFormId);

    /**
     * 删除增值服务
     * @param mobileBookingFormId mobileBookingFormId
     * @return 删除增值服务结果
     */
    int delMVAByMobileBookingFormId(Integer mobileBookingFormId);

}
