package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/7/13.
 */
public class InsuredPayRes implements Serializable {
    private static final long serialVersionUID = 5755044170055327346L;
    private String unitCode;//分公司代码
    private String applyNo;//投保单号
    private String payApplyNo;//支付号/订单号
    private String payFeeAddress;//支付URL

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPayApplyNo() {
        return payApplyNo;
    }

    public void setPayApplyNo(String payApplyNo) {
        this.payApplyNo = payApplyNo;
    }

    public String getPayFeeAddress() {
        return payFeeAddress;
    }

    public void setPayFeeAddress(String payFeeAddress) {
        this.payFeeAddress = payFeeAddress;
    }
}
