package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccount;
@MyBatisRepository
public interface ComAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComAccount record);

    int insertSelective(ComAccount record);

    ComAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAccount record);

    int updateByPrimaryKey(ComAccount record);

    ComAccount queryByAccount(String acct);
}