package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileCallInfo;

@MyBatisRepository
public interface MobileCallInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileCallInfo record);

    int insertSelective(MobileCallInfo record);

    MobileCallInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileCallInfo record);

    int updateByPrimaryKey(MobileCallInfo record);
}