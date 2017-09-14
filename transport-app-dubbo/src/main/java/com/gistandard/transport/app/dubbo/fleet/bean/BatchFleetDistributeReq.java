package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchFleetDistributeReq
 * @Date: 2017/6/21
 * @Description: 批量分发请求对象
 */
public class BatchFleetDistributeReq extends MsDubboReqBean {

    private String fleetCode;

    private String driverCode;

    private String truckCode;

    private BigDecimal driverBidValue;//报价给司机的价格

    private String driverBidCurr;//报价给司机的价格币制

    private List<VehicleOrder> allDocument;

    private Boolean distriAgainFlag;//再分发的标识

    public String getFleetCode() {
        return fleetCode;
    }

    public void setFleetCode(String fleetCode) {
        this.fleetCode = fleetCode;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public List<VehicleOrder> getAllDocument() {
        return allDocument;
    }

    public void setAllDocument(List<VehicleOrder> allDocument) {
        this.allDocument = allDocument;
    }

    public BigDecimal getDriverBidValue() {
        return driverBidValue;
    }

    public void setDriverBidValue(BigDecimal driverBidValue) {
        this.driverBidValue = driverBidValue;
    }

    public String getDriverBidCurr() {
        return driverBidCurr;
    }

    public void setDriverBidCurr(String driverBidCurr) {
        this.driverBidCurr = driverBidCurr;
    }

    public Boolean getDistriAgainFlag() {
        return distriAgainFlag;
    }

    public void setDistriAgainFlag(Boolean distriAgainFlag) {
        this.distriAgainFlag = distriAgainFlag;
    }
}
