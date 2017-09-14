package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/10/7.
 */
@ApiModel(description = "查询订单详情请求参数", parent = AppBaseRequest.class)
public class QueryOrderDetailReq extends AppBaseRequest {
    @ApiModelProperty(value = "扫描订单号",required = true, position = 1)
    private String busiBookNo;  //扫描订单号

    @ApiModelProperty(value = "订单号类型", required = true, position = 2)
    private String busiNoTag;

    @ApiModelProperty(value = "角色ID",required = true, position = 3)
    private Integer roleId;

    @ApiModelProperty(value = "订单状态，可以为空",required = false, position = 4)
    private Integer busiCtrl;

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public String getBusiNoTag() {
        return busiNoTag;
    }

    public void setBusiNoTag(String busiNoTag) {
        this.busiNoTag = busiNoTag;
    }
}
