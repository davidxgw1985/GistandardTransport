package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class ScheduCarConfirmKey implements Serializable{
    private static final long serialVersionUID = -4780867466462500478L;
    private String scheducarno;

    private Integer vehicleid;

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }
}