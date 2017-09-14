package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class FleetAccountInfo extends VehicleInfoBean implements Serializable {
    private static final long serialVersionUID = -3209922584485961833L;

    private String acctUsername;

    private String realName;

    private String telephone;

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
