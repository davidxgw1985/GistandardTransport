package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.SmsModel;

@MyBatisRepository
public interface SmsModelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsModel record);

    int insertSelective(SmsModel record);

    SmsModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsModel record);

    int updateByPrimaryKey(SmsModel record);
}