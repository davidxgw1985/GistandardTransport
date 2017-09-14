package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleCarriageRecord;

@MyBatisRepository
public interface ComVehicleCarriageRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleCarriageRecord record);

    int insertSelective(ComVehicleCarriageRecord record);

    ComVehicleCarriageRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleCarriageRecord record);

    int updateByPrimaryKey(ComVehicleCarriageRecord record);
}