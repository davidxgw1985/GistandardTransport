package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author kongxm
 * @ClassName CourierFollowReq
 * @Description 站点关注请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class CourierFollowReq extends AppBaseRequest {
    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    private Integer courierId;
    private Integer roleId;  //角色ID

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
