package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/10/29.
 */
@ApiModel(description = "咪站客户自提入参", parent = AppBaseRequest.class)
public class GetGoodsMySelfReq extends AppBaseRequest {
    @ApiModelProperty(value = "订单号", required = true, position = 1)
    private String busibookno;
    @ApiModelProperty(value = "角色", required = true, position = 2)
    private Integer roleId;

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
