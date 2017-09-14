package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComTackoutGoodsType;
@MyBatisRepository
public interface ComTackoutGoodsTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComTackoutGoodsType record);

    int insertSelective(ComTackoutGoodsType record);

    ComTackoutGoodsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComTackoutGoodsType record);

    int updateByPrimaryKey(ComTackoutGoodsType record);
}