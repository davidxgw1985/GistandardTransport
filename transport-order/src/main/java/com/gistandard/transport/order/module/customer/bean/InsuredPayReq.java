package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.math.BigDecimal;

/**
 * Created by m on 2016/7/13.
 */
public class InsuredPayReq  extends AppBaseRequest implements ValidTokenMark{
    private String unitCode;//分公司代码
    private String applyNo;//投保单号
    private Integer isPayment;//4-微信对私支付，5-银联对私支付，6-银联对公支付
    private BigDecimal premium;//支付保费金额
    private String successUrl;//微信支付成功跳转页面
    private String failedUrl;//微信失败成功跳转页面

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

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailedUrl() {
        return failedUrl;
    }

    public void setFailedUrl(String failedUrl) {
        this.failedUrl = failedUrl;
    }
}
