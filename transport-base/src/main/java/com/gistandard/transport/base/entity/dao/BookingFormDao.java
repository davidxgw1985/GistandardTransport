package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BookingForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface BookingFormDao {

    int deleteByPrimaryKey(Integer id);

    int insert(BookingForm record);

    int insertSelective(BookingForm record);

    BookingForm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookingForm record);

    int updateByPrimaryKey(BookingForm record);
}