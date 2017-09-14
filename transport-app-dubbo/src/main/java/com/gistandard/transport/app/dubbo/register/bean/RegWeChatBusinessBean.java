package com.gistandard.transport.app.dubbo.register.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/4/25 0025.
 */
public class RegWeChatBusinessBean implements Serializable {

    private static final long serialVersionUID = -1531180855278383243L;

    //朋友圈截图id
    private Integer friendsScreenshotFileId;

    //朋友圈截图url
    private String friendsScreenshotUrl;

    //微商微信号
    private String wechatMerchantAccount;

    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getFriendsScreenshotFileId() {
        return friendsScreenshotFileId;
    }

    public void setFriendsScreenshotFileId(Integer friendsScreenshotFileId) {
        this.friendsScreenshotFileId = friendsScreenshotFileId;
    }

    public String getFriendsScreenshotUrl() {
        return friendsScreenshotUrl;
    }

    public void setFriendsScreenshotUrl(String friendsScreenshotUrl) {
        this.friendsScreenshotUrl = friendsScreenshotUrl;
    }

    public String getWechatMerchantAccount() {
        return wechatMerchantAccount;
    }

    public void setWechatMerchantAccount(String wechatMerchantAccount) {
        this.wechatMerchantAccount = wechatMerchantAccount;
    }

}
