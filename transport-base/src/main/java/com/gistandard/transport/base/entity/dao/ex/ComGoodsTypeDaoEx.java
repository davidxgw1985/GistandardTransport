package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComGoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@MyBatisRepository
public interface ComGoodsTypeDaoEx {
    List<ComGoodsType> selectByDicType(@Param("dicType") Integer dicType);

    List<ComGoodsType> queryAll();

    ComGoodsType selectByTypeCode(@Param(value = "typeCode") String  typeCode);
}
