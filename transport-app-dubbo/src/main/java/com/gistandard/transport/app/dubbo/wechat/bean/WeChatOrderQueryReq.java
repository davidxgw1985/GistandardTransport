package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;

/**
 * 微信预约单查询请求
 * Created by m on 2017/2/6.
 */
public class WeChatOrderQueryReq implements Serializable {
    private static final long serialVersionUID = 6117221349467174589L;
    private String weChatId;//微信客户标识（查件必传）
    private String telephone;//手机号码，用于收件传参（收件必传）
    private String o2id;//绑定了o2id的，传帐号比如CN0002500560000（选填）
    private Boolean orderType = Boolean.TRUE;//查询类型查件/收件 true/false（必填）
    private Integer staffAccountId;//绑定的企业账户ID
    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean isOrderType() {
        return orderType;
    }

    public void setOrderType(Boolean orderType) {
        this.orderType = orderType;
    }

    public String getO2id() {
        return o2id;
    }

    public void setO2id(String o2id) {
        this.o2id = o2id;
    }

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }
}
