package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComAllType;
import com.gistandard.transport.base.entity.dao.ex.ComAllTypeDaoEx;
import com.gistandard.transport.base.entity.service.ComAllTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComAllTypeServiceImpl
 * @Date: 2015/7/24
 * @Description:
 */
@Service
public class ComAllTypeServiceImpl implements ComAllTypeService {
    @Autowired
    private ComAllTypeDaoEx comAllTypeDaoEx;

    @Override
    public Map<String, ComAllType> queryForMap(String type) {
        List<ComAllType> list = comAllTypeDaoEx.queryAll(type);
        Map<String, ComAllType> comAllTypeMap = new HashMap<String, ComAllType>();
        for (ComAllType comAllType : list) {
            comAllTypeMap.put(comAllType.gettCode(), comAllType);
        }
        return comAllTypeMap;
    }

    @Override
    public Map<String, ComAllType> queryForNameMap(String type) {
        List<ComAllType> list = comAllTypeDaoEx.queryAll(type);
        Map<String, ComAllType> comAllTypeMap = new HashMap<String, ComAllType>();
        for (ComAllType comAllType : list) {
            comAllTypeMap.put(comAllType.gettName(), comAllType);
        }
        return comAllTypeMap;
    }

    @Override
    public List<ComAllType> queryComAllTypeList(String type) {
        return comAllTypeDaoEx.queryAll(type);
    }

    @Override
    public List<ComAllType> queryAllTFlag0List(String type) {
        return comAllTypeDaoEx.queryAllTFlag0(type);
    }
}
