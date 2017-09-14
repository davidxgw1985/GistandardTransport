package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

/**
 * 商户用户快递员认证资料填写bean
 */
public class MerchantCourierBean implements Serializable{

    private String urgentLinkUser;

    private String urgentLinkTel;

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
