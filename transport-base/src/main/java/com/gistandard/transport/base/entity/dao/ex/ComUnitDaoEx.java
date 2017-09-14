package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@MyBatisRepository
public interface ComUnitDaoEx {

    /**
     * 根据下单单位类型查询
     * @title queryComUnitByUnitType
     * @describe TODO
     * @return
     * @author M.simple
     * @version 1.0
     */
    List<ComUnit> queryComUnitByUnitType(@Param("unitType")Integer unitType);

    /**
     * 对接太保获取包装单位
     * @return
     */
    List<ComUnit> queryComUnitforTaiBao();

    List<ComUnit> queryAllComUnitList();

    List<ComUnit> queryAll();
}
