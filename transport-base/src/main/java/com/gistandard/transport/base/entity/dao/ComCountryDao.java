package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCountry;
@MyBatisRepository
public interface ComCountryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComCountry record);

    int insertSelective(ComCountry record);

    ComCountry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComCountry record);

    int updateByPrimaryKey(ComCountry record);
}