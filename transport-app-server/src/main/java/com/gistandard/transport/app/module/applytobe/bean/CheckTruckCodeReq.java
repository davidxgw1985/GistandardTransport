package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.io.Serializable;

/**
 * Created by m on 2016/8/18.
 */
public class CheckTruckCodeReq extends AppBaseRequest implements Serializable {

    private String truckcode;

    public String getTruckcode() {
        return truckcode;
    }

    public void setTruckcode(String truckcode) {
        this.truckcode = truckcode;
    }
}
