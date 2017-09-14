package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.AppVersionInfo;

@MyBatisRepository
public interface AppVersionInfoDao {
    int deleteByPrimaryKey(Integer appVersionId);

    int insert(AppVersionInfo record);

    int insertSelective(AppVersionInfo record);

    AppVersionInfo selectByPrimaryKey(Integer appVersionId);

    int updateByPrimaryKeySelective(AppVersionInfo record);

    int updateByPrimaryKey(AppVersionInfo record);
}