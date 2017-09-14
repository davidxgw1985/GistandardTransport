package com.gistandard.transport.app.module.feedBack.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/7
 */
@ApiModel(description = "反馈信息新增接口参数对象", parent = AppBaseRequest.class)
public class FeedBackSaveReq extends AppBaseRequest {

    @ApiModelProperty(value = "系统标识[1-客户下单(通用快递)  2-客户下单(通用运输)]",required = true, position = 1)
    private Integer systemFlag;

    @ApiModelProperty(value = "反馈内容",required = true, position = 2)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(Integer systemFlag) {
        this.systemFlag = systemFlag;
    }

}
