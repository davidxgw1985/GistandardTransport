package com.gistandard.transport.system.center.search.service;

import com.gistandard.transport.system.center.search.bean.QueryDistanceByLatAndLonResult;

import java.math.BigDecimal;

/**
 * Created by m on 2016/10/7.
 */
public interface SenderSearchService {
    QueryDistanceByLatAndLonResult queryDistanceByLatAndLon(String fromCode, String arriveCode, BigDecimal fromLat, BigDecimal fromLon, BigDecimal arriveLat, BigDecimal arriveLon);
}
