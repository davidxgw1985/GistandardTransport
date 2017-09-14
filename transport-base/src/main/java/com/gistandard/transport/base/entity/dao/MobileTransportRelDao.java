package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileTransportRel;
@MyBatisRepository
public interface MobileTransportRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileTransportRel record);

    int insertSelective(MobileTransportRel record);

    MobileTransportRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileTransportRel record);

    int updateByPrimaryKeyWithBLOBs(MobileTransportRel record);

    int updateByPrimaryKey(MobileTransportRel record);
}