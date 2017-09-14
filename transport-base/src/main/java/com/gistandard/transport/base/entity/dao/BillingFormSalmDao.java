package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.BillingFormSalm;
@MyBatisRepository
public interface BillingFormSalmDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BillingFormSalm record);

    int insertSelective(BillingFormSalm record);

    BillingFormSalm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BillingFormSalm record);

    int updateByPrimaryKey(BillingFormSalm record);
}