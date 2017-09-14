package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileStockDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by m on 2016/8/6.
 */
@MyBatisRepository
public interface MobileStockDetailDaoEx {
    List<MobileStockDetail> selectByOrderNo(@Param("scheducarno") String scheducarno, @Param("busiBookNo") String busiBookNo, @Param("boxNum") String boxNum);

    int deleteByBusiBookNoAndScheducarno(@Param("busiBookNo") String busiBookNo, @Param("scheducarno") String scheducarno);

    /**
     * 批量插入
     * @param recordList
     * @return
     */
    int batchInsert(List<MobileStockDetail> recordList);
}
