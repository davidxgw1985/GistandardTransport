package com.gistandard.transport.order.webservice.server.mobilestation.bean;


import com.gistandard.platform.pojo.req.AppBasePagerRequest;

import java.io.Serializable;

/**
 * @Author zxnui
 * @ClassName
 * @Description
 * @Date 16-7-27.
 */
public class MobileWantReq extends AppBasePagerRequest implements Serializable {
    private static final long serialVersionUID = -6239434343410101449L;
    private Integer lineStart;//接单区域
    private Integer lineDest;//抵达区域
    private String stationName;//站点名称
    private String userName;//快递员名称

    public Integer getLineStart() {
        return lineStart;
    }

    public void setLineStart(Integer lineStart) {
        this.lineStart = lineStart;
    }

    public Integer getLineDest() {
        return lineDest;
    }

    public void setLineDest(Integer lineDest) {
        this.lineDest = lineDest;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
