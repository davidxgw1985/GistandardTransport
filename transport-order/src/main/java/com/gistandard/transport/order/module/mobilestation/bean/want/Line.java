package com.gistandard.transport.order.module.mobilestation.bean.want;

import java.util.Date;

/**
 * @author zxnui
 * @ClassName QueryQuoteListReq
 * @Description 我要接单、我要运输 线路Bean
 * @Version 1.0
 * @Date 2016-7-20
 */
public class Line {

    private String lineStart;
    private String lineDest;
    private Date departTime;
    private Date arriveTime;

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineDest() {
        return lineDest;
    }

    public void setLineDest(String lineDest) {
        this.lineDest = lineDest;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }
}
