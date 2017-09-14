package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountRole;
@MyBatisRepository
public interface ComAccountRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComAccountRole record);

    int insertSelective(ComAccountRole record);

    ComAccountRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAccountRole record);

    int updateByPrimaryKey(ComAccountRole record);
}