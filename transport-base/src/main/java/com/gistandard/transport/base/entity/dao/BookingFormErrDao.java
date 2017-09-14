package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BookingFormErr;

@MyBatisRepository
public interface BookingFormErrDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookingFormErr record);

    int insertSelective(BookingFormErr record);

    BookingFormErr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingFormErr record);

    int updateByPrimaryKey(BookingFormErr record);
}