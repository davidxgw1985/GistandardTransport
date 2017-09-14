package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class AppVersionInfo implements Serializable{
    private static final long serialVersionUID = -5285946648984368339L;
    private Integer appVersionId;

    private String os;

    private String project;

    private String version;

    private String filePath;

    private Short status;

    private Short forceUpdate;

    private String remark;

    private Date modifyTime;

    private String modifyUser;

    private Integer versionCode;

    public Integer getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(Integer appVersionId) {
        this.appVersionId = appVersionId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Short forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
}