package com.gistandard.transport.applytobe.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 商户用户快递员认证资料填写bean
 */
public class MerchantCourierBean extends MerchantPersonalBean implements Serializable{

    // 紧急联系人
    @Length(min = 0,max = 20, message = "紧急联系人超出指定长度")
    private String urgentLinkUser;

    // 紧急联系电话
    @Length(min = 0,max = 20, message = "紧急联系电话超出指定长度")
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
