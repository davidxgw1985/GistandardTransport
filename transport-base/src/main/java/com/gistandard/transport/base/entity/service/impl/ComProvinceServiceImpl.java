package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComProvince;
import com.gistandard.transport.base.entity.dao.ex.ComProvinceDaoEx;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComProvinceServiceImpl
 * @Date: 2015/7/24
 * @Description:
 */
@Service
public class ComProvinceServiceImpl implements ComProvinceService {
    @Autowired
    private ComProvinceDaoEx comProvinceDaoEx;

    @Override
    @Cacheable(value = "transport_app_queryComProvinceForMap")
    public Map<String, ComProvince> queryForMap() {
        List<ComProvince> list = comProvinceDaoEx.queryAll();
        Map<String,ComProvince> comProvinceMap = new HashMap<String, ComProvince>(list.size());
        for(ComProvince comProvince: list){
            comProvinceMap.put(comProvince.getId().toString(),comProvince);
        }
        return comProvinceMap;
    }

    @Override
    @Cacheable(value = "transport_app_queryComProvinceNameForMap")
    public Map<String, ComProvince> queryNameForMap() {
        List<ComProvince> list = comProvinceDaoEx.queryAll();
        Map<String,ComProvince> comProvinceMap = new HashMap<String, ComProvince>(list.size());
        for(ComProvince comProvince: list){
            comProvinceMap.put(comProvince.getProvinceName(),comProvince);
        }
        return comProvinceMap;
    }
}
