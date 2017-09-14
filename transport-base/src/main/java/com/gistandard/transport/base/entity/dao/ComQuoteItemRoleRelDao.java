package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteItemRoleRel;

@MyBatisRepository
public interface ComQuoteItemRoleRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComQuoteItemRoleRel record);

    int insertSelective(ComQuoteItemRoleRel record);

    ComQuoteItemRoleRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComQuoteItemRoleRel record);

    int updateByPrimaryKey(ComQuoteItemRoleRel record);
}