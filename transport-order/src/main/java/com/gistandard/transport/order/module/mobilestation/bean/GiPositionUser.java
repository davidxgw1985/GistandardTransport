package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.base.bean.gps.GiPoint;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: GiPositionUser
 * @Date: 2016/10/22
 * @Description:
 */
public class GiPositionUser implements Serializable {
    private static final long serialVersionUID = -7502205601684534774L;

    private GiPoint giPoint;
    private String province;
    private String city;
    private String district;
    private String address;

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
