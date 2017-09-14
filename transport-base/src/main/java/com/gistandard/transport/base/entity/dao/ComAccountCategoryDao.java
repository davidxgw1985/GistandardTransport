package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountCategory;

@MyBatisRepository
public interface ComAccountCategoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComAccountCategory record);

    int insertSelective(ComAccountCategory record);

    ComAccountCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAccountCategory record);

    int updateByPrimaryKey(ComAccountCategory record);
}