package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class BizAttachmentRecord implements Serializable {

    private Integer attachId;

    private String attachName;

    private String attachOldName;

    private String attachDomain;

    private String attachPath;

    private String projectPath;

    private String attachType;

    private Integer attachState;

    private String attachSize;

    private Integer attachRelId;

    private String attachServerIp;

    private Integer uploadUser;

    private Date uploadTime;

    private Integer fileType;

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachOldName() {
        return attachOldName;
    }

    public void setAttachOldName(String attachOldName) {
        this.attachOldName = attachOldName;
    }

    public String getAttachDomain() {
        return attachDomain;
    }

    public void setAttachDomain(String attachDomain) {
        this.attachDomain = attachDomain;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public Integer getAttachState() {
        return attachState;
    }

    public void setAttachState(Integer attachState) {
        this.attachState = attachState;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

    public Integer getAttachRelId() {
        return attachRelId;
    }

    public void setAttachRelId(Integer attachRelId) {
        this.attachRelId = attachRelId;
    }

    public String getAttachServerIp() {
        return attachServerIp;
    }

    public void setAttachServerIp(String attachServerIp) {
        this.attachServerIp = attachServerIp;
    }

    public Integer getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(Integer uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}