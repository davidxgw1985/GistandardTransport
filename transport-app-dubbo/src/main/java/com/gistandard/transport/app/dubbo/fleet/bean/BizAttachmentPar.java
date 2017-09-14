package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class BizAttachmentPar implements Serializable {
    private static final long serialVersionUID = -8068833427833138601L;

    private Integer attachId;

    //文件名称
    private String attachName;

    //原文件名称
    private String attachOldName;

    //fastdfs地址
    private String attachDomain;

    //路径
    private String attachPath;

    //文件后缀
    private String attachType;

    //文件大小
    private String attachSize;

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

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}
