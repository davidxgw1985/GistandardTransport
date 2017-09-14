package com.gistandard.transport.system.common.baseinfo.bean;

/**
 * @author kongxm
 * @ClassName BaseInfoRes
 * @Description
 * @Version 1.0
 * @Date 2016/1/28
 */
public class BaseInfoRes {
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String nickName;
    private String phone;
    private String avatar;
}
