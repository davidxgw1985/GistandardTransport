package com.gistandard.transport.gps.bean;


import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.io.Serializable;

/**
 * Created by yjf on 2016/9/30.
 */
public class GpsTrackReq  extends AppBaseRequest implements Serializable,ValidTokenMark {
    private String events;

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }
}
