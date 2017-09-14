package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComProvince;
import com.gistandard.transport.base.entity.bean.ex.ComProvinceEx;

import java.util.List;

@MyBatisRepository
public interface ComProvinceDaoEx {
    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<ComProvince> list);


    List<ComProvince> queryAll();

    /**
     * 根据条件查询省份数据
     */
	List<ComProvinceEx> queryProvince();
}