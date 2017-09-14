package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComProvince;

import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComProvinceService
 * @Date: 2015/7/24
 * @Description:
 */
public interface ComProvinceService {
    Map<String, ComProvince> queryForMap();
    Map<String, ComProvince> queryNameForMap();
}
