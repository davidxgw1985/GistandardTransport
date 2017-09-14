package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComDriverInfoRecord;

import java.util.List;

@MyBatisRepository
public interface ComDriverInfoRecordDaoEx {

    /**
     * 根据车辆ID查询
     * @param vehicleId
     * @return
     */
    List<ComDriverInfoRecord> queryByVehicleId(Integer vehicleId);

    /**
     * 根据车辆ID删除
     * @param vehicleId
     * @return
     */
    Integer deleteByVehicleId(Integer vehicleId);
}