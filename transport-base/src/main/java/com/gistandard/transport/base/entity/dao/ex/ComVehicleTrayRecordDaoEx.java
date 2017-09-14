package com.gistandard.transport.base.entity.dao.ex;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComVehicleTrayRecord;

import java.util.List;

@MyBatisRepository
public interface ComVehicleTrayRecordDaoEx {

    /**
     * 根据车辆ID查询
     * @param vehicleId
     * @return
     */
    List<ComVehicleTrayRecord> queryByVehicleId(Integer vehicleId);

    /**
     * 根据车辆ID查询
     * @param vehicleId
     * @return
     */
    Integer deleteByVehicleId(Integer vehicleId);
}