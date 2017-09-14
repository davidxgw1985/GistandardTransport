package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileOrderOperate;

@MyBatisRepository
public interface MobileOrderOperateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileOrderOperate record);

    int insertSelective(MobileOrderOperate record);

    MobileOrderOperate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileOrderOperate record);

    int updateByPrimaryKey(MobileOrderOperate record);
}