package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.util.List;

/**
 * Created by shenzhijun on 2016/9/30.
 */
public class AppStationListResult extends AppBasePagerResult {
    private List<Station> data;

    public List<Station> getData() {
        return data;
    }

    public void setData(List<Station> data) {
        this.data = data;
    }
}
