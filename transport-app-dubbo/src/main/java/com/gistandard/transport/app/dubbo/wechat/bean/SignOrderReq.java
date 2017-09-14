package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信签收
 * Created by m on 2017/4/12.
 */
public class SignOrderReq implements Serializable {
    private Integer id;//被签收订单ID（必传）
    private String telephone;//手机号码，用于校验是否是收件人传参（必传）
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
