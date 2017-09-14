package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ComReason implements Serializable {
    private static final long serialVersionUID = 4280046806824951307L;

    @ApiModelProperty(value = "理由Id",required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "理由类型（1放弃订单理由;2订单失败理由）",required = true, position = 2)
    private Integer tType;

    @ApiModelProperty(value = "理由描述",required = true, position = 3)
    private String reason;

    @ApiModelProperty(value = "理由排序号",required = true, position = 4)
    private Integer tSort;

    @ApiModelProperty(value = "理由语言（1、简体，2、繁体）",required = true, position = 5)
    private Integer lang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer gettSort() {
        return tSort;
    }

    public void settSort(Integer tSort) {
        this.tSort = tSort;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }
}