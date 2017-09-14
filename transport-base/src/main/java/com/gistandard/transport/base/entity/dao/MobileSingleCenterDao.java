package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileSingleCenter;
@MyBatisRepository
public interface MobileSingleCenterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileSingleCenter record);

    int insertSelective(MobileSingleCenter record);

    MobileSingleCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileSingleCenter record);

    int updateByPrimaryKey(MobileSingleCenter record);
}