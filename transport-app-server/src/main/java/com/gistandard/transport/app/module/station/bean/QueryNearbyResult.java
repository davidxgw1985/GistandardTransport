package com.gistandard.transport.app.module.station.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.system.common.station.bean.Station;

import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
public class QueryNearbyResult extends AppBasePagerResult {
    private List<Station> data;

    public List<Station> getData() {
        return data;
    }

    public void setData(List<Station> data) {
        this.data = data;
    }
}
