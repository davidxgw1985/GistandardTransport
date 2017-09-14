package com.gistandard.transport.order.module.agency.bean;


import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/5/17.
 */
public class BroadcastOrderReq extends AppBaseRequest {
    private String acctUsername;
    private String bookbusino;
    private String userImg;//头像
    private String realName;//真实姓名
    private String registerType;//类型
    private String tel;//手机


    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getBookbusino() {
        return bookbusino;
    }

    public void setBookbusino(String bookbusino) {
        this.bookbusino = bookbusino;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
