package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccessoryRelation;

import java.util.List;

@MyBatisRepository
public interface ComAccessoryRelationDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ComAccessoryRelation record);

    int insertSelective(ComAccessoryRelation record);

    ComAccessoryRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAccessoryRelation record);

    int updateByPrimaryKey(ComAccessoryRelation record);

    int batchInsert(List<ComAccessoryRelation> list);
}