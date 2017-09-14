package com.gistandard.transport.base.entity.dao.ex;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.OrderNumberPartition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yujie on 2016/9/9.
 */

@MyBatisRepository
public interface OrderNumberPartitionDaoEx {

    int batchInsert(List<OrderNumberPartition> list);

    OrderNumberPartition queryByCityIdAndPartitionNo(@Param("cityId") Integer cityId, @Param("partitionNo") Integer partitionNo);

    int updateSeqByCityIdAndPartitionNo(@Param("cityId") Integer cityId, @Param("partitionNo") Integer partitionNo,
                                        @Param("seq") String seq, @Param("nextSeq") Integer nextSeq);

    int updateIdByCityIdAndPartitionNo(@Param("cityId") int cityId, @Param("partitionNo") int partitionNo, @Param("id") int id);
}
