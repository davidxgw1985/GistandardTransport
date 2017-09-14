package com.gistandard.transport.base.entity.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileSignIn;

@MyBatisRepository
public interface MobileSignInDao {

    int deleteByPrimaryKey(Integer id);

    int insert(MobileSignIn record);

    int insertSelective(MobileSignIn record);

    MobileSignIn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileSignIn record);

    int updateByPrimaryKey(MobileSignIn record);
}