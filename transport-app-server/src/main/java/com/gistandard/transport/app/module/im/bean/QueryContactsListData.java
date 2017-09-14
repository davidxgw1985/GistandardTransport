package com.gistandard.transport.app.module.im.bean;

/**
 * Created by yujie on 2016/9/30.
 */
public class QueryContactsListData {

    private int accountId;//账号Id
    private String acctUsername;//登录账号
    private String nickName;//昵称
    private String userImg;//账号头像

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }
}
