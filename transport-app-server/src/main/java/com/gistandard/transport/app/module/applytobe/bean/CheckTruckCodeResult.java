package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
public class CheckTruckCodeResult extends AppBaseResult implements Serializable {

    private boolean data;

    public CheckTruckCodeResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public boolean getData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

}
