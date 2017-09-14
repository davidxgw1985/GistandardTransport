package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileStationAcceptOrderReq
 * @Date: 2016/1/27
 * @Description: 接单请求Bean
 */
public class MobileStationBatchAcceptOrderReq extends AppBaseRequest implements ValidTokenMark {

    private static final long serialVersionUID = 716462359547529722L;
    @ApiModelProperty(value = "接单形式 3司机、7快递员 、23咪站", required = true, position = 1)
    private Integer roleId; //接单形式 3司机、7快递员 、23咪站
    @ApiModelProperty(value = "接单列表", required = true, position = 2)
    private List<MobileStationAcceptOrderCustomReq> mobileStationAcceptOrderReqList;
    private Integer assignUserId;//商管中心指派人账号ID
    private String assignUser;//商管中心指派人账号
    private Date assignDate;//商管中心指派时间

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<MobileStationAcceptOrderCustomReq> getMobileStationAcceptOrderReqList() {
        return mobileStationAcceptOrderReqList;
    }

    public void setMobileStationAcceptOrderReqList(List<MobileStationAcceptOrderCustomReq> mobileStationAcceptOrderReqList) {
        this.mobileStationAcceptOrderReqList = mobileStationAcceptOrderReqList;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }
}
