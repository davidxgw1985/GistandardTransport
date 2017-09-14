package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: OrderResBean
 * @Date: 2016/12/23
 * @Description:
 */
@ApiModel(description = "订单单个图片上传返回对象")
public class OrderPicUploadResBean implements Serializable {
    private static final long serialVersionUID = 2870075048660011089L;
    @ApiModelProperty(value = "文件编号", position = 1)
    private String fileId;
    @ApiModelProperty(value = "订单号", position = 2)
    private String busiBookNo;
    @ApiModelProperty(value = "操作结果 1成功 其他失败", position = 3)
    private Integer retCode = 1;
    @ApiModelProperty(value = "操作提示", position = 4)
    private String retMsg = "上传成功";

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
