package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileAssistInfo;

@MyBatisRepository
public interface MobileAssistInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileAssistInfo record);

    int insertSelective(MobileAssistInfo record);

    MobileAssistInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileAssistInfo record);

    int updateByPrimaryKey(MobileAssistInfo record);
}