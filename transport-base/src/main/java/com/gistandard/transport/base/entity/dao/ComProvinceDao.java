package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComProvince;

@MyBatisRepository
public interface ComProvinceDao {
    int deleteByPrimaryKey(String id);

    int insert(ComProvince record);

    int insertSelective(ComProvince record);

    ComProvince selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ComProvince record);

    int updateByPrimaryKey(ComProvince record);
}