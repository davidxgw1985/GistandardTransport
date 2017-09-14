package com.gistandard.transport.app.dubbo.register.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/4/25 0025.
 */
public class RegOnlineBusinessBean implements Serializable {

    private static final long serialVersionUID = -1531180855278383243L;

    private String shopkeeperName;
    private String shopkeeperIdentityNo;
    private String shopName;
    private String shopLink;
    private Integer shopScreenshotFileId;
    private Integer shopkeeperFileId;
    private String shopScreenshotFileUrl;
    private String shopkeeperFileIdUrl;
    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getShopkeeperName() {
        return shopkeeperName;
    }

    public void setShopkeeperName(String shopkeeperName) {
        this.shopkeeperName = shopkeeperName;
    }

    public String getShopkeeperIdentityNo() {
        return shopkeeperIdentityNo;
    }

    public void setShopkeeperIdentityNo(String shopkeeperIdentityNo) {
        this.shopkeeperIdentityNo = shopkeeperIdentityNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }

    public Integer getShopScreenshotFileId() {
        return shopScreenshotFileId;
    }

    public void setShopScreenshotFileId(Integer shopScreenshotFileId) {
        this.shopScreenshotFileId = shopScreenshotFileId;
    }

    public Integer getShopkeeperFileId() {
        return shopkeeperFileId;
    }

    public void setShopkeeperFileId(Integer shopkeeperFileId) {
        this.shopkeeperFileId = shopkeeperFileId;
    }

    public String getShopScreenshotFileUrl() {
        return shopScreenshotFileUrl;
    }

    public void setShopScreenshotFileUrl(String shopScreenshotFileUrl) {
        this.shopScreenshotFileUrl = shopScreenshotFileUrl;
    }

    public String getShopkeeperFileIdUrl() {
        return shopkeeperFileIdUrl;
    }

    public void setShopkeeperFileIdUrl(String shopkeeperFileIdUrl) {
        this.shopkeeperFileIdUrl = shopkeeperFileIdUrl;
    }
}
