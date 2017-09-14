package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
@MyBatisRepository
public interface MobileMoudleRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileMoudleRel record);

    int insertSelective(MobileMoudleRel record);

    MobileMoudleRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileMoudleRel record);

    int updateByPrimaryKey(MobileMoudleRel record);
}