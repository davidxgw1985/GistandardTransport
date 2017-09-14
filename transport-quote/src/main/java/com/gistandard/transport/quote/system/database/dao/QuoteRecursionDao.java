package com.gistandard.transport.quote.system.database.dao;

 import com.gistandard.transport.base.annotation.MyBatisRepository;
 import org.apache.ibatis.annotations.Param;

/**
 * Created by shenzhijun on 2016/3/4.
 */
@MyBatisRepository
public interface QuoteRecursionDao {
    int updateSonQuoteIds(@Param(value = "rootId") Integer rootId, @Param(value = "sonId") String sonId);
}
