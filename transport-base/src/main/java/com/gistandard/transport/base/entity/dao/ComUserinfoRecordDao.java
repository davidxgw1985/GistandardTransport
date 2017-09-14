package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComUserinfoRecord;

@MyBatisRepository
public interface ComUserinfoRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComUserinfoRecord record);

    int insertSelective(ComUserinfoRecord record);

    ComUserinfoRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComUserinfoRecord record);

    int updateByPrimaryKey(ComUserinfoRecord record);
}