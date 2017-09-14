package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileGpsInsertInfo;

@MyBatisRepository
public interface MobileGpsInsertInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileGpsInsertInfo record);

    int insertSelective(MobileGpsInsertInfo record);

    MobileGpsInsertInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileGpsInsertInfo record);

    int updateByPrimaryKey(MobileGpsInsertInfo record);
}