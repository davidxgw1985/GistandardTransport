package com.gistandard.transport.app.module.station.bean;

import com.gistandard.transport.base.bean.gps.GiPoint;

/**
 * Created by m on 2017/1/12.
 */
public class GiMiRoutePoint {
    private String id;
    private String idGiMiRoutePlan;
    private int seq;
    private GiPoint giPoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdGiMiRoutePlan() {
        return idGiMiRoutePlan;
    }

    public void setIdGiMiRoutePlan(String idGiMiRoutePlan) {
        this.idGiMiRoutePlan = idGiMiRoutePlan;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
    }
}
