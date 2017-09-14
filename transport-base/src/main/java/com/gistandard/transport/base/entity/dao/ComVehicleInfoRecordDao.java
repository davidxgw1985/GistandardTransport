package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleInfoRecord;

@MyBatisRepository
public interface ComVehicleInfoRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComVehicleInfoRecord record);

    int insertSelective(ComVehicleInfoRecord record);

    ComVehicleInfoRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComVehicleInfoRecord record);

    int updateByPrimaryKey(ComVehicleInfoRecord record);
}