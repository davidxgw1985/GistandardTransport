package com.gistandard.transport.base.entity.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComGoodsType;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface ComGoodsTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ComGoodsType record);

    int insertSelective(ComGoodsType record);

    ComGoodsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComGoodsType record);

    int updateByPrimaryKey(ComGoodsType record);

    ComGoodsType selectByTypeCode(@Param(value = "typeCode") String  typeCode);
}