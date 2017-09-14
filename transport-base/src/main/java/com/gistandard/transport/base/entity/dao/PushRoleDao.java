package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.PushRole;
@MyBatisRepository
public interface PushRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PushRole record);

    int insertSelective(PushRole record);

    PushRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushRole record);

    int updateByPrimaryKey(PushRole record);
}