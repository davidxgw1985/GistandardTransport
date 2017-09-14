package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
@MyBatisRepository
public interface MobileScheduSubOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileScheduSubOrder record);

    int insertSelective(MobileScheduSubOrder record);

    MobileScheduSubOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileScheduSubOrder record);

    int updateByPrimaryKey(MobileScheduSubOrder record);
}