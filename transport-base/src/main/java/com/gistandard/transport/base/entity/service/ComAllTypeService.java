package com.gistandard.transport.base.entity.service;


import com.gistandard.transport.base.entity.bean.ComAllType;

import java.util.List;
import java.util.Map;

/**
 * @author: xgw
 * @ClassName: ComAllTypeService
 * @Date: 2015/7/24
 * @Description:
 */
public interface ComAllTypeService {
    Map<String,ComAllType> queryForMap(String type);

    List<ComAllType> queryComAllTypeList(String type);

    Map<String,ComAllType> queryForNameMap(String type);

    List<ComAllType> queryAllTFlag0List(String type);
}
