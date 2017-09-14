package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleTrayRecord;

@MyBatisRepository
public interface ComVehicleTrayRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleTrayRecord record);

    int insertSelective(ComVehicleTrayRecord record);

    ComVehicleTrayRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleTrayRecord record);

    int updateByPrimaryKey(ComVehicleTrayRecord record);
}