package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.AttentionInfo;
@MyBatisRepository
public interface AttentionInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AttentionInfo record);

    int insertSelective(AttentionInfo record);

    AttentionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttentionInfo record);

    int updateByPrimaryKey(AttentionInfo record);
}