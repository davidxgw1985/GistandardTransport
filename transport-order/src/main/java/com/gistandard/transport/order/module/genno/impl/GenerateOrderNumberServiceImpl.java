package com.gistandard.transport.order.module.genno.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.entity.bean.ComCity;
import com.gistandard.transport.base.entity.bean.OrderNumberPartition;
import com.gistandard.transport.base.entity.dao.ex.OrderNumberPartitionDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.app.dubbo.genno.GenerateOrderNumberService;
import com.gistandard.transport.order.module.genno.bean.GenOrderNumberPartitionTask;
import com.gistandard.transport.order.module.genno.define.GenerateOrderNumberDefine;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yujie on 2016/9/9.
 */
@Service
public class GenerateOrderNumberServiceImpl implements GenerateOrderNumberService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateOrderNumberServiceImpl.class);

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderNumberPartitionDaoEx orderNumberPartitionDaoEx;

    @Override
    public void createOrderNumberPartitionByCity(List<String> cityList) {
        //线程池初始化大小1000，每个线程执行插入100分区数据
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for(String cityId : cityList){
            for(int i = 0 ; i < GenerateOrderNumberDefine.PARTITION_TOTAL; i = i + 100){
                String[] arr = cityId.split(",");
                GenOrderNumberPartitionTask genOrderNumberPartitionTask = new GenOrderNumberPartitionTask(
                        NumberUtils.toInt(arr[0]), i, i + 100 - 1,NumberUtils.toInt(arr[1])
                );
                executorService.execute(genOrderNumberPartitionTask);
            }
        }
    }

    @Override
    public String getOrderNumberByCityId(Integer cityId) {

        if(cityId == null){
            logger.error(" cityId is null");
            return null;
        }
        Map<String, ComCity> cityMap = comCityService.queryForMap();
        ComCity comCity = cityMap.get(cityId.toString());
        if(comCity == null){
            logger.error("unknown cityId : {}", cityId);
            return null;
        }
        long cityIndex = redisTemplate.opsForValue().increment(GenerateOrderNumberDefine.getOrderNumberIndexKey(cityId), 1);
        int partitionNo =  (int)cityIndex % GenerateOrderNumberDefine.PARTITION_TOTAL;
        //从缓存中取出当前城市和分区对应的序列
        String seqNumJsonStr = (String)redisTemplate.opsForValue().get(GenerateOrderNumberDefine.getOrderNumberSeqKey(cityId, partitionNo));
        //如果不存在，从数据库中查出加载到内存
        if(seqNumJsonStr == null){
            OrderNumberPartition orderNumberPartition = orderNumberPartitionDaoEx.queryByCityIdAndPartitionNo(cityId, partitionNo);
            seqNumJsonStr = orderNumberPartition.getNumberSequence();
            redisTemplate.opsForValue().set(GenerateOrderNumberDefine.getOrderNumberSeqKey(cityId, partitionNo), orderNumberPartition.getNumberSequence(), 10, TimeUnit.MINUTES);
        }
        List<Integer> seqList = JSON.parseArray(seqNumJsonStr, Integer.class);
        //如果当前分区序列已经用完，跳转到下一个未用完的分区，轮训一圈过来如果都使用结束，就返回null
        int searchTime = 0;
        while (seqList.size() == 0 || searchTime == (GenerateOrderNumberDefine.PARTITION_TOTAL - 1)) {
            cityIndex = redisTemplate.opsForValue().increment(GenerateOrderNumberDefine.getOrderNumberIndexKey(cityId), 1);
            partitionNo =  (int)cityIndex % GenerateOrderNumberDefine.PARTITION_TOTAL;
            seqNumJsonStr = (String)redisTemplate.opsForValue().get(GenerateOrderNumberDefine.getOrderNumberSeqKey(cityId, partitionNo));
            if(seqNumJsonStr == null){
                OrderNumberPartition orderNumberPartition = orderNumberPartitionDaoEx.queryByCityIdAndPartitionNo(cityId, partitionNo);
                seqNumJsonStr = orderNumberPartition.getNumberSequence();
                redisTemplate.opsForValue().set(GenerateOrderNumberDefine.getOrderNumberSeqKey(cityId, partitionNo), orderNumberPartition.getNumberSequence(), 10, TimeUnit.MINUTES);
            }
            seqList = JSON.parseArray(seqNumJsonStr, Integer.class);
            searchTime ++;
        }
        //取出序列中第一个
        Integer orderIndex = seqList.get(0);
        seqList.remove(orderIndex);
        //往序列里面补充新的编号
        if(seqList.size() < 100 && seqList.size() > 0){
            Integer lastNumber = seqList.get(seqList.size() - 1);
            //如果序列中最后一个编号大于最大值，就不再放入序列中
            if(lastNumber < (GenerateOrderNumberDefine.SEQUENCE_NUMBER_TOTAL - 1)) {
                seqList.add(lastNumber + 1);
            }
        }
        //更新数据库记录
        orderNumberPartitionDaoEx.updateSeqByCityIdAndPartitionNo(cityId, partitionNo, JSON.toJSONString(seqList),
                seqList.size() != 0 ? (seqList.get(seqList.size() - 1) + 1) : null);
        //更新缓存数据
        redisTemplate.opsForValue().set(GenerateOrderNumberDefine.getOrderNumberSeqKey(cityId, partitionNo), JSON.toJSONString(seqList));
        return GenerateOrderNumberDefine.genOrderNumner(cityId, partitionNo, orderIndex);
    }

}
