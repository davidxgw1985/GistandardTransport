package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomerRelation;

@MyBatisRepository
public interface ComCustomerRelationDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ComCustomerRelation record);

    int insertSelective(ComCustomerRelation record);

    ComCustomerRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComCustomerRelation record);

    int updateByPrimaryKey(ComCustomerRelation record);
}