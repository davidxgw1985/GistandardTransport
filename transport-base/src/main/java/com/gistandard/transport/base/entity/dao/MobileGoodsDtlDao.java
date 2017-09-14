package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface MobileGoodsDtlDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileGoodsDtl record);

    int insertSelective(MobileGoodsDtl record);

    MobileGoodsDtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileGoodsDtl record);

    int updateByPrimaryKey(MobileGoodsDtl record);

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
    List<MobileGoodsDtl> selectByMobileBookingFormId(@Param("mobileBookingFormId")Integer mobileBookingFormId);
}