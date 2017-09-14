package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteClientRel;

@MyBatisRepository
public interface ComQuoteClientRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuoteClientRel record);

    int insertSelective(ComQuoteClientRel record);

    ComQuoteClientRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuoteClientRel record);

    int updateByPrimaryKey(ComQuoteClientRel record);
}