package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomerRecord;

@MyBatisRepository
public interface ComCustomerRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComCustomerRecord record);

    int insertSelective(ComCustomerRecord record);

    ComCustomerRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComCustomerRecord record);

    int updateByPrimaryKey(ComCustomerRecord record);
}