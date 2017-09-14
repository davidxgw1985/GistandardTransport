package com.gistandard.transport.app.dubbo.order.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileOperateInfo
 * @Date: 2017/5/16
 * @Description:
 */
@ApiModel(description = "订单货物信息")
public class MobileOperateInfo {

    @ApiModelProperty(value = "描述信息", position = 1)
    private String description;
    @ApiModelProperty(value = "操作类型 1拒绝 2取消 3派送失败", position = 2)
    private Integer operType;
    @ApiModelProperty(value = "操作人账号", position = 3)
    private String createUser;
    @ApiModelProperty(value = "操作人", position = 4)
    private Integer createUserId;
    @ApiModelProperty(value = "操作人姓名", position = 5)
    private String createUserName;
    @ApiModelProperty(value = "操作时间", position = 6)
    private Date createDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
