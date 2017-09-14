package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteStationRel;

@MyBatisRepository
public interface ComQuoteStationRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuoteStationRel record);

    int insertSelective(ComQuoteStationRel record);

    ComQuoteStationRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuoteStationRel record);

    int updateByPrimaryKey(ComQuoteStationRel record);
}