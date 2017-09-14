package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;

@MyBatisRepository
public interface ComQuoteItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuoteItem record);

    int insertSelective(ComQuoteItem record);

    ComQuoteItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuoteItem record);

    int updateByPrimaryKey(ComQuoteItem record);
}