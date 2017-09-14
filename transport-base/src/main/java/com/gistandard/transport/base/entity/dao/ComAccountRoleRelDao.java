package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;
@MyBatisRepository
public interface ComAccountRoleRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComAccountRoleRel record);

    int insertSelective(ComAccountRoleRel record);

    ComAccountRoleRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAccountRoleRel record);

    int updateByPrimaryKey(ComAccountRoleRel record);
}