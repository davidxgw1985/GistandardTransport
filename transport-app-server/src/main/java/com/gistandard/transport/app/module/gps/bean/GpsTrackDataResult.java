package com.gistandard.transport.app.module.gps.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by yjf on 2016/9/30.
 */
public class GpsTrackDataResult extends AppBaseResult {
    private GpsTrackData data;

    public GpsTrackData getData() {
        return data;
    }

    public void setData(GpsTrackData data) {
        this.data = data;
    }
}
