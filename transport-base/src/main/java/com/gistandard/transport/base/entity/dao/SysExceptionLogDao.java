package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.SysExceptionLog;

@MyBatisRepository
public interface SysExceptionLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysExceptionLog record);

    int insertSelective(SysExceptionLog record);

    SysExceptionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysExceptionLog record);

    int updateByPrimaryKey(SysExceptionLog record);
}