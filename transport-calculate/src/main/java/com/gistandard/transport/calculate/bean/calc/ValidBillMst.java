package com.gistandard.transport.calculate.bean.calc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: ValidBillMst
 * @Date: 2016/3/15
 * @Description:
 */
public class ValidBillMst implements Serializable {
    private static final long serialVersionUID = -1051031020701569802L;

    private String docNo;//对账单号
    private String recGFUserCode;//收款账户
    private String recGFUserName;//收款账户名
    private BigDecimal price;    //	价格
    private String currency; //	货币单位
    private Date tsCreated;//对账单创建时间
    private int payStatus;//0未支付 1已预支付 2支付成功 3已支付操作但失败

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getRecGFUserCode() {
        return recGFUserCode;
    }

    public void setRecGFUserCode(String recGFUserCode) {
        this.recGFUserCode = recGFUserCode;
    }

    public String getRecGFUserName() {
        return recGFUserName;
    }

    public void setRecGFUserName(String recGFUserName) {
        this.recGFUserName = recGFUserName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(Date tsCreated) {
        this.tsCreated = tsCreated;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }
}
