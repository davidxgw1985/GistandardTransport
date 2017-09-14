package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class SysExceptionLog implements Serializable{
    private static final long serialVersionUID = -5412312595279647853L;
    private Integer id;

    private String exClass;

    private String exMessage;

    private String exUser;

    private Integer customExNo;

    private Date createTime;

    private Integer exStatus;

    private String exDesc;

    private Date solveTime;

    private Integer solveUser;

    private Integer exLevel;

    private String exParam;

    private String exCause;

    private String fromSys;

    public String getExParam() {
        return exParam;
    }

    public void setExParam(String exParam) {
        this.exParam = exParam;
    }

    public String getExCause() {
        return exCause;
    }

    public void setExCause(String exCause) {
        this.exCause = exCause;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExClass() {
        return exClass;
    }

    public void setExClass(String exClass) {
        this.exClass = exClass;
    }

    public String getExMessage() {
        return exMessage;
    }

    public void setExMessage(String exMessage) {
        this.exMessage = exMessage;
    }

    public String getExUser() {
        return exUser;
    }

    public void setExUser(String exUser) {
        this.exUser = exUser;
    }

    public Integer getCustomExNo() {
        return customExNo;
    }

    public void setCustomExNo(Integer customExNo) {
        this.customExNo = customExNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExStatus() {
        return exStatus;
    }

    public void setExStatus(Integer exStatus) {
        this.exStatus = exStatus;
    }

    public String getExDesc() {
        return exDesc;
    }

    public void setExDesc(String exDesc) {
        this.exDesc = exDesc;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public Integer getSolveUser() {
        return solveUser;
    }

    public void setSolveUser(Integer solveUser) {
        this.solveUser = solveUser;
    }

    public Integer getExLevel() {
        return exLevel;
    }

    public void setExLevel(Integer exLevel) {
        this.exLevel = exLevel;
    }

    public String getFromSys() {
        return fromSys;
    }

    public void setFromSys(String fromSys) {
        this.fromSys = fromSys;
    }
}