package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileSingleCenter implements Serializable {
    private static final long serialVersionUID = 1232830813633759937L;
    private Integer id;

    private Integer mobileBookingFormId;

    private String busiBookNo;

    private Integer busiCtrl;

    private String comQuoteId;

    private Integer quotedType;

    private Integer dispatchId;

    private Integer createUserId;

    private String createUser;

    private Date createDate;

    private Date singleDate;

    private String teamComsixNo;

    private Integer revUserId;

    private String revUser;

    private Date revDate;

    private String productType;

    private BigDecimal singleValue;

    private String singleCurr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public Integer getQuotedType() {
        return quotedType;
    }

    public void setQuotedType(Integer quotedType) {
        this.quotedType = quotedType;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSingleDate() {
        return singleDate;
    }

    public void setSingleDate(Date singleDate) {
        this.singleDate = singleDate;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(BigDecimal singleValue) {
        this.singleValue = singleValue;
    }

    public String getSingleCurr() {
        return singleCurr;
    }

    public void setSingleCurr(String singleCurr) {
        this.singleCurr = singleCurr;
    }
}