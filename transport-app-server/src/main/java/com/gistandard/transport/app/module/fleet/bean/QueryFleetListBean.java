package com.gistandard.transport.app.module.fleet.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: QueryFleetListBean
 * @Date: 2017/7/18
 * @Description:
 */
@ApiModel(description = "查询车队列表返回Bean")
public class QueryFleetListBean implements Serializable{
    private static final long serialVersionUID = -1300081905319339366L;

    @ApiModelProperty(value = "车队账号ID", position = 1)
    private Integer fleetAccountId;
    @ApiModelProperty(value = "车队账号", position = 1)
    private String fleetAccount;
    @ApiModelProperty(value = "车队名称", position = 1)
    private String fleetName;
    @ApiModelProperty(value = "车队头像url", position = 1)
    private String headImgUrl;
    @ApiModelProperty(value = "关注状态：1已关注0未关注", position = 1)
    private Integer attFlag;

    public Integer getFleetAccountId() {
        return fleetAccountId;
    }

    public void setFleetAccountId(Integer fleetAccountId) {
        this.fleetAccountId = fleetAccountId;
    }

    public String getFleetAccount() {
        return fleetAccount;
    }

    public void setFleetAccount(String fleetAccount) {
        this.fleetAccount = fleetAccount;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getAttFlag() {
        return attFlag;
    }

    public void setAttFlag(Integer attFlag) {
        this.attFlag = attFlag;
    }
}
