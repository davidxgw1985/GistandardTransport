package com.gistandard.transport.order.module.mistation.schedu.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.entity.bean.MobileValueAdd;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MiBidInitiationReq
 * @Date: 2017/6/23
 * @Description: 咪站竞价请求对象
 */
public class MiBidInitiationReq extends AppBaseRequest{
    private static final long serialVersionUID = 1819182301137327188L;

    private Integer orderId;
    private BigDecimal selfQuoteValue;//咪站自报价
    private String selfQuoteCurr;//咪站自报价币制
    private Integer vehicleTypeId;//车辆类型
    private List<MobileValueAdd> valueAddList;//增值服务列表
    private BigDecimal mileage;//公里数

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public List<MobileValueAdd> getValueAddList() {
        return valueAddList;
    }

    public void setValueAddList(List<MobileValueAdd> valueAddList) {
        this.valueAddList = valueAddList;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }
}
