package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComDriverInfoRecord;

@MyBatisRepository
public interface ComDriverInfoRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComDriverInfoRecord record);

    int insertSelective(ComDriverInfoRecord record);

    ComDriverInfoRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComDriverInfoRecord record);

    int updateByPrimaryKey(ComDriverInfoRecord record);
}