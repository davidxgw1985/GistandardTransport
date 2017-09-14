package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComVehicleType;

import java.util.Map;

/**
 * 车辆类型业务接口
 * @author 员伟
 */
public interface ComVehicleTypeService {

    Map<Integer, ComVehicleType> queryForMap();
}
