package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCity;
@MyBatisRepository
public interface ComCityDao {
    int deleteByPrimaryKey(String id);

    int insert(ComCity record);

    int insertSelective(ComCity record);

    ComCity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComCity record);

    int updateByPrimaryKey(ComCity record);
}