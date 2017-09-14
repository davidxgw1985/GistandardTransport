package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileAddressInfo;
@MyBatisRepository
public interface MobileAddressInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileAddressInfo record);

    int insertSelective(MobileAddressInfo record);

    MobileAddressInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileAddressInfo record);

    int updateByPrimaryKey(MobileAddressInfo record);
}