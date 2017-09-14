package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleInfo;

@MyBatisRepository
public interface ComVehicleInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleInfo record);

    int insertSelective(ComVehicleInfo record);

    ComVehicleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleInfo record);

    int updateByPrimaryKey(ComVehicleInfo record);
}