package com.gistandard.transport.base.entity.service;

import com.gistandard.transport.base.entity.bean.ComUnit;

import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/9/30.
 */
public interface ComUnitService {
    Map<String, ComUnit> queryForMap();

    ComUnit getComUnitByCode(String code);
    /**
     * 根据下单单位类型查询
     * @title queryComUnitByUnitType
     * @describe TODO
     * @return
     * @author M.simple
     * @version 1.0
     */
    List<ComUnit> queryComUnitByUnitType(int unitType);

    /**
     * 对接太保获取包装单位
     * @return
     */
    List<ComUnit> queryComUnitforTaiBao();
}
