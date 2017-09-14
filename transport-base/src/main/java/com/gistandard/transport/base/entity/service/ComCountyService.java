package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComCounty;

import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCountyService
 * @Date: 2015/7/24
 * @Description:
 */
public interface ComCountyService {
    Map<String, ComCounty> queryForMap();

    ComCounty queryById(String id);
}
