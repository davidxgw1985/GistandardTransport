package com.gistandard.transport.app.module.station.bean;

import com.gistandard.transport.base.bean.gps.GiPoint;

/**
 * Created by m on 2016/12/30.
 */
public class GiMiRouteStop {
    private String id; //值赋为: idGiMiRoutePlan-seq
    private String idGiMiRoutePlan;
    private long seq;
    private String idGiMiStop;
    private String code;
    private String name;
    private String address;
    private String remark;
    private GiPoint giPoint;
    //返回给前台距离
    private Double distance; //单位：米

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

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getIdGiMiStop() {
        return idGiMiStop;
    }

    public void setIdGiMiStop(String idGiMiStop) {
        this.idGiMiStop = idGiMiStop;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
