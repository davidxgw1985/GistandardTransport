package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchOperateReq
 * @Date: 2016/3/1
 * @Description: 批量操作(发车、送达确认)请求对象
 */
@ApiModel(description = "批量操作(发车、送达确认)请求对象", parent = AppBaseRequest.class)
public class BatchOperateReq extends AppBaseRequest {

    private static final long serialVersionUID = -4777523951133241411L;

    @ApiModelProperty(value = "0物流  1运输  2快递",required = true, position = 1)
    private String transportType;
    @ApiModelProperty(value = "取消订单登录用户的角色，必填",required = true, position = 2)
    private Integer roleId;
    @ApiModelProperty(value = "批量操作(发车、送达确认)请求对象", required = true, position = 3)
    private List<OperateReqBean> depatReqBeanList;

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<OperateReqBean> getDepatReqBeanList() {
        return depatReqBeanList;
    }

    public void setDepatReqBeanList(List<OperateReqBean> depatReqBeanList) {
        this.depatReqBeanList = depatReqBeanList;
    }
}
