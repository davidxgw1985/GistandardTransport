package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

public class ComAllType implements Serializable {
    private static final long serialVersionUID = 4214300652182607448L;
    private Integer id;

    private String tType;

    private String tCode;

    private String tName;

    private String tRemark;

    private Boolean tFalg;

    private Integer tSort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettRemark() {
        return tRemark;
    }

    public void settRemark(String tRemark) {
        this.tRemark = tRemark;
    }

    public Boolean gettFalg() {
        return tFalg;
    }

    public void settFalg(Boolean tFalg) {
        this.tFalg = tFalg;
    }

    public Integer gettSort() {
        return tSort;
    }

    public void settSort(Integer tSort) {
        this.tSort = tSort;
    }
}