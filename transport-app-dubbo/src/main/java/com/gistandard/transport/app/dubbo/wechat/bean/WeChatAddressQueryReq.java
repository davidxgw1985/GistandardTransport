package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;

/**
 * Created by m on 2017/2/6.
 */
public class WeChatAddressQueryReq implements Serializable {
    private String weChatId;//微信客户标识
    private Integer addressType;//类型： 1发货地址，2收货地址
    private Integer accountId;//绑定了o2id的，传帐号id比如44448（选填）
    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
