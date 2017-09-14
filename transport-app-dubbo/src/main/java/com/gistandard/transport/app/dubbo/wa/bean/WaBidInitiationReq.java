package com.gistandard.transport.app.dubbo.wa.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: WaBidInitiationReq
 * @Date: 2017/7/4
 * @Description: 蛙站M-W发起竞价接口请求bean
 */
public class WaBidInitiationReq implements Serializable{
    private static final long serialVersionUID = -6775658203305050149L;

    private String busiBookNo;//M-W的派车单号
    private BigDecimal selfQuoteValue;//蛙站自报价
    private String selfQuoteCurr;//蛙站自报价币制
    private String bidUser;//发起竞价蛙站账号
    private Integer bidUserId;//发起竞价蛙站账号Id
    private Integer vehicleTypeId;//发起竞价蛙站同城运输客户要求车型ID
    private String custTtl;//发起竞价蛙站简称
    List<MobileValueAddDubbo> valueAddList;//增值服务列表

    public List<MobileValueAddDubbo> getValueAddList() {
        return valueAddList;
    }

    public void setValueAddList(List<MobileValueAddDubbo> valueAddList) {
        this.valueAddList = valueAddList;
    }

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public BigDecimal getSelfQuoteValue() {
        return selfQuoteValue;
    }

    public void setSelfQuoteValue(BigDecimal selfQuoteValue) {
        this.selfQuoteValue = selfQuoteValue;
    }

    public String getSelfQuoteCurr() {
        return selfQuoteCurr;
    }

    public void setSelfQuoteCurr(String selfQuoteCurr) {
        this.selfQuoteCurr = selfQuoteCurr;
    }

    public String getBidUser() {
        return bidUser;
    }

    public void setBidUser(String bidUser) {
        this.bidUser = bidUser;
    }

    public Integer getBidUserId() {
        return bidUserId;
    }

    public void setBidUserId(Integer bidUserId) {
        this.bidUserId = bidUserId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
