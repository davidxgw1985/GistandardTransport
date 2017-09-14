package com.gistandard.transport.app.dubbo.sms.bean;


import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

/**
 * @author: xgw
 * @ClassName: SmsValidateReq
 * @Date: 2016/2/4
 * @Description:  短信验证码校验 请求Bean
 */
public class SmsValidateReq extends MsDubboReqBean {
    private static final long serialVersionUID = 2240742373689282517L;

    private String tokenId;//短信验证码对应token
    private String verifyCode;//用户输入的验证码
    private String receiveNo;//短信接收人号码

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
