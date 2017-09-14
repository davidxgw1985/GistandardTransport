package com.gistandard.transport.app.module.account.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/5/16.
 */
public class QueryAccountByTelephoneReq extends AppBaseRequest {
    private String phoneNumber;

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPhoneNumber(){ return phoneNumber; }

    @Override
    public String toString() {
        return "QueryAccountByTelephoneReq{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
