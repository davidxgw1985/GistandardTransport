package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.dao.ex.ComCityDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCityServiceImpl
 * @Date: 2015/7/24
 * @Description:
 */
@Service
public class ComCityServiceImpl implements ComCityService {

    @Autowired
    private ComCityDaoEx comCityDaoEx;

    @Override
    @Cacheable("transport_app_queryComCityForMap")
    public Map<String, ComCity> queryForMap() {
        List<ComCity> list = comCityDaoEx.queryAll();
        Map<String,ComCity> comCityMap = new HashMap<String, ComCity>();
        for(ComCity comCity: list){
            comCityMap.put(comCity.getId().toString(),comCity);
        }
        return comCityMap;
    }

    @Override
    @Cacheable("transport_app_queryComCityNameForMap")
    public Map<String, ComCity> queryNameForMap() {
        List<ComCity> list = comCityDaoEx.queryAll();
        Map<String,ComCity> comCityMap = new HashMap<String, ComCity>();
        for(ComCity comCity: list){
            comCityMap.put(comCity.getName(),comCity);
        }
        return comCityMap;
    }
}
