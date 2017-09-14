package com.gistandard.transport.app.module.version.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
public class VersionInfo {

    @ApiModelProperty(value = "是否升级标识（0：不需要升级，1:需要升级）",required = true)
    private int action;

    @ApiModelProperty(value = "是否强制升级标识（0：不需要强制升级，1:需要强制升级）",required = true)
    private int forceUpdate;

    @ApiModelProperty(value = "当前版本号",required = true)
    private String version;

    @ApiModelProperty(value = "当前版本号",required = true)
    private Integer versionCode;

    @ApiModelProperty(value = "升级包地址",required = true)
    private String filePath;

    public VersionInfo(){
        action = 0;
        forceUpdate = 0;
        version = "";
        versionCode = 0;
        filePath = "";
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
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

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
}
