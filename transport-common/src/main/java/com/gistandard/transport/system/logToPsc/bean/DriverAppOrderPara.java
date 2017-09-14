package com.gistandard.transport.system.logToPsc.bean;

import java.io.Serializable;

/**
 * 司机APP接单参数
 */
public class DriverAppOrderPara implements Serializable{

    private String busiBookNo;//订舱订单号

    private Integer scheduleSheetId;//签派从表ID

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getScheduleSheetId() {
        return scheduleSheetId;
    }

    public void setScheduleSheetId(Integer scheduleSheetId) {
        this.scheduleSheetId = scheduleSheetId;
    }
}
