package com.gistandard.transport.app.dubbo.sms.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: SendSmsVerifyCodeRes
 * @Date: 2016/2/27
 * @Description:
 */
public class SendSmsVerifyCodeRes implements Serializable {
    private static final long serialVersionUID = -8519590369012477845L;

    private String tokenId;//短信验证发送后返回tokenId

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
