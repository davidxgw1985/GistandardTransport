package com.gistandard.transport.system.center.search.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.math.BigDecimal;

/**
 * Created by m on 2016/10/7.
 */
public class QueryDistanceByLatAndLonResult extends AppBasePagerResult {
    private BigDecimal data;

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }
}
