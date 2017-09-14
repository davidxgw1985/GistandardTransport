package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/9/30.
 */
public class OrderQueryReq  extends AppBasePagerRequest implements ValidTokenMark {
    private String busiBookNo;//对应订仓订单表业务订单号
    private Boolean orderType;//查询类型寄件/收件
    private String startTime;
    private String endTime;
    private Integer areaId;//目的地
    private List<String> itemCodeList;//条目类型编码
    private String startLinkMan;//发件人姓名
    private String startLinkTelephone;//发件人电话号码
    private String startLinkAddress;//发件人地址
    private String receiverTelephone;//收件人手机
    private String receiverName;//收件人姓名
    private String receiverAddress;//收件人地址
    @ApiModelProperty(value = "企业账户ID，只有企业下单需要传。")
    private Integer staffAccountId;

    @ApiModelProperty(value = "模糊查询条件")
    private String searchCondition;

    @ApiModelProperty(value = "订单分类查询条件，1全部 2待支付 3待解冻")
    private Integer orderState;

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public Boolean getOrderType() {
        return orderType;
    }

    public void setOrderType(Boolean orderType) {
        this.orderType = orderType;
    }

    public String getReceiverTelephone() {
        return receiverTelephone;
    }

    public void setReceiverTelephone(String receiverTelephone) {
        this.receiverTelephone = receiverTelephone;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public List<String> getItemCodeList() {
        return itemCodeList;
    }

    public void setItemCodeList(List<String> itemCodeList) {
        this.itemCodeList = itemCodeList;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getStartLinkMan() {
        return startLinkMan;
    }

    public void setStartLinkMan(String startLinkMan) {
        this.startLinkMan = startLinkMan;
    }

    public String getStartLinkTelephone() {
        return startLinkTelephone;
    }

    public void setStartLinkTelephone(String startLinkTelephone) {
        this.startLinkTelephone = startLinkTelephone;
    }

    public String getStartLinkAddress() {
        return startLinkAddress;
    }

    public void setStartLinkAddress(String startLinkAddress) {
        this.startLinkAddress = startLinkAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}