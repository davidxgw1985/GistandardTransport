package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.transport.system.webservice.client.payinfo.ValidBillMst;

/**
 * @author: xgw
 * @ClassName: ValidBillMstResBean
 * @Date: 2017/7/15
 * @Description:
 */
public class ValidBillMstResBean extends ValidBillMst{
    private String expressUser;//快递员账号
    private String expressName;//快递员姓名
    private String productType;//产品类型
    private Integer testFlag;//是否测试单 1 测试单 0 真实单

    public Integer getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Integer testFlag) {
        this.testFlag = testFlag;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getExpressUser() {
        return expressUser;
    }

    public void setExpressUser(String expressUser) {
        this.expressUser = expressUser;
    }
}
