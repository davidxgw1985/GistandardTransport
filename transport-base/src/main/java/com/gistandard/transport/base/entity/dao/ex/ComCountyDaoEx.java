package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCounty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface ComCountyDaoEx {

    int batchInsert(List<ComCounty> list);

    List<ComCounty> queryAll();

    /**
     * 根据市的ID和区的名称 查询区的信息
     *
     * @param cityId
     * @param areaName
     * @return
     */
    ComCounty queryByParams(@Param(value = "cityId") String cityId, @Param(value = "areaName") String areaName);
}