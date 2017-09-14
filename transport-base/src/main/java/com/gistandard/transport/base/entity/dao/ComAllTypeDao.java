package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAllType;
@MyBatisRepository
public interface ComAllTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComAllType record);

    int insertSelective(ComAllType record);

    ComAllType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAllType record);

    int updateByPrimaryKey(ComAllType record);
}