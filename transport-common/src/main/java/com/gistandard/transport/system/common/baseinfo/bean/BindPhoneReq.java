package com.gistandard.transport.system.common.baseinfo.bean;


import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author kongxm
 * @ClassName BindPhoneReq
 * @Description 客户下单APP，绑定手机请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class BindPhoneReq extends AppBaseRequest {

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    private String phone;
    private String verifyCode;
    private String tokenId;

}