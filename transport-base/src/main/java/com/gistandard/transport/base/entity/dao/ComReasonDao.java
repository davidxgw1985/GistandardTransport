package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComReason;
@MyBatisRepository
public interface ComReasonDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComReason record);

    int insertSelective(ComReason record);

    ComReason selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComReason record);

    int updateByPrimaryKey(ComReason record);
}