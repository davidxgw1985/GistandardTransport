package com.gistandard.transport.calculate.bean.calc;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: FindAllValidBillMstBean
 * @Date: 2016/10/21
 * @Description:
 */
public class FindAllValidBillMstBean {
    private String currencyCode;
    private String currencyName;
    private String docNo;
    private int initDoc;
    private String initDocNo;
    private int payStatus;
    private BigDecimal price;
    private String recGFUserCode;
    private String recGFUserName;
    private String tsCreated;
    private String productType;
    protected Integer testFlag;//是否测试单  0真实单，1测试单

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Integer testFlag) {
        this.testFlag = testFlag;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public int getInitDoc() {
        return initDoc;
    }

    public void setInitDoc(int initDoc) {
        this.initDoc = initDoc;
    }

    public String getInitDocNo() {
        return initDocNo;
    }

    public void setInitDocNo(String initDocNo) {
        this.initDocNo = initDocNo;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(String tsCreated) {
        this.tsCreated = tsCreated;
    }
}
