package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuotePrice;

@MyBatisRepository
public interface ComQuotePriceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuotePrice record);

    int insertSelective(ComQuotePrice record);

    ComQuotePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuotePrice record);

    int updateByPrimaryKey(ComQuotePrice record);
}