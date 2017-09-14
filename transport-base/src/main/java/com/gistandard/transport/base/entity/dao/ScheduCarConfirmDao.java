package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ScheduCarConfirm;
import com.gistandard.transport.base.entity.bean.ScheduCarConfirmKey;

@MyBatisRepository
public interface ScheduCarConfirmDao {
    int deleteByPrimaryKey(ScheduCarConfirmKey key);

    int insert(ScheduCarConfirm record);

    int insertSelective(ScheduCarConfirm record);

    ScheduCarConfirm selectByPrimaryKey(ScheduCarConfirmKey key);

    int updateByPrimaryKeySelective(ScheduCarConfirm record);

    int updateByPrimaryKey(ScheduCarConfirm record);
}