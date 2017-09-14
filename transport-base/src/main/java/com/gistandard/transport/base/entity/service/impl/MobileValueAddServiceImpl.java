package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.MobileValueAdd;
import com.gistandard.transport.base.entity.dao.MobileValueAddDao;
import com.gistandard.transport.base.entity.service.MobileValueAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增值服务业务实现
 * @author 员伟
 */
@Service
public class MobileValueAddServiceImpl implements MobileValueAddService {

    @Autowired
    private MobileValueAddDao mbileValueAddDao;

    @Override
    @Cacheable(value = "transport_app_queryMobileValueAddForMap")
    public Map<Integer, MobileValueAdd> queryForMap() {
        List<MobileValueAdd> list = mbileValueAddDao.queryAll();
        Map<Integer,MobileValueAdd> comUnitMap = new HashMap<>();
        for(MobileValueAdd mva: list){
            comUnitMap.put(mva.getId(),mva);
        }
        return comUnitMap;
    }



}
