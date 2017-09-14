package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileValueAddRel;

import java.util.List;

@MyBatisRepository
public interface MobileValueAddRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileValueAddRel record);

    int insertSelective(MobileValueAddRel record);

    MobileValueAddRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileValueAddRel record);

    int updateByPrimaryKey(MobileValueAddRel record);

    List<MobileValueAddRel> selectByBookingFormId(Integer bookingFormId);
}