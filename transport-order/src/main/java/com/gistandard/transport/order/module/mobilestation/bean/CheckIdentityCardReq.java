package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: CheckIdentityCardReq
 * @Date: 2016/2/24
 * @Description: 请求身份证验证请求Bean
 */
public class CheckIdentityCardReq extends AppBaseRequest {
    private static final long serialVersionUID = -4462213612856179303L;

    private String identityCard;//身份证号码

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}
