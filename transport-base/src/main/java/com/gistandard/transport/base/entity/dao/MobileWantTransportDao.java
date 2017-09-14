package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileWantTransport;
@MyBatisRepository
public interface MobileWantTransportDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileWantTransport record);

    int insertSelective(MobileWantTransport record);

    MobileWantTransport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileWantTransport record);

    int updateByPrimaryKey(MobileWantTransport record);
}