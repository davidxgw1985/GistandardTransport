package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComUnit;
import com.gistandard.transport.base.entity.dao.ex.ComUnitDaoEx;
import com.gistandard.transport.base.entity.service.ComUnitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/9/30.
 */
@Service
public class ComUnitServiceImpl implements ComUnitService {
    @Autowired
    private ComUnitDaoEx comUnitDaoEx;

    @Override
    @Cacheable(value = "transport_app_queryComUnitForMap")
    public Map<String, ComUnit> queryForMap() {
        List<ComUnit> list = comUnitDaoEx.queryAll();
        Map<String,ComUnit> comUnitMap = new HashMap<String, ComUnit>();
        for(ComUnit comUnit: list){
            comUnitMap.put(comUnit.getUnitCode(),comUnit);
        }
        return comUnitMap;
    }


    @Override
    public ComUnit getComUnitByCode(String code) {
        if(StringUtils.isEmpty(code)){
            return  null;
        }
        return queryForMap().get(code);
    }
    /**
     * 根据下单单位类型查询
     * @title queryComUnitByUnitType
     * @describe TODO
     * @return
     * @author M.simple
     * @version 1.0
     */
    public List<ComUnit> queryComUnitByUnitType(int unitType){
        return comUnitDaoEx.queryComUnitByUnitType(unitType);
    }

    /**
     * 对接太保获取包装单位
     * @return
     */
    @Override
    public List<ComUnit> queryComUnitforTaiBao() {
        return comUnitDaoEx.queryComUnitforTaiBao();
    }
}
