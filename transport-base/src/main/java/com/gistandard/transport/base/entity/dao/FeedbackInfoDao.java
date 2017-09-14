package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.FeedbackInfo;

@MyBatisRepository
public interface FeedbackInfoDao {
	int deleteByPrimaryKey(Integer id);

	int insert(FeedbackInfo record);

	int insertSelective(FeedbackInfo record);

	FeedbackInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FeedbackInfo record);

	int updateByPrimaryKey(FeedbackInfo record);
}