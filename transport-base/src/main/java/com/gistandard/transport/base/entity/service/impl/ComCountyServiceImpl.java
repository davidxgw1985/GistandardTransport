package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComCounty;
import com.gistandard.transport.base.entity.dao.ex.ComCountyDaoEx;
import com.gistandard.transport.base.entity.service.ComCountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComCountyServiceImpl
 * @Date: 2015/7/24
 * @Description:
 */
@Service
public class ComCountyServiceImpl implements ComCountyService {

    @Autowired
    private ComCountyDaoEx comCountyDaoEx;

    private volatile Map<String,ComCounty> comCountyMap;

    @Override
    public Map<String, ComCounty> queryForMap() {
        if (comCountyMap == null) {
            synchronized (this) {
                if (comCountyMap == null) {
                    List<ComCounty> list = comCountyDaoEx.queryAll();
                    comCountyMap = new HashMap<>(list.size());
                    for (ComCounty comCounty : list) {
                        comCountyMap.put(comCounty.getId().toString(), comCounty);
                    }
                }
            }
        }
        return comCountyMap;
    }

    @Override
    public ComCounty queryById(String id) {
        return queryForMap().get(id);
    }

}
