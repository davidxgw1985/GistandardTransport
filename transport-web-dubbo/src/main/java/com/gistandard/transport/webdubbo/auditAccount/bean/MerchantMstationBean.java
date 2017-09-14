package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商户用户咪站认证资料填写bean
 */
public class MerchantMstationBean implements Serializable{

    // 详细地址
    private String address;

    private String detailAdd;

    // 站点经度
    private BigDecimal staLongitude;

    // 站点纬度
    private BigDecimal staLatitude;

    // 紧急联系人
    private String urgentLinkUser;

    // 紧急联系电话
    private String urgentLinkTel;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAdd() {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd) {
        this.detailAdd = detailAdd;
    }

    public BigDecimal getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(BigDecimal staLongitude) {
        this.staLongitude = staLongitude;
    }

    public BigDecimal getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(BigDecimal staLatitude) {
        this.staLatitude = staLatitude;
    }

    public String getUrgentLinkUser() {
        return urgentLinkUser;
    }

    public void setUrgentLinkUser(String urgentLinkUser) {
        this.urgentLinkUser = urgentLinkUser;
    }

    public String getUrgentLinkTel() {
        return urgentLinkTel;
    }

    public void setUrgentLinkTel(String urgentLinkTel) {
        this.urgentLinkTel = urgentLinkTel;
    }
}
