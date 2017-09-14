package com.gistandard.transport.system.logToPsc.bean;

import java.io.Serializable;

/**
 * 物流平台发布物流订单参数
 */
public class LogisticsOrderPara implements Serializable {

    private static final long serialVersionUID = -990375012722346326L;

    private Integer bookingFormId;//订舱订单ID

    private String busiBookNo;//订舱订单号

    public Integer getBookingFormId() {
        return bookingFormId;
    }

    public void setBookingFormId(Integer bookingFormId) {
        this.bookingFormId = bookingFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
