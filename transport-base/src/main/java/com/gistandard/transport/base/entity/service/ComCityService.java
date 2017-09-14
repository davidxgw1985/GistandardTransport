package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComCity;

import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCityService
 * @Date: 2015/7/24
 * @Description:
 */
public interface ComCityService {
    Map<String, ComCity> queryForMap();
    Map<String, ComCity> queryNameForMap();

}
