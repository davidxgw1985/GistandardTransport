package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComAccountRequest;

@MyBatisRepository
public interface ComAccountRequestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComAccountRequest record);

    int insertSelective(ComAccountRequest record);

    ComAccountRequest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComAccountRequest record);

    int updateByPrimaryKey(ComAccountRequest record);
}