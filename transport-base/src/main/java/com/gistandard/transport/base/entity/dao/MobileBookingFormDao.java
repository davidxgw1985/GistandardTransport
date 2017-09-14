package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;

@MyBatisRepository
public interface MobileBookingFormDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileBookingForm record);

    int insertSelective(MobileBookingForm record);

    MobileBookingForm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileBookingForm record);

    int updateByPrimaryKey(MobileBookingForm record);

}