package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleType;

import java.util.List;

@MyBatisRepository
public interface ComVehicleTypeDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleType record);

    int insertSelective(ComVehicleType record);

    ComVehicleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleType record);

    int updateByPrimaryKey(ComVehicleType record);

    List<ComVehicleType> queryAll();
}