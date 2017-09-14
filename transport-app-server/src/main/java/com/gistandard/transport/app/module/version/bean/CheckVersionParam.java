package com.gistandard.transport.app.module.version.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "版本校验接口参数对象", parent = AppBaseRequest.class)
public class CheckVersionParam extends AppBaseRequest {

    private static final long serialVersionUID = 1669314496616748862L;
    //操作系统
    @ApiModelProperty(value = "操作系统代码",required = true, position = 1)
    private String os;

    //项目名称
    @ApiModelProperty(value = "项目名称",required = true, position = 2)
    private String project;

    //当前版本
    @ApiModelProperty(value = "当前版本号",required = true, position = 3)
    private String version;

    //当前版本Interr
    @ApiModelProperty(value = "当前版本Interr",required = true, position = 3)
    private Integer versionCode;

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

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
}
