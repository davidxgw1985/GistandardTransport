package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
@MyBatisRepository
public interface ComWaybillTraceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComWaybillTrace record);

    int insertSelective(ComWaybillTrace record);

    ComWaybillTrace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComWaybillTrace record);

    int updateByPrimaryKey(ComWaybillTrace record);
}