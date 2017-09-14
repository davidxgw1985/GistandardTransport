package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleTray;

@MyBatisRepository
public interface ComVehicleTrayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleTray record);

    int insertSelective(ComVehicleTray record);

    ComVehicleTray selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleTray record);

    int updateByPrimaryKey(ComVehicleTray record);
}