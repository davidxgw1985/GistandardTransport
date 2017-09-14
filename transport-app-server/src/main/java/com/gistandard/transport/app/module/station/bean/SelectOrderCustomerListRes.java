package com.gistandard.transport.app.module.station.bean;

/**
 * Created by m on 2016/10/8.
 */
public class SelectOrderCustomerListRes {
    private int Status;
    private String Mesasge;
    private Object data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMesasge() {
        return Mesasge;
    }

    public void setMesasge(String mesasge) {
        Mesasge = mesasge;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
