package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.SmsVerificationCode;
@MyBatisRepository
public interface SmsVerificationCodeDao {
    int deleteByPrimaryKey(String tokenId);

    int insert(SmsVerificationCode record);

    int insertSelective(SmsVerificationCode record);

    SmsVerificationCode selectByPrimaryKey(String tokenId);

    int updateByPrimaryKeySelective(SmsVerificationCode record);

    int updateByPrimaryKey(SmsVerificationCode record);
}