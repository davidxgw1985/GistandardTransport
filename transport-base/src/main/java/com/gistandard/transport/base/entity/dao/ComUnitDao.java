package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUnit;
@MyBatisRepository
public interface ComUnitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComUnit record);

    int insertSelective(ComUnit record);

    ComUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComUnit record);

    int updateByPrimaryKey(ComUnit record);
}