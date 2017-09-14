package com.gistandard.transport.app.dubbo.genno;

import java.util.List;

/**
 * Created by yujie on 2016/9/9.
 */
public interface GenerateOrderNumberService {

    void createOrderNumberPartitionByCity(List<String> cityList);

    String getOrderNumberByCityId(Integer cityId);
}
