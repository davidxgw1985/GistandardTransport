package com.gistandard.transport.order.module.genno.bean;

import com.gistandard.transport.base.entity.service.impl.OrderNumberPartitionServiceImpl;
import com.gistandard.transport.tools.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujie on 2016/9/9.
 */
public class GenOrderNumberPartitionTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(GenOrderNumberPartitionTask.class);

    private Integer cityId;

    private Integer partitionNoStart;

    private Integer partitionNoEnd;

    private Integer citIndex;

    public GenOrderNumberPartitionTask(Integer cityId, Integer partitionNoStart, Integer partitionNoEnd, Integer cityIndex) {
        this.cityId = cityId;
        this.partitionNoStart = partitionNoStart;
        this.partitionNoEnd = partitionNoEnd;
        this.citIndex = cityIndex;
    }

    @Override
    public void run() {
        OrderNumberPartitionServiceImpl orderNumberPartitionService = (OrderNumberPartitionServiceImpl) SpringContextUtil.getBean("orderNumberPartitionServiceImpl");
        List<Integer> sequenceList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            sequenceList.add(i);
        }
        for (int index = partitionNoStart; index <= partitionNoEnd; index++) {
            /*OrderNumberPartition orderNumberPartition = new OrderNumberPartition();
            orderNumberPartition.setCityId(cityId);
            orderNumberPartition.setCreateTime(new Date());
            orderNumberPartition.setPartitionNo(index);
            orderNumberPartition.setNextSeq(100);
            orderNumberPartition.setNumberSequence(JSON.toJSONString(sequenceList));*/
            //int record = orderNumberPartitionService.updateIdByCityIdAndPartitionNo(String cityId, String index);
            int id = 100000 * (citIndex - 1) + index + 1;
            int record = orderNumberPartitionService.updateIdByCityIdAndPartitionNo(cityId, index, id);
            logger.info("insert cityId : {}, partitionNoStart: {}, partitionNoEnd : {}, insert result : {}",
                    cityId, partitionNoStart, partitionNoEnd, record > 0);
        }
    }
}