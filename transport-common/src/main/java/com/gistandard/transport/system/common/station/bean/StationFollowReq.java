package com.gistandard.transport.system.common.station.bean;


import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * @author kongxm
 * @ClassName CourierFollowReq
 * @Description 站点关注请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class StationFollowReq extends AppBaseRequest implements ValidTokenMark {
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    private Integer stationId;
    private Integer roleId;  //角色ID

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
