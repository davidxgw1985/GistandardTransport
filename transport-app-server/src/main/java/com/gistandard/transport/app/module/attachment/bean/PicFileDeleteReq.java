package com.gistandard.transport.app.module.attachment.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author: xgw
 * @ClassName: PicFileDeleteReq
 * @Date: 2016/3/4
 * @Description:
 */
@ApiModel(description = "附件删除参数对象", parent = AppBaseRequest.class)
public class PicFileDeleteReq extends AppBaseRequest {

    @ApiModelProperty(value = "附件Id",required = true, position = 1)
    private String picFileId;//图片文件编号

    public String getPicFileId() {
        return picFileId;
    }

    public void setPicFileId(String picFileId) {
        this.picFileId = picFileId;
    }
}
