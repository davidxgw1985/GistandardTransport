package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComDriverInfo;

@MyBatisRepository
public interface ComDriverInfoDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ComDriverInfo record);

    int insertSelective(ComDriverInfo record);

    ComDriverInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComDriverInfo record);

    int updateByPrimaryKey(ComDriverInfo record);
}