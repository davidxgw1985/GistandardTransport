package com.gistandard.transport.order.module.mobilestation.bean.want;

/**
 * @author zxnui
 * @ClassName QueryQuoteListReq
 * @Description 我要接单、我要运输 站点Bean
 * @Version 1.0
 * @Date 2016-7-20
 */
public class Station {

    private Integer stationId;
    private String stationTtl;
    private Integer wantId;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationTtl() {
        return stationTtl;
    }

    public void setStationTtl(String stationTtl) {
        this.stationTtl = stationTtl;
    }

    public Integer getWantId() {
        return wantId;
    }

    public void setWantId(Integer wantId) {
        this.wantId = wantId;
    }
}
