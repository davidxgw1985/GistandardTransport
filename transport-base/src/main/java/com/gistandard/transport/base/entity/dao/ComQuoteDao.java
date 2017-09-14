package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuote;

@MyBatisRepository
public interface ComQuoteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuote record);

    int insertSelective(ComQuote record);

    ComQuote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuote record);

    int updateByPrimaryKeyWithBLOBs(ComQuote record);

    int updateByPrimaryKey(ComQuote record);
}