package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "身份证正反面验证参数对象")
public class CheckIdentityFileReq extends AppBaseRequest implements Serializable {

    @ApiModelProperty(value = "身份证正面附件ID", required = true)
    Integer identityPositiveFileId;

    @ApiModelProperty(value = "身份证反面附件ID", required = true)
    Integer identityNegativeFileId;

    @ApiModelProperty(value = "居住地区", required = true)
    String area;

    public Integer getIdentityNegativeFileId() {
        return identityNegativeFileId;
    }

    public void setIdentityNegativeFileId(Integer identityNegativeFileId) {
        this.identityNegativeFileId = identityNegativeFileId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIdentityPositiveFileId() {
        return identityPositiveFileId;
    }

    public void setIdentityPositiveFileId(Integer identityPositiveFileId) {
        this.identityPositiveFileId = identityPositiveFileId;
    }

}
