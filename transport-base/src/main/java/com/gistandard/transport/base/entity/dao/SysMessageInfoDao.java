package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.SysMessageInfo;

@MyBatisRepository
public interface SysMessageInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMessageInfo record);

    int insertSelective(SysMessageInfo record);

    SysMessageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMessageInfo record);

    int updateByPrimaryKey(SysMessageInfo record);
}