package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.util.List;

/**
 * @author xgw
 * @ClassName AgencyOrderListReq
 * @Description MS3.0代理下单-订单查询请求Bean
 * @Version 3.0
 * @Date 2016/6/23
 */
public class AgencyOrderListReq extends AppBasePagerRequest implements ValidTokenMark {

    private String acctUsername;//MS登录账号名称
    private String shipperName;//发件人姓名
    private String receiverName;//收件人姓名
    private String startTime;
    private String endTime;
    private Integer areaId;//目的地
    private List<String> productTypeList;
    private Integer roleId; //角色ID

    public List<String> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<String> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}