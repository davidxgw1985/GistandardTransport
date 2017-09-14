package com.gistandard.transport.app.module.gps.bean;

import java.io.Serializable;

/**
 * Created by yjf on 2016/9/30.
 */
public class GpsTokenData implements Serializable {
    private static final long serialVersionUID = 5632495429718728898L;
    private  String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
