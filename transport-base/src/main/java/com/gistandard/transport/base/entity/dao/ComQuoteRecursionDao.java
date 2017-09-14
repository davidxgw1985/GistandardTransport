package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteRecursion;

@MyBatisRepository
public interface ComQuoteRecursionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuoteRecursion record);

    int insertSelective(ComQuoteRecursion record);

    ComQuoteRecursion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuoteRecursion record);

    int updateByPrimaryKey(ComQuoteRecursion record);
}