package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomer;
@MyBatisRepository
public interface ComCustomerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComCustomer record);

    int insertSelective(ComCustomer record);

    ComCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComCustomer record);

    int updateByPrimaryKey(ComCustomer record);

    ComCustomer queryCustomerInfoByAcctId(Integer acctId);
}