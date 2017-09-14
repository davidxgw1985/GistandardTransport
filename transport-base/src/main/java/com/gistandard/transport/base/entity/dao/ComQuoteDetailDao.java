package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteDetail;

@MyBatisRepository
public interface ComQuoteDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuoteDetail record);

    int insertSelective(ComQuoteDetail record);

    ComQuoteDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuoteDetail record);

    int updateByPrimaryKey(ComQuoteDetail record);
}