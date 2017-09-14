package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: MobileOrderInfo
 * @Date: 2017/5/23
 * @Description:
 */
public class MobileOrderInfo implements Serializable {
    private static final long serialVersionUID = 6600827942851639844L;

    private Integer orderId;

    private Integer createUserId;//下单人账号ID
    private Integer createCompanyId;

    private Integer revUserId;//接单人账号ID

    private String revUser;//接单人账号

    private String realName;//接单人姓名

    private String busiBookNo;

    private Integer busiCtrl;

    private Integer roleId;//角色ID

    private String startLocus;

    private String destnLocus;

    private Integer orderFrom;//订单来源

    private Integer revCompanyId;

    private BigDecimal predictValue;

    private String bidBy;

    private Integer bidUserId;

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getBidBy() {
        return bidBy;
    }

    public void setBidBy(String bidBy) {
        this.bidBy = bidBy;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public Integer getBidUserId() {
        return bidUserId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(Integer createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    public void setBidUserId(Integer bidUserId) {
        this.bidUserId = bidUserId;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
