package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: GetExpressScheduCarInfoRes
 * @Date: 2016/9/23
 * @Description:
 */
public class GetExpressScheduCarInfoBean implements Serializable{
    private static final long serialVersionUID = -3624089471546278035L;

    private String scheduCarId;//派车单号
    private BigDecimal revWeight;//派车单总重量
    private BigDecimal revVolume;//派车单总体积

    public String getScheduCarId() {
        return scheduCarId;
    }

    public void setScheduCarId(String scheduCarId) {
        this.scheduCarId = scheduCarId;
    }

    public BigDecimal getRevWeight() {
        return revWeight;
    }

    public void setRevWeight(BigDecimal revWeight) {
        this.revWeight = revWeight;
    }

    public BigDecimal getRevVolume() {
        return revVolume;
    }

    public void setRevVolume(BigDecimal revVolume) {
        this.revVolume = revVolume;
    }
}
