package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/10/9.
 */
public class QueryPlatformQuoteReq extends AppBaseRequest {
    private String systemFlag;  //系统标识
    private String itemCode;  //产品类型
    private String userScope;  //角色（1:W1,2:W2,3:W3,4:小(M)站,5:快递员,6:司机）

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUserScope() {
        return userScope;
    }

    public void setUserScope(String userScope) {
        this.userScope = userScope;
    }
}
