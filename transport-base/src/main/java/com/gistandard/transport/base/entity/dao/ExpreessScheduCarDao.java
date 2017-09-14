package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ExpreessScheduCar;

@MyBatisRepository
public interface ExpreessScheduCarDao {
    int deleteByPrimaryKey(String scheduCarId);

    int insert(ExpreessScheduCar record);

    int insertSelective(ExpreessScheduCar record);

    ExpreessScheduCar selectByPrimaryKey(String scheduCarId);

    int updateByPrimaryKeySelective(ExpreessScheduCar record);

    int updateByPrimaryKey(ExpreessScheduCar record);
}