package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class MobileTransportRel implements Serializable {
    private static final long serialVersionUID = -6587469790318191260L;
    private Integer id;

    private Integer wantId;

    private Integer stationId;

    private String stationTtl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWantId() {
        return wantId;
    }

    public void setWantId(Integer wantId) {
        this.wantId = wantId;
    }

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
}