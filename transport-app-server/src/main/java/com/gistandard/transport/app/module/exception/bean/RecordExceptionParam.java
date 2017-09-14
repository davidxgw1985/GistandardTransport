package com.gistandard.transport.app.module.exception.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
public class RecordExceptionParam extends AppBaseRequest{

    //异常信息
    @ApiModelProperty(value = "异常信息",required = true, position = 1)
    private String exceptionInfo;

    //用户编号
    @ApiModelProperty(value = "异常发生用户帐号Id",required = true, position = 2)
    private String userId;

    //应用名称
    @ApiModelProperty(value = "应用名称",required = true, position = 3)
    private String appName;

    //异常代码
    @ApiModelProperty(value = "异常代码", position = 4)
    private String exceptionCode;

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
