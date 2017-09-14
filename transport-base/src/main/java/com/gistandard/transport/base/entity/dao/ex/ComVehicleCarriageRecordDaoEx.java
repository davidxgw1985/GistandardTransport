package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleCarriageRecord;

import java.util.List;

@MyBatisRepository
public interface ComVehicleCarriageRecordDaoEx {

    /**
     * 根据车辆ID查询
     * @param vehicleId
     * @return
     */
    List<ComVehicleCarriageRecord> queryByVehicleId(Integer vehicleId);

    /**
     * 根据车辆ID删除
     * @param vehicleId
     * @return
     */
    Integer deleteByVehicleId(Integer vehicleId);
}