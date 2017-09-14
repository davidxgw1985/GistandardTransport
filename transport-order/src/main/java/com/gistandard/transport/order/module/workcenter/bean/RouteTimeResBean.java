package com.gistandard.transport.order.module.workcenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: RouteTimeResBean
 * @Date: 2017/4/11
 * @Description:
 */
public class RouteTimeResBean implements Serializable{

    private Integer status;
    private String mesasge;
    private List<RouteTime> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMesasge() {
        return mesasge;
    }

    public void setMesasge(String mesasge) {
        this.mesasge = mesasge;
    }

    public List<RouteTime> getData() {
        return data;
    }

    public void setData(List<RouteTime> data) {
        this.data = data;
    }
}
