package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

public class ComCurrency implements Serializable {
    private static final long serialVersionUID = 742174326307124296L;
    private Integer id;

    private String currencyCode;

    private String currencyEn;

    private String currencyCh;

    private Integer sortid;

    private Integer isuse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyEn() {
        return currencyEn;
    }

    public void setCurrencyEn(String currencyEn) {
        this.currencyEn = currencyEn;
    }

    public String getCurrencyCh() {
        return currencyCh;
    }

    public void setCurrencyCh(String currencyCh) {
        this.currencyCh = currencyCh;
    }

    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
}