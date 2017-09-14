package com.gistandard.transport.order.module.mobilestation.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2016/12/14.
 */
public class MobileStationAcceptOrderCustomResult  implements Serializable {

    @ApiModelProperty(value = "接单成功列表", required = true, position = 1)
    private List<MobileStationAcceptOrderCustomReq> successList;
    @ApiModelProperty(value = "接单失败列表", required = true, position = 2)
    private List<MobileStationAcceptOrderCustomReq> failList;
    @ApiModelProperty(value = "接单成功个数", required = true, position = 3)
    private Integer successCount;
    @ApiModelProperty(value = "接单失败个数", required = true, position = 4)
    private Integer failCount;

    public List<MobileStationAcceptOrderCustomReq> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<MobileStationAcceptOrderCustomReq> successList) {
        this.successList = successList;
    }

    public List<MobileStationAcceptOrderCustomReq> getFailList() {
        return failList;
    }

    public void setFailList(List<MobileStationAcceptOrderCustomReq> failList) {
        this.failList = failList;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }
}
