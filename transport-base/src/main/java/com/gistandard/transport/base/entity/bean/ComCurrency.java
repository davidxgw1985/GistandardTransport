package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ComCurrency implements Serializable {

    private static final long serialVersionUID = 742174326307124296L;

    @ApiModelProperty(value = "币制Id",required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "币制代码",required = true, position = 2)
    private String currencyCode;

    @ApiModelProperty(value = "币制英文",required = true, position = 3)
    private String currencyEn;

    @ApiModelProperty(value = "币制中文",required = true, position = 4)
    private String currencyCh;

    @ApiModelProperty(value = "币制排序号",required = true, position = 5)
    private Integer sortid;

    @ApiModelProperty(value = "状态标识(1、可用)",required = true, position = 6)
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