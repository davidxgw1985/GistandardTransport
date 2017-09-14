package com.gistandard.transport.base.entity.service.impl;

import com.gistandard.transport.base.entity.bean.ComGoodsType;
import com.gistandard.transport.base.entity.dao.ex.ComGoodsTypeDaoEx;
import com.gistandard.transport.base.entity.service.ComGoodsTypeService;
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
public class ComGoodsTypeServiceImpl implements ComGoodsTypeService {
    @Autowired
    private ComGoodsTypeDaoEx comGoodsTypeDaoEx;

    @Override
    @Cacheable(value = "transport_app_queryComGoodsTypeForMap")
    public Map<String, ComGoodsType> queryForMap() {
        List<ComGoodsType> list = comGoodsTypeDaoEx.queryAll();
        Map<String,ComGoodsType> comGoodsTypeMap = new HashMap<String, ComGoodsType>();
        for(ComGoodsType comGoodsType: list){
            comGoodsTypeMap.put(comGoodsType.getTypeCode(),comGoodsType);
        }
        return comGoodsTypeMap;
    }
}
