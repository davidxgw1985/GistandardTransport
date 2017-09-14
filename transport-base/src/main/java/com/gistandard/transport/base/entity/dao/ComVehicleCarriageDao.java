package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleCarriage;

@MyBatisRepository
public interface ComVehicleCarriageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleCarriage record);

    int insertSelective(ComVehicleCarriage record);

    ComVehicleCarriage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleCarriage record);

    int updateByPrimaryKey(ComVehicleCarriage record);
}