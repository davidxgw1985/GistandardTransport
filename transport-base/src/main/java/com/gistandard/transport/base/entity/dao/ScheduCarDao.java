package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.entity.bean.ScheduCar;

public interface ScheduCarDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ScheduCar record);

    int insertSelective(ScheduCar record);

    ScheduCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduCar record);

    int updateByPrimaryKey(ScheduCar record);
}