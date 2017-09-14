package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCurrency;
@MyBatisRepository
public interface ComCurrencyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComCurrency record);

    int insertSelective(ComCurrency record);

    ComCurrency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComCurrency record);

    int updateByPrimaryKey(ComCurrency record);
}