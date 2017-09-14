package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileStock;
@MyBatisRepository
public interface MobileStockDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileStock record);

    int insertSelective(MobileStock record);

    MobileStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileStock record);

    int updateByPrimaryKey(MobileStock record);
}