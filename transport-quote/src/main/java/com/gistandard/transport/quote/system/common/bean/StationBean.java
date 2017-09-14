package com.gistandard.transport.quote.system.common.bean;

import java.io.Serializable;

/**
 * Created by shenzhijun on 2016/2/25.
 */
public class StationBean implements Serializable{
    private static final long serialVersionUID = -2098258864701700605L;
    private int id;

    private String customNo;

    private String stationCode;

    private String custName;

    private String custAdd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }
}
