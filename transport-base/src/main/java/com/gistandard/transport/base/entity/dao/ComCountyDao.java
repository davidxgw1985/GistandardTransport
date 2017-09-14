package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCounty;
@MyBatisRepository
public interface ComCountyDao {
    int deleteByPrimaryKey(String id);

    int insert(ComCounty record);

    int insertSelective(ComCounty record);

    ComCounty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComCounty record);

    int updateByPrimaryKey(ComCounty record);
}