package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileStockDetail;
@MyBatisRepository
public interface MobileStockDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileStockDetail record);

    int insertSelective(MobileStockDetail record);

    MobileStockDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileStockDetail record);

    int updateByPrimaryKey(MobileStockDetail record);
}