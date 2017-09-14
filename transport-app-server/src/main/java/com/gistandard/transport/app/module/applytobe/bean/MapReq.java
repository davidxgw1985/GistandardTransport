package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.io.Serializable;

/**
 * Created by m on 2016/8/18.
 */
public class MapReq extends AppBaseRequest implements Serializable {

    private String address;

    // 站点经度
    private String staLongitude;

    // 站点纬度
    private String staLatitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(String staLongitude) {
        this.staLongitude = staLongitude;
    }

    public String getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(String staLatitude) {
        this.staLatitude = staLatitude;
    }
}
