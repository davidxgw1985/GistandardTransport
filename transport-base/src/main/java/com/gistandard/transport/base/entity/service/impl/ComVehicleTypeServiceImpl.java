package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComVehicleType;
import com.gistandard.transport.base.entity.dao.ComVehicleTypeDao;
import com.gistandard.transport.base.entity.service.ComVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆类型业务实现
 * @author 员伟
 */
@Service
public class ComVehicleTypeServiceImpl implements ComVehicleTypeService {

    @Autowired
    private ComVehicleTypeDao comVehicleTypeDao;

    @Override
    @Cacheable(value = "transport_app_queryVehicleTypeForMap")
    public Map<Integer, ComVehicleType> queryForMap() {
        List<ComVehicleType> list = comVehicleTypeDao.queryAll();
        Map<Integer,ComVehicleType> vehicleTypeMap = new HashMap<>();
        for(ComVehicleType cvt: list){
            vehicleTypeMap.put(cvt.getId(),cvt);
        }
        return vehicleTypeMap;
    }
}
