package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileValueAdd;

import java.util.List;

@MyBatisRepository
public interface MobileValueAddDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileValueAdd record);

    int insertSelective(MobileValueAdd record);

    MobileValueAdd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileValueAdd record);

    int updateByPrimaryKey(MobileValueAdd record);

    List<MobileValueAdd> queryAll();

}