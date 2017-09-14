package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @Author zxnui
 * @Date 8/11/16.
 */
public class PayStatusReq extends AppBaseRequest{
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
